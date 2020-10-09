package com.taboola.tn.api20tester.widget;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taboola.tn.api20tester.R;

public class TNItemView extends LinearLayout
//        implements View.OnClickListener
{

//        private View.OnClickListener mOnClickListener;
        private SimpleDraweeView mSimpleDraweeView;
        private TextView mTitle;
        private TextView mDescription;
        private TextView mBranding;

        public TNItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            LayoutInflater.from(context).inflate(R.layout.article_item_layout, this);
            mSimpleDraweeView = findViewById(R.id.rcv_video_photo);
            mTitle = findViewById(R.id.rcv_video_title);
            mDescription = findViewById(R.id.rcv_video_description);
            mBranding = findViewById(R.id.rcv_video_publisher);
//            setOnClickListener(this);

        }

//        @Override
//        public void onClick(View v) {
//            if (mOnClickListener != null) {
//                mOnClickListener.onClick(v);
//            }
//        }
//
//        public void setOnClickListener(View.OnClickListener onClickListener) {
//            mOnClickListener = onClickListener;
//        }

        public void loadArticleDetails (String imgURL, String title, String description, String branding) {
            mSimpleDraweeView.setImageURI(Uri.parse(imgURL));
            mTitle.setText(title);
            mDescription.setText(description);
            mBranding.setText(branding);
        }

        public boolean isTNItemVisible() {
            int isVisible = this.getVisibility();
            if (isVisible == View.VISIBLE) {
                return true;
            } else {
                return false;
            }
        }

}
