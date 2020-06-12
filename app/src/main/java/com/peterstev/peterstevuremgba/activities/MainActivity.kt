package com.peterstev.peterstevuremgba.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.peterstev.peterstevuremgba.R
import com.peterstev.peterstevuremgba.adapters.MainAdapter
import com.peterstev.peterstevuremgba.models.UserAccount
import com.peterstev.peterstevuremgba.network.ServiceGenerator
import com.peterstev.peterstevuremgba.utils.FILTER_KEY
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainAdapter.MainItemClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressDialog: MaterialCardView
    private lateinit var adapter: MainAdapter
    private lateinit var title: TextView
    private lateinit var backKey: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews().apply {
            getRemoteFilters()
        }
    }

    private fun setupViews() {
        recyclerView = main_recyclerview
        progressDialog = home_progress
        title = main_title
        backKey = main_back_key

        backKey.visibility = View.GONE
        title.text = getString(R.string.filters)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        adapter = MainAdapter(this, ArrayList(), this)
        recyclerView.adapter = adapter
    }

    private fun getRemoteFilters() {
        progressDialog.visibility = View.VISIBLE
        val api = ServiceGenerator().getRetrofit(this)
        val call = api.getFilters()
        call.enqueue(object : Callback<List<UserAccount>> {
            override fun onFailure(call: Call<List<UserAccount>>, t: Throwable) {
                progressDialog.visibility = View.GONE
                Snackbar.make(
                    recyclerView,
                    t.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Try Again") {
                    getRemoteFilters()
                }.show()
            }

            override fun onResponse(
                call: Call<List<UserAccount>>,
                response: Response<List<UserAccount>>
            ) {
                progressDialog.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    val itemList = response.body()
                    adapter.updateList(itemList!!)
                } else {
                    Snackbar.make(
                        recyclerView,
                        "unable to load resources, try again",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Try Again") {
                        getRemoteFilters()
                    }.show()

                }
            }
        })
    }

    override fun onItemClick(userItem: UserAccount) {
        startActivity(
            Intent(this, ResultActivity::class.java)
                .putExtra(FILTER_KEY, userItem)
        )
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}