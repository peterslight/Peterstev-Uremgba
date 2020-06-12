package com.peterstev.peterstevuremgba

import com.peterstev.peterstevuremgba.utils.FilterImpl
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FilterTest {

    private val colorList = listOf(
        "Green",
        "Violet",
        "Yellow",
        "Blue",
        "Teal",
        "Maroon",
        "Red",
        "Aquamarine",
        "Orange",
        "Mauv"
    )
    private val countryList = listOf(
        "China",
        "South Africa",
        "france",
        "Mexico",
        "Japan",
        "Estonia",
        "Colombia",
        "China"
    )

    @Test
    fun isValuePresent_nonPresentValue_false() {
        assertFalse("value isn't present", FilterImpl().isValuePresent("nigeria", countryList))
        assertFalse("value isn't present", FilterImpl().isValuePresent("pink", colorList))
    }

    @Test
    fun isValuePresent_presentValue_true() {
        assertTrue("value is present", FilterImpl().isValuePresent("france", countryList))
        assertTrue("value is present", FilterImpl().isValuePresent("Blue", colorList))
    }

    @Test
    fun isValuePresent_emptyString_false() {
        assertFalse("value isn't present", FilterImpl().isValuePresent("", countryList))
        assertFalse("value isn't present", FilterImpl().isValuePresent("", colorList))
    }

    @Test
    fun isValuePresent_null_false() {
        assertFalse("value isn't present", FilterImpl().isValuePresent(null, countryList))
        assertFalse("value isn't present", FilterImpl().isValuePresent(null, colorList))
    }

    @Test
    fun stringNullEquals_null_false() {
        assertFalse("values aren't equal", FilterImpl().publicStringNullEquals(colorList[0], null))
    }

    @Test
    fun stringNullEquals_emptyString_false() {
        assertFalse("values aren't equal", FilterImpl().publicStringNullEquals(colorList[0], ""))
    }

    @Test
    fun stringNullEquals_wrongString_false() {
        assertFalse(
            "values aren't equal",
            FilterImpl().publicStringNullEquals(colorList[0], colorList[1])
        )
    }

    @Test
    fun stringNullEquals_correctValue_true() {
        assertTrue("values are equal", FilterImpl().publicStringNullEquals(colorList[0], "green"))
    }
}