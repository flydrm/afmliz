package cn.flydrm.fm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.flydrm.fm.R;
import cn.flydrm.fm.bean.Blog;
import cn.flydrm.fm.ui.ManitoBlogDetailActivity;

/**
 * Created by Administrator on 2015/11/13.
 */
public class ManitoBlogRecyclerAdapter extends RecyclerView.Adapter<ManitoBlogRecyclerAdapter.MyBlogListViewHolder> {
    private List<Blog> mBlogs ;
    private LayoutInflater minfInflater;
    private Context mContext;

    public ManitoBlogRecyclerAdapter(Context context,List<Blog> blogs) {
        mContext = context;
        mBlogs = blogs;
        minfInflater = LayoutInflater.from(context);
    }

    @Override
    public MyBlogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = minfInflater.inflate(R.layout.manito_blog_list_item,null);
        return new MyBlogListViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyBlogListViewHolder holder, int position) {
        holder.mTitle.setText(mBlogs.get(position).getTitle());
        holder.mId.setText(String.valueOf(mBlogs.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return (mBlogs.size()==0)?0:mBlogs.size();
    }

    class MyBlogListViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle ;
        TextView mId;
        public MyBlogListViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.manito_blog_list_item_title);
            mId= (TextView) itemView.findViewById(R.id.manito_blog_list_item_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(mTitle,mId.getText(), Snackbar.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(mContext, ManitoBlogDetailActivity.class);
                    mIntent.putExtra(ManitoBlogDetailActivity.BLOG_ID,mId.getText());
                    mContext.startActivity(mIntent);
                }
            });
        }
    }
}
