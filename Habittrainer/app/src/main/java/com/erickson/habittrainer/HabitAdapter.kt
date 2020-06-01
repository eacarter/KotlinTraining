package com.erickson.habittrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.singlecard.view.*

class HabitAdapter(val habits: List<Habit>): RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(val v1: View) : RecyclerView.ViewHolder(v1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlecard, parent,false)
        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        if(holder != null){
            holder.v1.text1.text = habits[position].title
            holder.v1.text2.text = habits[position].descrip
            holder.v1.image.setImageResource(habits[position].image)
        }
    }
}