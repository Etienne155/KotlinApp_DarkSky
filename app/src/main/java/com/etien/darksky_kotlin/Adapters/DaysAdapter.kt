package com.etien.darksky_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.etien.darksky_kotlin.DataModels.OneDayData
import com.etien.darksky_kotlin.DataModels.OneHourData
import com.etien.darksky_kotlin.R

class DaysAdapter(private val context: Context,
                  private val dataSource: MutableList<OneDayData>) : BaseAdapter() {

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

        val rowView = inflater.inflate(R.layout.day_item, parent, false)

        val timeDayView: TextView = rowView.findViewById(R.id.timeDayView)
        val precipIntensityDayView: TextView = rowView.findViewById(R.id.precipIntensityDayView)
        val precipProbabilityDayView: TextView = rowView.findViewById(R.id.precipProbabilityDayView)

        val increment: Int = (dataSource.get(position).time!! - dataSource.get(0).time!!).div(60 * 60 * 24).toInt()
        val precipIntensity: Double = dataSource.get(position).precipIntensity!!
        val precipProbability: Double = dataSource.get(position).precipProbability!! * 100

        timeDayView.setText("+ $increment " + context.getString(R.string.days))
        precipIntensityDayView.setText(context.getString(R.string.precipIntensity) + " : " + String.format("%.2f", precipIntensity) + " mm/h")
        precipProbabilityDayView.setText(context.getString(R.string.precipProbability) + " : " + String.format("%.0f", precipProbability) + " %")

        return rowView
    }
}
