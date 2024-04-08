package com.exo.inb1oo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ImageView

class TypeCardAdapter (private val context: Context, private val imageList: List<Int>) : ArrayAdapter<Int>(context, R.layout.sp_type_card, imageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.sp_type_card, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(imageList[position])

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.sp_type_card, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(imageList[position])

        return view
    }


}