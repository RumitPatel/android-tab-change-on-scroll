package duggu.scroll.tabchange.util

import duggu.scroll.tabchange.models.TitleInfo

const val TAG = "@***@***@"
fun getTitles() : ArrayList<TitleInfo?> {
    return ArrayList<TitleInfo?>().apply {
        add(TitleInfo("Classification", true))
        add(TitleInfo("Overview", false))
        add(TitleInfo("Description", false))
    }
}