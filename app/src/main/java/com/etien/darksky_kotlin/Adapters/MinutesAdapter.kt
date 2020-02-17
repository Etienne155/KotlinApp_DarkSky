package com.etien.darksky_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.etien.darksky_kotlin.DataModels.OneMinuteData
import com.etien.darksky_kotlin.R
import kotlinx.android.synthetic.main.minute_item.view.*

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

        val actualTime: Double? = dataSource.get(position)?.time
        val firstTime: Double? = dataSource.get(0)?.time
        val increment: Int? = (firstTime?.let { actualTime?.minus(it) })?.div(60)?.toInt()

        val precipIntensity: Double? = dataSource.get(position)?.precipIntensity
        val precipProbability: Double? = dataSource.get(position)?.precipProbability?.times(100)

        val precipTypeVal: String? = dataSource.get(position)?.precipType
        var precipType = ""
        if(!precipTypeVal.isNullOrEmpty()) {
            precipType = " - $precipTypeVal"
        }

        rowView.timeMinuteView.setText(context.getString(R.string.minutes, increment))
        rowView.precipIntensityMinuteView.setText(context.getString(R.string.precipIntensity, String.format("%.2f", precipIntensity), precipType))
        rowView.precipProbabilityMinuteView.setText(context.getString(R.string.precipProbability, String.format("%.0f", precipProbability)))

        return rowView
    }
}
