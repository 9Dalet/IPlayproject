package com.example.iplay.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iplay.R

class Adapter(private val sports: ArrayList<SportEvent>, private val context: Context)
    : RecyclerView.Adapter<Adapter.CustomViewHolder>() {


    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val event = sports[position]

        val luogoEvento = holder.view.findViewById<TextView>(R.id.nameSport)
        luogoEvento.text = event.luogo

        val numPersoneEvento = holder.view.findViewById<TextView>(R.id.textView5)
        numPersoneEvento.text = event.numPersone

        val dataEvento = holder.view.findViewById<TextView>(R.id.textView7)
        dataEvento.text = event.data

        val oraEvento = holder.view.findViewById<TextView>(R.id.oraEvent)
        oraEvento.text = event.ora

        val prezzoEvento = holder.view.findViewById<TextView>(R.id.prezzo)
        prezzoEvento.text = event.prezzo

        val sportEvento = holder.view.findViewById<TextView>(R.id.textView4)
        sportEvento.text = event.sport


//        holder.view.setOnClickListener {
//            /*val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("name", product.name)
//            context.startActivity(intent)*/
//            mListener?.selectItem(position)
//        }
//        holder.view.setOnLongClickListener {
//            //mListener?.deleteItem(position)
//            products.removeAt(position)
//            notifyDataSetChanged()
//            //notifyItemRemoved(position)
//            true
//        }
    }

    override fun getItemCount() = sports.size

    /*
     *
     *       Callback
     *
     * */

//    interface AdapterCallback {
//        fun selectItem(position: Int)
//        fun deleteItem(position: Int)
//    }
//
//    private var mListener: AdapterCallback? = null
//
//    //mi serve se devo cliccare sulla card
//    fun setOnCallback(mItemClickListener: AdapterCallback) {
//        this.mListener = mItemClickListener
//    }
}