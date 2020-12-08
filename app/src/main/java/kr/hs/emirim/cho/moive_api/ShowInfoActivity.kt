package kr.hs.emirim.cho.moive_api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.showinfo.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class ShowInfoActivity : AppCompatActivity() {
    var arrayList : ArrayList<MovieC> = arrayListOf()
    lateinit var adapter1 : ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.showinfo)
        adapter1=ListViewAdapter(arrayList)
        listView.adapter=adapter1

        var thread=OpenApiThread()
        thread.start()
    }

    inner class OpenApiThread:Thread(){
        override fun run(){
            var url= URL("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20190527")
            var conn: URLConnection =url.openConnection()
            var input=conn.getInputStream()
            var instreamr= InputStreamReader(input)
            var br= BufferedReader(instreamr)
            var buf=StringBuffer()
            var str:String?=null
            do{
                str=br.readLine()
                if(str!=null)
                    buf.append(str)
            }while(str!=null)

            var rootJsonObject= JSONObject(buf.toString())
            var level1JsonObject: JSONObject =rootJsonObject.getJSONObject("boxOfficeResult")
            //화면에 출력하는 부분
            runOnUiThread{
                var movieList=level1JsonObject.getJSONArray("dailyBoxOfficeList")

                for(i in 0 until movieList.length()) {
                    var obj = movieList.getJSONObject(i)

                    var rank = obj.getString("rank")
                    var title = obj.getString("movieNm")
                    var openDt:String=obj.getString("openDt")
                    var audiAcc:String=obj.getString("audiAcc")

                    var movie1=MovieC(rank+"위", title, openDt, audiAcc)
                    arrayList.add(movie1)

                }

                if(arrayList.size>0){
                    adapter1.notifyDataSetChanged()
                }

            }
        }
    }
}