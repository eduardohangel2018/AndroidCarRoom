package com.android.carroom

import android.view.View

interface RecyclerViewItemListener {

    fun recyclerViewItemClicked(view: View, id: Int)
}