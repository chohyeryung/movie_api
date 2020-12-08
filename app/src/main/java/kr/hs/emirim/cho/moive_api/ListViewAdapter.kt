package kr.hs.emirim.cho.moive_api

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.rowitem.view.*

class ListViewAdapter(var arrayList: ArrayList<MovieC>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var rowitemView=convertView
        if(rowitemView == null){
            rowitemView = View.inflate(parent!!.context, R.layout.rowitem, null)
        }
        var movie:MovieC=arrayList[position]
        rowitemView!!.rRank.text=movie.rank
        rowitemView!!.rName.text=movie.movieNm
        rowitemView!!.rOpenDt.text="개봉일 "+movie.openDt
        rowitemView!!.raudiAcc.text="누적 관객수  "+movie.audiAcc+"명"
        return rowitemView
    }

    override fun getItem(position: Int): Any {
       return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return arrayList.size
    }

}