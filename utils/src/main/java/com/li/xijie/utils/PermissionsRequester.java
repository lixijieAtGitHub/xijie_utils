package com.li.xijie.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by lixijie1990@163.com on 2016/12/6.
 */

public class PermissionsRequester {
    /**
     * 要请求权限的Activity
     */
    private Activity mActivity;
    /**
     * 保存权限的列表
     */
    private ArrayList<String> permissionsList;
    public static final int REQUESTCODE = 111;
    ;

    private PermissionsRequester(Activity mActivity, ArrayList<String> permissionsList) {
        this.mActivity = mActivity;
        this.permissionsList = permissionsList;

        ArrayList<String> markedList = new ArrayList<>();
        for (String permission : this.permissionsList) {
            if (ContextCompat.checkSelfPermission(this.mActivity, permission) == PackageManager.PERMISSION_GRANTED) {//权限已经授予
                //从权限集合移除
                //放入标记集合
                markedList.add(permission);
            } else {
                //do nothing...
            }
        }
        //移除所有已经授予的权限
        this.permissionsList.removeAll(markedList);

        /**   PERMISSIONS_READ_PHONE_STATE = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_PHONE_STATE);
         if (PERMISSIONS_READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
         permissionsList.add(Manifest.permission.READ_PHONE_STATE);
         }
         PERMISSIONS_ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION);
         if (PERMISSIONS_ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
         permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
         }
         PERMISSIONS_WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
         if (PERMISSIONS_WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
         permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
         }
         PERMISSIONS_CAMERA = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA);
         if (PERMISSIONS_CAMERA != PackageManager.PERMISSION_GRANTED) {
         permissionsList.add(Manifest.permission.CAMERA);
         }
         PERMISSIONS_RECORD_AUDIO = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.RECORD_AUDIO);
         if (PERMISSIONS_RECORD_AUDIO != PackageManager.PERMISSION_GRANTED) {
         permissionsList.add(Manifest.permission.RECORD_AUDIO);
         }
         */
    }

    public static class Builder {
        private final Activity mActivity;
        private final ArrayList<String> permissionsList;

        public Builder(Activity activity) {
            this.mActivity = activity;
            this.permissionsList = new ArrayList<>();
        }

        public Builder add(String permission) {
            permissionsList.add(permission);
            return this;
        }

        public PermissionsRequester build() {
            return new PermissionsRequester(mActivity, permissionsList);
        }
    }

    /**
     * 请求权限
     */
    public void requestPermissions() {
        if (permissionsList.size() > 0) {//如果有要请求的权限
            String[] contents = new String[permissionsList.size()];
            permissionsList.toArray(contents);
            //请求权限
            ActivityCompat.requestPermissions(mActivity, contents, REQUESTCODE);
        }


        /**      if (PERMISSIONS_READ_PHONE_STATE == PackageManager.PERMISSION_GRANTED
         && PERMISSIONS_ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
         && PERMISSIONS_WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED
         && PERMISSIONS_CAMERA == PackageManager.PERMISSION_GRANTED
         && PERMISSIONS_RECORD_AUDIO == PackageManager.PERMISSION_GRANTED
         ) {

         init();
         } else {
         String[] contents = new String[permissionsList.size()];
         permissionsList.toArray(contents);
         ActivityCompat.requestPermissions(this, contents, 111);
         }
         */
    }
}
