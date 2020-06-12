package com.peterstev.peterstevuremgba.utils

class ColorFactory {

    fun getMatchingColor(color: String?): String? {
        val defaultColor = "#3869fe"
        if (color.isNullOrEmpty())
            return defaultColor
        val colorMap = getColorMap()
        return if (colorMap.containsKey(color)) colorMap[color]?.trimEnd() else defaultColor
    }

    private fun getColorMap(): Map<String, String> {
        val map = HashMap<String, String>()
        map["Green"] = "#00E676"
        map["Violet"] = "#7F00FF"
        map["Yellow"] = "#FFFF00"
        map["Blue"] = "#0288D1"
        map["Teal"] = "#00796B"
        map["Maroon"] = "#800000"
        map["Red"] = "#D32F2F"
        map["Aquamarine"] = "#7fffd4"
        map["Orange"] = "#EF6C00"
        map["Mauv"] = "#b784a7"

        return map
    }
}