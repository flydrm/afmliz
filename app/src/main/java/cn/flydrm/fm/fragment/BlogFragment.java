package cn.flydrm.fm.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.flydrm.fm.R;
import cn.flydrm.fm.adapter.ManitoRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlogFragment.OnBlogFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlogFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BLOG_CATEGOOY = "category"; //博客类型（属于哪个博客下面的）
    private static final String BLOG_ID = "bid";//博客的Id

    private String mCategory;
    private String mBid;

    private OnBlogFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category Parameter 1.
     * @param bid Parameter 2.
     * @return A new instance of fragment BlogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlogFragment newInstance(String category, String bid) { //查询具体的博客
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        args.putString(BLOG_CATEGOOY, category);
        args.putString(BLOG_ID, bid);
        fragment.setArguments(args);
        return fragment;
    }

    public static BlogFragment newInstance() { //默认
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        args.putString(BLOG_CATEGOOY, "");
        args.putString(BLOG_ID, "");
        fragment.setArguments(args);
        return fragment;
    }

    public BlogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(BLOG_CATEGOOY);
            mBid = getArguments().getString(BLOG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_blog,null);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // listview 效果
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.HORIZONTAL)); //瀑布流
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));  //gridview 效果

        recyclerView.setAdapter(new ManitoRecyclerAdapter(getActivity()));

        return recyclerView;
    }

    // TODO: Rename method, update argument and h  ook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onBlogFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBlogFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBlogFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onBlogFragmentInteraction(Uri uri);
    }

}
