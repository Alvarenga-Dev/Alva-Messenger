package com.alvarengadev.alvamessenger.view.adapters.messages

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R

class ViewHolderMessages(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvMessage = itemView.findViewById(R.id.tvMessage) as TextView
}
