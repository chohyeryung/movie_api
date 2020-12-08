package kr.hs.emirim.cho.moive_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_getData.setOnClickListener{
            var thread=OpenApiThread()
            thread.start()
        }
    }

    inner class OpenApiThread:Thread(){
        override fun run(){
            var url= URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20190527")
            var conn:URLConnection=url.openConnection()
            var input=conn.getInputStream()
            var instreamr=InputStreamReader(input)
            var br=BufferedReader(instreamr)
            var buf=StringBuffer()
            var str:String?=null
            do{
                str=br.readLine()
                if(str!=null)
                    buf.append(str)
            }while(str!=null)

            var rootJsonObject=JSONObject(buf.toString())
            var level1JsonObject:JSONObject=rootJsonObject.getJSONObject("boxOfficeResult")
            //화면에 출력하는 부분
            runOnUiThread{
                textView.append("result: ${level1JsonObject}\n")
                var movieList=level1JsonObject.getJSONArray("dailyBoxOfficeList")

                for(i in 0 until movieList.length()){
                    var obj=movieList.getJSONObject(i)
                    var rank= obj.getString("rank")
                    var title=obj.getString("movieNm")
                    textView.append("순위 : "+rank)
                    textView.append("영화 제목 : "+title)
                }

            }
        }
    }


}