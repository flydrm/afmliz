package cn.flydrm.fm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.flydrm.fm.R;
import cn.flydrm.fm.ui.ManitoBlogActivity;

/**
 * Created by Administrator on 2015/11/13.
 */
public class ManitoRecyclerAdapter extends RecyclerView.Adapter<ManitoRecyclerAdapter.MyViewHolder> {
    private String[] mNames;
    private LayoutInflater mInflater;
    private Context mContext;

    public ManitoRecyclerAdapter(Context content) {
        mInflater = LayoutInflater.from(content);
        mContext = content;
        mNames = content.getResources().getStringArray(R.array.blogs);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mvView = mInflater.inflate(R.layout.blog_gridview_item,null);

        return new MyViewHolder(mvView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(R.drawable.blog_icon);
        holder.mTextView.setText(mNames[position]);
    }

    @Override
    public int getItemCount() {
        return (mNames.length==0)?0:mNames.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.blog_gridview_item_image);
            mTextView = (TextView) itemView.findViewById(R.id.blog_gridview_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, ManitoBlogActivity.class);
                    mIntent.putExtra(ManitoBlogActivity.AUTHOR_NAME,mNames[getPosition()]);
                    mContext.startActivity(mIntent);
                }
            });
        }




    }
}
