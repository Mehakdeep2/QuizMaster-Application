package com.example.quizapplication.utils

import com.example.quizapplication.R

object IconPicker {
    private val icons = arrayOf(R.drawable.icon01,
        R.drawable.icon02,
        R.drawable.icon03,
        R.drawable.icon04,
        R.drawable.icon05,
        R.drawable.icon06,
        R.drawable.icon07,
        R.drawable.icon08)

    private var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}
