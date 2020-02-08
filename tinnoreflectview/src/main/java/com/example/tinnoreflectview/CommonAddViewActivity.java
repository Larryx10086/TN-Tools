package com.example.tinnoreflectview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CommonAddViewActivity extends AppCompatActivity {

    //Declare an class member instance TaboolaWidget
//    private TaboolaWidget taboolaWidget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coomon_add_view);

//        LinearLayout layout = findViewById(R.id.layout1);
//        layout.addView(new SmartLeftViewFragment());

        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.layout1,new SmartLeftViewFragment())
                .commit();




////In your Activity's OnCreate() or Fragment's OnCreateView(), create a new TaboolaWidget instance
//        taboolaWidget = new TaboolaWidget(this);
//
////Assign LayoutParams to TaboolaWidget
//        taboolaWidget.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//
////Add TaboolaWidget to your layout (This example assumes your parent layout is a FrameLayout with an arbitrary id parent_layout)
//        LinearLayout layout = findViewById(R.id.layout1);
//        layout.addView(taboolaWidget);
//
////Set the following parameters in your TaboolaWidget instance, before calling fetchContent()
//        taboolaWidget.setPublisher("tinno-network")
//                .setMode("editorial-stream")
//                .setPlacement("Editorial Thumbnails Finite")
//                .setPageUrl("https://tinno.minusone.france")
//                .setPageType("home")
//                .setTargetType("mix");
//                //Optional: sets an internal ID for the current page
////                .setPageId("<your-internal-id-for-the-current-page>");
//
//// Activate `useOnlineTemplate` for improved loading time
//        HashMap<String, String> optionalExtraProperties = new HashMap<>();
//        optionalExtraProperties.put("useOnlineTemplate", "true");
//        taboolaWidget.setExtraProperties(optionalExtraProperties);
//
////fetch the contebt
//        taboolaWidget.fetchContent();


    }

}
