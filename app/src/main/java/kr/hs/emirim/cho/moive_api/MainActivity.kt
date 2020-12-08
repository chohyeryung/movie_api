package kr.hs.emirim.cho.moive_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
        }
    }


}