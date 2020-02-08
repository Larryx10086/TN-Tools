package com.example.tinnolauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.taboola.android.INTEGRATION_TYPE;
import com.taboola.android.TaboolaWidget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent("com.example.tinnoreflectview",null);
//        PackageManager pm = getPackageManager();
//        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent,0);
//        Log.d("launcher","List<ResolveInfo> = " + resolveInfos.size());
//        ActivityInfo actInfo = resolveInfos.get(0).activityInfo;
//
//        String packageName = actInfo.packageName;
//        String apkPath = actInfo.applicationInfo.sourceDir;
//        String dexOutputDir = getApplicationInfo().dataDir;
//
//        String libPath = actInfo.applicationInfo.nativeLibraryDir;
//        DexClassLoader classLoader = new DexClassLoader(apkPath,dexOutputDir,libPath,
//                this.getClass().getClassLoader());

        try{
            Context c = this.createPackageContext("com.example.tinnoreflectview", Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            Class<?> cls = c.getClassLoader().loadClass("com.example.tinnoreflectview.SmartLeftViewFragment");
            Object obj1 = cls.newInstance();
            Class[] param1 = new Class[1];
            param1[0] = Context.class;

            Method method1 = cls.getDeclaredMethod("initTaboolaWidget",param1);
            method1.setAccessible(true);
            Fragment fragment = (Fragment)method1.invoke(obj1,MainActivity.this);

            getSupportFragmentManager().beginTransaction().add(R.id.layout_container,fragment).commit();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

    }
}
