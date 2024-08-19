package com.example.quizapplication.utils

object ColorPicker {
    private val colors = arrayOf(
        "#FF6F61", // Teal Blue
        "#98FF98", // Coral Pink
        "#FF6F61", // Lavender
        "#98FF98", // Emerald Green
        "#FF6F61", // Sunset Orange
        "#98FF98", // Sapphire Blue
        "#FF6F61", // Rose Gold
        "#98FF98", // Goldenrod
        "#FF6F61", // Turquoise
        "#98FF98"  // Amethyst Purple
    )

    private var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}
