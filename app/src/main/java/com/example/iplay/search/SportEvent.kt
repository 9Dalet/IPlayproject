package com.example.iplay.search

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.ViewTarget
import com.google.firebase.storage.StorageReference
import java.sql.Timestamp

class SportEvent(
    var luogo: String,
    var numPersone: String,
    var data: String,
    var ora: String,
    var prezzo: String,
    var sport: String,
    var image: String
) {
}

