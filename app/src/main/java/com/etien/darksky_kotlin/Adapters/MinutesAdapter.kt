package com.etien.darksky_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.etien.darksky_kotlin.DataModels.OneMinuteData
import com.etien.darksky_kotlin.R

class MinutesAdapter(private val context: Context,
                     private val dataSource: MutableList<OneMinuteData>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.minute_item, parent, false)

        val timeMinuteView: TextView = rowView.findViewById(R.id.timeMinuteView)
        val precipIntensityMinuteView: TextView = rowView.findViewById(R.id.precipIntensityMinuteView)
        val precipProbabilityMinuteView: TextView = rowView.findViewById(R.id.precipProbabilityMinuteView)

        val increment: Int = (dataSource.get(position).time!! - dataSource.get(0).time!!).div(60).toInt()
        val precipIntensity: Double = dataSource.get(position).precipIntensity!!
        val precipProbability: Double = dataSource.get(position).precipProbability!! * 100

        timeMinuteView.setText("+ $increment minutes")
        precipIntensityMinuteView.setText("PrecipIntensity: " + String.format("%.2f", precipIntensity) + " mm/h")
        precipProbabilityMinuteView.setText("PrecipProbability: " + String.format("%.1f", precipProbability) + "%")

        return rowView
    }
}
