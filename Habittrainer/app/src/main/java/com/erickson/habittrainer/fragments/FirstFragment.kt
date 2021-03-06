package com.erickson.habittrainer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.erickson.habittrainer.R
import com.erickson.habittrainer.adapter.HabitAdapter
import com.erickson.habittrainer.db.habitdbtable
//import com.erickson.habittrainer.data.getSampleHabit
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var tvDescription: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Addapter defines data
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(view.context)
        recycler_view.adapter = HabitAdapter(habitdbtable(view.context).readAll())
//            HabitAdapter(getSampleHabit())


//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }
}
