package com.powernusa.andy.mycheese;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheeseListFragment extends Fragment {


    public CheeseListFragment() {
        // Required empty public constructor
    }

    //private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cheese_list,container,false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);

        setupRecyclerView(rv);

        return view;
    }

    private void setupRecyclerView(RecyclerView rv){
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> dataList = getCheeseStringData(Cheeses.sCheeseStrings,30);
        SimpleRVAdapter adapter = new SimpleRVAdapter(getActivity(),dataList);
        rv.setAdapter(adapter);

    }

    private List<String> getCheeseStringData(String[] array,int amount){
        List<String> list = new ArrayList<>();
        Random random = new Random();
        while(list.size() < amount){
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }
    /**
     *  ********************************************************************************************
     *
     *  RecyclerView.Adapter
     *
     *  ********************************************************************************************
     *
     */

    public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder>{
        private List<String> mCheeseDataList;
        private Context mContext;

        public SimpleRVAdapter(Context context,List<String> dataList){
            mContext = context;
            mCheeseDataList = dataList;
        }

        public void addData(Context context,List<String> dataList){
            mCheeseDataList.clear();
            if(dataList != null && !dataList.isEmpty()){
                mCheeseDataList.addAll(dataList);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cheese_list_item,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final String cheeseString = mCheeseDataList.get(position);

            holder.mCheeseText.setText(cheeseString);
            Picasso.with(mContext)
                    .load(Cheeses.getRandomCheeseDrawable())
                    .into(holder.mAvatarView);

            holder.mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext,"position clicked: " + position,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext,CheeseDetailActivity.class);
                    intent.putExtra(CheeseDetailActivity.EXTRA_CHEESE_NAME,cheeseString);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mCheeseDataList.size();
        }

        /**
         *  RecyclerView.ViewHolder
         */
        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView mAvatarView;
            private TextView mCheeseText;
            private View mRootView;

            public ViewHolder(View itemView) {
                super(itemView);
                mRootView = itemView;
                mAvatarView = (ImageView) itemView.findViewById(R.id.avatar);
                mCheeseText = (TextView) itemView.findViewById(R.id.cheese_text);
            }
        }
    }

}
