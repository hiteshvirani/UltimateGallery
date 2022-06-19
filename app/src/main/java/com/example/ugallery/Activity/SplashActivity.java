package com.example.ugallery.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.ugallery.MainActivity;
import com.example.ugallery.R;

public class SplashActivity extends AppCompatActivity {
    private String[] PERMISSION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        changeStatusBarColor();
        PERMISSION = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.SET_WALLPAPER,
                Manifest.permission.RECORD_AUDIO,
        };
        if (!hasPermission(SplashActivity.this, PERMISSION)) {
            ActivityCompat.requestPermissions(SplashActivity.this, PERMISSION, 1);
            Thread thread = new Thread() {

                public void run() {
                    try {
                        sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            };
            thread.start();
        } else {
            Thread thread = new Thread() {

                public void run() {
                    try {
                        sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            };
            thread.start();
        }
    }
    private boolean hasPermission(Context context, String... PERMISSION) {
        if (context != null && PERMISSION != null) {
            for (String permission : PERMISSION) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int ii=0;
        if (requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if(grantResults[2] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if(grantResults[3] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if (grantResults[4] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if(grantResults[5] == PackageManager.PERMISSION_GRANTED) ii = 0;
            else ii = 1;
            if (ii==0){ Intent i = new Intent(SplashActivity.this, MainActivity.class);startActivity(i);finish();}
            else finishAndRemoveTask();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.color_sky_blue));

    }
}