package cn.flydrm.fm.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.flydrm.fm.R;
import cn.flydrm.fm.bean.Blog;

public class ManitoBlogDetailActivity extends AppCompatActivity {
    public static final String BLOG_ID = "blog_id";
    private Toolbar mToolbar;
    private WebView mWebView;
    private LayoutInflater mInflater;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manito_blog_detail);
        mToolbar = (Toolbar) findViewById(R.id.manito_blog_detail_toobar);

//        mToolbar.setTitle("123");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mInflater = LayoutInflater.from(this);
        mWebView = (WebView) findViewById(R.id.manito_blog_detail_content);
        mProgressBar = (ProgressBar) findViewById(R.id.test_processbar);
//        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadData("2123", "text/html", "utf-8");
        String bid;
        bid = getIntent().getStringExtra(BLOG_ID);
        if (bid!=null){

            getData(bid);
        }


    }

    public void getData(final String bid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new BlogDetalTask().execute("http://427studio.net/api/blog/"+bid,"100");
            }
        }).start();
    }

    class BlogDetalTask extends AsyncTask<String,Integer,String >{

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("charset","utf-8");
                conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream in = conn.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));

                String line;
                int i=1;

                while((line=r.readLine())!=null){
                    publishProgress(i);
                    sb.append(line);
                    i++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Blog b = gson.fromJson(s, Blog.class);
            mProgressBar.setVisibility(View.GONE);
            mWebView.loadDataWithBaseURL("",b.getContent(),"text/html","utf-8",null);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            int i = values[0];
            Log.e(" 测试",""+i);
            mProgressBar.setProgress(i);
        }
    }

}
