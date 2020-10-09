package com.taboola.tn.api20tester.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taboola.tn.api20tester.R;
import com.taboola.tn.api20tester.model.Placement;
import com.taboola.tn.api20tester.widget.SmartSimpleDraweeView;
import com.taboola.tn.api20tester.model.TNArticleBean;
import com.taboola.tn.api20tester.utils.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter for TN
 *
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = FeedAdapter.class.getSimpleName();

    private Context context;
    private View view;
    private OnItemClickLitener mOnItemClickLitener;
    private LayoutInflater mLayoutInflater;
    private TNArticleBean mTNArticleBean;
    private List<Placement> placements;


    public FeedAdapter(Context context, List<Placement> placements) {
        this.context = context;
        if (placements == null) {
            placements = new ArrayList<>();
        } else {
            this.placements = placements;
        }
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mLayoutInflater.inflate(R.layout.article_item_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder){

            String img_url = placements.get(position).getArticles().get(0).getThumbnails().get(0).getUrl();
            Log.d(TAG,"img_url = " +img_url);
            String title = placements.get(position).getArticles().get(0).getName();
            Log.d(TAG,"title = " +title);
            String pub_date = placements.get(position).getArticles().get(0).getCreated();
            Log.d(TAG,"pub_date = " +pub_date);
            String pub_name = placements.get(position).getArticles().get(0).getBranding();
            Log.d(TAG,"pub_name = " +pub_name);
            String article_description = placements.get(position).getArticles().get(0).getDescription();
            Log.d(TAG,"article_description = " +article_description);


            MyViewHolder newHolder = (MyViewHolder) viewHolder;
            newHolder.rcvArticlePhoto.setImageURI(Uri.parse(img_url));
            newHolder.rcvArticleTitle.setText(title);
            newHolder.rcvArticleDate.setText(pub_date);
            newHolder.rcvArticlePublisher.setText(pub_name);
            newHolder.rcvArticleDescription.setText(article_description);

            if (mOnItemClickLitener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(pos);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return placements.size();
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView rcvArticlePhoto;
        TextView rcvArticleTitle;
        TextView rcvArticlePublisher;
        TextView rcvArticleDate;
        TextView rcvArticleDescription;

        public MyViewHolder(View itemView) {
            super(itemView);

            rcvArticlePhoto = (SimpleDraweeView)itemView.findViewById(R.id.rcv_video_photo);
            rcvArticleTitle = (TextView)itemView.findViewById(R.id.rcv_video_title);
            rcvArticlePublisher = (TextView)itemView.findViewById(R.id.rcv_video_publisher);
            rcvArticleDate = (TextView)itemView.findViewById(R.id.rcv_video_date);
            rcvArticleDescription = (TextView)itemView.findViewById(R.id.rcv_video_description);
        }
    }

    public void addItems(List<Placement> items){
        placements.addAll(0,items);
        notifyDataSetChanged();
    }
}