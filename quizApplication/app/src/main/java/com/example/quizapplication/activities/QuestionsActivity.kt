package com.example.quizapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapplication.activities.ResultActivity
import com.example.quizapplication.adapters.OptionAdapter
import com.example.quizapplication.databinding.ActivityQuestionsBinding
import com.example.quizapplication.models.Questions
import com.example.quizapplication.models.Quiz
import com.google.android.play.integrity.internal.c
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionsBinding
    private var quizzes : MutableList<Quiz>? = null
    private var questions : MutableMap<String, Questions>? = null
    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpEventListener()
        setUpFireStore()
    }

    private fun setUpEventListener() {
        binding.btnPrevious.setOnClickListener{
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener{
            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("FINAL QUIZ", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }

    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        val date = intent.getStringExtra("DATE")
        if(date!=null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        binding.btnNext.visibility = View.GONE

        when (index) {
            1 -> { // First question
                binding.btnNext.visibility = View.VISIBLE
            }
            questions!!.size -> { // Last question
                binding.btnSubmit.visibility = View.VISIBLE
                binding.btnPrevious.visibility = View.VISIBLE
            }
            else -> { // Middle questions
                binding.btnPrevious.visibility = View.VISIBLE
                binding.btnNext.visibility = View.VISIBLE
            }
        }

        val questions = questions!!["question$index"]
        questions?.let { binding.description.text = questions.description
            val optionAdapter = OptionAdapter(this, questions)
            binding.optionList.layoutManager = LinearLayoutManager(this)
            binding.optionList.adapter = optionAdapter
            binding.optionList.setHasFixedSize(true)}
    }
}
