package com.celltick.apac.news.util;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.UUID;

import static com.celltick.apac.news.app.StarNewsApp.getContext;

/**
 * Created by Larryx on 11/12/2018.
 */

public class UUIDGenerator {

    public static String getUUID32() {
//        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String uuid = UUID.randomUUID().toString().toLowerCase();
        return uuid;
    }

    public static String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Service.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "getdeviceidfailed_00000";
        }
        return tm.getDeviceId();
    }

}
