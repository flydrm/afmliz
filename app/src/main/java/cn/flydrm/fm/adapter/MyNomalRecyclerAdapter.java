package cn.flydrm.fm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.flydrm.fm.R;
import cn.flydrm.fm.ui.ManitoBlogActivity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class MyNomalRecyclerAdapter extends RecyclerView.Adapter<MyNomalRecyclerAdapter.MyNomalViewHodler> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mTitles;

    private FragmentManager mFragmentManager;

    public MyNomalRecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mTitles = context.getResources().getStringArray(R.array.test); // 读取数组
    }

    @Override
    public MyNomalViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyNomalViewHodler(mLayoutInflater.inflate(R.layout.blog_gridview_item,parent,false)); // 通过自定义的viewholder 注入 包装的item
    }


    @Override
    public void onBindViewHolder(MyNomalViewHodler holder, int position) {
        holder.mTextView.setText(mTitles[position]);//
        holder.mImageView.setImageResource(R.drawable.blog_icon);
    }

    @Override
    public int getItemCount() {
        return (mTitles==null)?0:mTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    public class MyNomalViewHodler extends RecyclerView.ViewHolder{ // 实现自定义的viewholder
        TextView mTextView ;
        ImageView mImageView ;

        public MyNomalViewHodler(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.blog_gridview_item_name);
            mImageView = (ImageView) itemView.findViewById(R.id.blog_gridview_item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mTitles[getPosition()].equalsIgnoreCase("R")){
                        Toast.makeText(mContext,mTitles[getPosition()],Toast.LENGTH_SHORT).show();
                    }else{
                        Intent mIntent = new Intent(mContext, ManitoBlogActivity.class) ;
                        mIntent.putExtra("blog_name",mTitles[getPosition()]);
                        mContext.startActivity(mIntent);
                    }
                }
            });
        }
    }
}
