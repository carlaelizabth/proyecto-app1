package com.exo.inb1oo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ImageView

class TypeCardAdapter (private val context: Context, private val imageList: List<Int>) : ArrayAdapter<Int>(context, 0, imageList) {

    private val factRend = 1.0f

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        val margen = 10

        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(margen, margen, margen, margen)
        } else {
            imageView = convertView as ImageView
        }

        val bitmap = BitmapFactory.decodeResource(context.resources, imageList[position])
        val bitmapRedimend = Bitmap.createScaledBitmap(bitmap, (bitmap.width*factRend).toInt(), (bitmap.height*factRend).toInt(), false)


        imageView.setImageBitmap(bitmapRedimend)
        return imageView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }


}