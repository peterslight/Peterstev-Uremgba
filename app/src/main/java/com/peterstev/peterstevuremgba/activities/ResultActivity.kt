package com.peterstev.peterstevuremgba.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.peterstev.peterstevuremgba.R
import com.peterstev.peterstevuremgba.adapters.ResultAdapter
import com.peterstev.peterstevuremgba.models.FilterResult
import com.peterstev.peterstevuremgba.models.UserAccount
import com.peterstev.peterstevuremgba.utils.FILTER_KEY
import com.peterstev.peterstevuremgba.utils.FilterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.util.stream.Collectors

class ResultActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var bufferedReader: BufferedReader
    private lateinit var adapter: ResultAdapter
    private lateinit var filter: UserAccount
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressDialog: MaterialCardView
    private lateinit var title: TextView
    private lateinit var backKey: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        recyclerView = main_recyclerview
        progressDialog = home_progress
        title = main_title
        backKey = main_back_key

        backKey.visibility = View.VISIBLE
        title.text = getString(R.string.results)
        backKey.setOnClickListener {
            onBackPressed()
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ResultAdapter(this, ArrayList())
        recyclerView.adapter = adapter

        filter = getIntentData()!!
        if (filter.colors.isNotEmpty()
            || filter.countries.isNotEmpty()
            || filter.gender.isNotEmpty()
        ) {
            getMoreData()
        } else {
            dialog()
        }
    }

    private fun getIntentData(): UserAccount? =
        intent.getSerializableExtra(FILTER_KEY) as UserAccount

    private fun getMoreData() {
        progressDialog.visibility = View.VISIBLE
        launch {
            val items = getDataFromReader()
            withContext(Dispatchers.Main) {
                adapter.updateList(items)
                progressDialog.visibility = View.GONE
            }
        }
    }

    private fun isFiltered(result: FilterResult?): Boolean {
        return (FilterImpl().isValuePresent(result?.country, filter.countries)
                && FilterImpl().isValuePresent(result?.carColor, filter.colors)
                && FilterImpl().publicStringNullEquals(filter.gender, result?.gender))
    }

    private fun dialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setMessage("Sorry no results match your search")
        dialog.setPositiveButton("Try Another") { dialogInt, _ ->
            dialogInt.dismiss()
            onBackPressed()
        }
        dialog.show()
    }

    private suspend fun getDataFromReader(): List<FilterResult> {
        bufferedReader = resources.openRawResource(R.raw.car_ownsers_data).bufferedReader()
        return withContext(Dispatchers.IO) {
            val list = bufferedReader.lines()!!
                .skip(1)
                .collect(Collectors.toList())

            val newList = ArrayList<FilterResult>()

            for (it in list) {
                val tokens = it.toString().split(",".toRegex(), 11)

                try {
                    val result = FilterResult(
                        Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3],
                        tokens[4], tokens[5], Integer.valueOf(tokens[6]),
                        tokens[7], tokens[8], tokens[9], tokens[10]
                    )

                    if (isFiltered(result)) {
                        newList.add(result)
                    }
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }
            return@withContext newList
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
