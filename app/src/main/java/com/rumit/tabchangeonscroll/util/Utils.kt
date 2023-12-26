package com.rumit.tabchangeonscroll.util

import com.rumit.tabchangeonscroll.models.TitleInfo

const val TAG = "@***@***@"
fun getTitles() : ArrayList<TitleInfo?> {
    return ArrayList<TitleInfo?>().apply {
        add(TitleInfo("Classification", true))
        add(TitleInfo("Overview", false))
        add(TitleInfo("Description", false))
    }
}