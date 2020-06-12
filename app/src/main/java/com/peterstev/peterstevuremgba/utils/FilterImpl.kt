package com.peterstev.peterstevuremgba.utils

open class FilterImpl {

    fun isValuePresent(needle: String?, haystack: List<String>?): Boolean {
        return if (haystack.isNullOrEmpty())
            true
        else
            haystack.contains(needle)
    }

    fun String?.nullEquals(result: String?): Boolean {
        return if (this.isNullOrBlank() || this.isEmpty())
            true
        else
            this.equals(result, true)
    }

    fun publicStringNullEquals(constant: String?, testValue:String?):Boolean =
        constant.nullEquals(testValue)

}