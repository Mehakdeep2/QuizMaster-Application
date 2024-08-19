package com.example.quizapplication.models

data class Quiz(
    var id: String = "",
    var title: String = "",
    var questions: MutableMap<String, Questions> = mutableMapOf()
)
