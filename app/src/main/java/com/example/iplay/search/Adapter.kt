package com.example.iplay.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iplay.R

class Adapter(private val sports: ArrayList<SportEvent>, private val context: Context)
    : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val event = sports[position]

        holder.luogoEvento.text = event.luogo
        holder.numPersoneEvento.text = event.numPersone
        holder.dataEvento.text = event.data
        holder.oraEvento.text = event.ora
        holder.prezzoEvento.text = event.prezzo
        holder.sportEvento.text = event.sport

        Glide.with(context).load(event.image).into(holder.imageurl)
        holder.view.setOnClickListener { mListener?.selectItem(position) }
    }

    override fun getItemCount() = sports.size

    interface AdapterCallback {
        fun selectItem(position: Int)
    }

    private var mListener: AdapterCallback? = null

    //mi serve se devo cliccare sulla card
    fun setOnCallback(mItemClickListener: AdapterCallback) {
        mListener = mItemClickListener
    }
}

class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view) {

    val luogoEvento = view.findViewById<TextView>(R.id.nameSport)
    val numPersoneEvento = view.findViewById<TextView>(R.id.textView5)
    val dataEvento = view.findViewById<TextView>(R.id.textView7)
    val oraEvento = view.findViewById<TextView>(R.id.oraEvent)
    val prezzoEvento = view.findViewById<TextView>(R.id.prezzo)
    val sportEvento = view.findViewById<TextView>(R.id.textView4)
    val imageurl = view.findViewById<ImageView>(R.id.imageSport)
}

