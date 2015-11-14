package cn.flydrm.fm.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.flydrm.fm.R;
import cn.flydrm.fm.adapter.ManitoBlogRecyclerAdapter;
import cn.flydrm.fm.bean.Blog;

public class ManitoBlogActivity extends AppCompatActivity {
    public static final String AUTHOR_NAME = "authorName";
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecView;
    private List<Blog> mBlogs = new ArrayList<Blog>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manito_blog);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.blog_manito_toobar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        doGetData(); // 获取博客数据

        mLayoutInflater = LayoutInflater.from(this);
        mRecView = (RecyclerView) findViewById(R.id.blog_manito_recyclerview);
        mRecView.setLayoutManager(new LinearLayoutManager(this));
        mRecView.setAdapter(new ManitoBlogRecyclerAdapter(this, mBlogs));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void doGetData() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        new BlogGetAysncTask().execute("http://427studio.net/api/blog");
                    }
                }
        ).start();
    }

    class BlogGetAysncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn;
            InputStream in;
            String result = "";
            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                in = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
                in.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson mGson = new Gson();
            TypeToken token = new TypeToken<List<Blog>>(){};
            mBlogs = mGson.fromJson(s, token.getType());
            Collections.reverse(mBlogs);
            mRecView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecView.setAdapter(new ManitoBlogRecyclerAdapter(mContext, mBlogs));

        }
    }
}
