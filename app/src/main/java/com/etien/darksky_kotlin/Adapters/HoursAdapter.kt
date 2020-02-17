package com.etien.darksky_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.etien.darksky_kotlin.DataModels.OneHourData
import com.etien.darksky_kotlin.R
import kotlinx.android.synthetic.main.hour_item.view.*

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

        val actualTime: Double? = dataSource.get(position)?.time
        val firstTime: Double? = dataSource.get(0)?.time
        val increment: Int? = (firstTime?.let { actualTime?.minus(it) })?.div(60 * 60)?.toInt()

        val precipIntensity: Double? = dataSource.get(position)?.precipIntensity
        val precipProbability: Double? = dataSource.get(position)?.precipProbability?.times(100)

        val precipTypeVal: String? = dataSource.get(position)?.precipType
        var precipType = ""
        if(!precipTypeVal.isNullOrEmpty()) {
            precipType = " - $precipTypeVal"
        }

        rowView.timeHourView.setText(context.getString(R.string.hours, increment))
        rowView.precipIntensityHourView.setText(context.getString(R.string.precipIntensity, String.format("%.2f", precipIntensity), precipType))
        rowView.precipProbabilityHourView.setText(context.getString(R.string.precipProbability, String.format("%.0f", precipProbability)))

        return rowView
    }
}
