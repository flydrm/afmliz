/*
package cn.flydrm.fm.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

*/
/**
 * Created by Administrator on 2015/11/12.
 *//*

public class HttpHelper {
123 main

    public static void get(String url) {
        final String u = url;
        AsyncTask mAsyncTask = new AsyncTask<String,Integer,String>() {
            @Override
            protected String doInBackground(String... params) {
                URL url;
                HttpURLConnection connection;
                String result="";
                try {

                    url = new URL(u);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    String line = "";
                    StringBuffer sb = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while((line = bufferedReader.readLine())!=""){
                        sb.append(line);
                    }
                    result = sb.toString();
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(result);
                Log.e("--------------",result);
                return result;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };


    }


}
*/
