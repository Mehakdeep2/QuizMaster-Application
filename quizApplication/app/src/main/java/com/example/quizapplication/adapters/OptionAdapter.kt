package com.example.quizapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.models.Questions

class OptionAdapter(
    private val context: Context,
    private val question: Questions
) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private val options: List<String> = listOf(
        question.option1,
        question.option2,
        question.option3,
        question.option4
    )

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionView: TextView = itemView.findViewById(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = options[position]
        holder.optionView.text = option

        holder.itemView.setOnClickListener {
            question.userAnswer = option
            notifyDataSetChanged()  // Update the RecyclerView to reflect changes
        }

        if (question.userAnswer == option) {
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int = options.size
}
