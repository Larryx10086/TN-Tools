package com.example.tinnoreflectview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.tinnoreflectview.R;
import com.taboola.android.TaboolaWidget;

import java.util.HashMap;

public class SmartLeftViewFragment extends Fragment {

    private TaboolaWidget taboolaWidget;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.samrtleft_fragment_view, container,false);
        //In your Activity's OnCreate() or Fragment's OnCreateView(), create a new TaboolaWidget instance
        initTaboolaWidget(getActivity());

        return mView;
    }

    private TaboolaWidget initTaboolaWidget(Context ctx){
        taboolaWidget = new TaboolaWidget(ctx);

        //Assign LayoutParams to TaboolaWidget
        taboolaWidget.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        //Add TaboolaWidget to your layout (This example assumes your parent layout is a FrameLayout with an arbitrary id parent_layout)
        LinearLayout layout = mView.findViewById(R.id.fragment_smartleft);
        layout.addView(taboolaWidget);

        //Set the following parameters in your TaboolaWidget instance, before calling fetchContent()
        taboolaWidget.setPublisher("tinno-network")
                .setMode("editorial-stream")
                .setPlacement("Editorial Thumbnails Finite")
                .setPageUrl("https://tinno.minusone.france")
                .setPageType("home")
                .setTargetType("mix");
        //Optional: sets an internal ID for the current page
//                .setPageId("<your-internal-id-for-the-current-page>");

        // Activate `useOnlineTemplate` for improved loading time
        HashMap<String, String> optionalExtraProperties = new HashMap<>();
        optionalExtraProperties.put("useOnlineTemplate", "true");
        taboolaWidget.setExtraProperties(optionalExtraProperties);

        //fetch the contebt
        taboolaWidget.fetchContent();

        return taboolaWidget;
    }

}
