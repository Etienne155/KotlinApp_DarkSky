package com.etien.darksky_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.etien.darksky_kotlin.DataModels.OneHourData
import com.etien.darksky_kotlin.R

class HoursAdapter(private val context: Context,
                   private val dataSource: MutableList<OneHourData>) : BaseAdapter() {

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

        val rowView = inflater.inflate(R.layout.hour_item, parent, false)

        val timeHourView: TextView = rowView.findViewById(R.id.timeHourView)
        val precipIntensityHourView: TextView = rowView.findViewById(R.id.precipIntensityHourView)
        val precipProbabilityHourView: TextView = rowView.findViewById(R.id.precipProbabilityHourView)

        val increment: Int = (dataSource.get(position).time!! - dataSource.get(0).time!!).div(60 * 60).toInt()
        val precipIntensity: Double = dataSource.get(position).precipIntensity!!
        val precipProbability: Double = dataSource.get(position).precipProbability!! * 100

        timeHourView.setText("+ $increment" + context.getString(R.string.hours))
        precipIntensityHourView.setText(context.getString(R.string.precipIntensity) + ": " + String.format("%.2f", precipIntensity) + " mm/h")
        precipProbabilityHourView.setText(context.getString(R.string.precipProbability) + ": " + String.format("%.1f", precipProbability) + "%")

        return rowView
    }
}
