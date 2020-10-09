package com.taboola.tn.api20tester.utils;

import android.util.Log;

import com.taboola.tn.api20tester.model.ArticleBean;
import com.taboola.tn.api20tester.model.ArticleItemDetail;
import com.taboola.tn.api20tester.model.Placement;
import com.taboola.tn.api20tester.model.TNArticleBean;
import com.taboola.tn.api20tester.model.TNCategory;
import com.taboola.tn.api20tester.model.TNEvent;
import com.taboola.tn.api20tester.model.Thumnail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larryx on 3/7/2018.
 */
public class JsonParseTool {

    private static final String TAG = "JsonParseTool";

    public TNArticleBean parseTaboolaNewsJson(String jsonData){
        TNArticleBean mTNArticleEntity = new TNArticleBean();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject sessionObj = jsonObject.getJSONObject("user");
            String session = sessionObj.getString("session");
            Log.d(TAG,"SESSION = "+session);

            String externalVariant = jsonObject.getString("externalVariant");
            Log.d(TAG,"externalVariant = "+externalVariant);

            List<Placement> placements = new ArrayList<>();
            JSONArray jsonArray_placement = jsonObject.getJSONArray("placements");
            for (int i = 0; i < jsonArray_placement.length(); i++) {
                Placement placement = new Placement();
                JSONObject jsonObject1 = jsonArray_placement.getJSONObject(i);
                String placement_name = jsonObject1.getString("name");
                String placement_id = jsonObject1.getString("id");
                String placement_ui = jsonObject1.getString("ui");
                Log.d(TAG,"placement_ui = "+placement_ui);
                placement.setName(placement_name);
                placement.setId(placement_id);
                placement.setUi(placement_ui);
                JSONObject eventsObj = jsonObject1.getJSONObject("events");
                String available = eventsObj.getString("available");
                String visible = eventsObj.getString("visible");
                Log.d(TAG,"visible = "+visible);
                TNEvent tnEvent = new TNEvent();
                tnEvent.setAvailable(available);
                tnEvent.setVisible(visible);
                placement.setEvents(tnEvent);

                JSONArray jsonArray_list = jsonObject1.getJSONArray("list");
                List<ArticleItemDetail> articleItemDetails = new ArrayList<>();
                for (int n=0; n<jsonArray_list.length();n++){
                    JSONObject listObj = jsonArray_list.getJSONObject(n);
                    ArticleItemDetail articleInfo = new ArticleItemDetail();
                    String article_description;
                    try{
                        article_description = listObj.getString("description");
                    } catch (Exception e){
                        article_description = "No Description!";
                    }
                    String article_type = listObj.getString("type");
                    String article_name = listObj.getString("name");
                    String article_created = listObj.getString("created");
                    String article_branding;
                    try {
                        article_branding = listObj.getString("branding");
                    } catch (Exception e) {
                        article_branding = "No Branding";
                    }
                    Log.d(TAG,"article_branding = "+article_branding);
                    String article_duration = listObj.getString("duration");
                    String article_views = listObj.getString("views");
                    String article_isdconlyprovider ;
                    try{
                        article_isdconlyprovider = listObj.getString("is-dc-only-provider");
                    } catch (Exception e){
                        article_isdconlyprovider = "Don't have this field.";
                    }
                    String article_isdc ;
                    try{
                        article_isdc = listObj.getString("is-dc");
                    } catch (Exception e) {
                        article_isdc = "Don't have this field.";
                    }
                    String article_id = listObj.getString("id");
                    String article_origin = listObj.getString("origin");
                    String article_url = listObj.getString("url");
                    Log.d(TAG,"article_url = "+article_url);
                    List<Thumnail> thumbnails = new ArrayList<>();
                    JSONArray jsonArray_imgs = listObj.getJSONArray("thumbnail");
                    for (int m=0; m<jsonArray_imgs.length();m++){
                        JSONObject imgObj = jsonArray_imgs.getJSONObject(m);
                        String imgUrl = imgObj.getString("url");
                        Thumnail thumbnail = new Thumnail();
                        thumbnail.setUrl(imgUrl);
                        thumbnails.add(thumbnail);
                    }
                    List<TNCategory> categories = new ArrayList<>();
                    JSONArray jsonArray_category = listObj.getJSONArray("categories");
                    for (int m=0; m<jsonArray_category.length();m++){
                        TNCategory tnCategory = new TNCategory();
                        tnCategory.setCategory(jsonArray_category.get(m).toString());
                        categories.add(tnCategory);
                    }

                    articleInfo.setDescription(article_description);
                    articleInfo.setType(article_type);
                    articleInfo.setType(article_type);
                    articleInfo.setName(article_name);
                    articleInfo.setCreated(article_created);
                    articleInfo.setBranding(article_branding);
                    articleInfo.setDuration(article_duration);
                    articleInfo.setViews(article_views);
                    articleInfo.setIs_dc_only_provider(article_isdconlyprovider);
                    articleInfo.setIs_dc(article_isdc);
                    articleInfo.setId(article_id);
                    articleInfo.setOrigin(article_origin);
                    articleInfo.setUrl(article_url);
                    articleInfo.setThumbnails(thumbnails);
                    articleInfo.setCategories(categories);

                    articleItemDetails.add(articleInfo);
                }
                placement.setArticles(articleItemDetails);
                placements.add(placement);
            }
            Log.d(TAG,"placements size = "+placements.size());
            mTNArticleEntity.setSession(session);
            mTNArticleEntity.setExternalVariant(externalVariant);
            mTNArticleEntity.setPlacements(placements);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return mTNArticleEntity;
    }

    public List<ArticleBean> parseSCJson(String jsonData){
        List<ArticleBean> mArticles = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.getInt("totalItems") == 0) {
                return mArticles;
            }
            JSONArray jsonArray = jsonObject.getJSONArray("content");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                ArticleBean articleInfo = new ArticleBean();

                String mArticleID = jsonObject1.getString("contentId");
                String mArticleTitle = jsonObject1.getString("title");
                String mArticleURL  = jsonObject1.getString("actionUri");
                String mPubDate = jsonObject1.getString("promotedText");
                String mPublisherName = jsonObject1.getString("contentSourceDisplay");
                String mCPLogoURL = jsonObject1.getString("imageUrl");

                articleInfo.setArticleURL(mArticleURL);
                articleInfo.setPublishedDate(mPubDate);
                articleInfo.setTitle(mArticleTitle);
                articleInfo.setUniqueKey(mArticleID);
                articleInfo.setPublisherName(mPublisherName);
                articleInfo.setCpLogoURL(mCPLogoURL);
                List<String> additionalImages = new ArrayList<>();
                articleInfo.setAdditionalImages(additionalImages);

                mArticles.add(articleInfo);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return mArticles;
    }
}