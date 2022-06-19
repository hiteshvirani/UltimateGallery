package com.example.ugallery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.example.ugallery.R;

public class VaultSecondActivity extends AppCompatActivity {
    public static final String TAG = "PinLockView";
    public String pinlock;
    ProgressDialog dialog;
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d(TAG, "Pin complete: " + pin);
            if (pinlock.equals(pin)){
                dialog.show();
                Thread thread = new Thread(){

                    public void run(){
                        try {
                            sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            dialog.dismiss();
                            Intent intent = new Intent(VaultSecondActivity.this,CompleteActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                thread.start();
            }
            else {
                Intent intent = new Intent(VaultSecondActivity.this, VaultActivity.class);
                startActivity(intent);
            }
        }
        @Override
        public void onEmpty() {
            Log.d(TAG, "Pin empty");
        }
        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };
    public void insrting(String a){
        pinlock = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_second);
        Context context;
        dialog = new ProgressDialog(this);
        dialog.setMessage("PIN Creating....");
        Intent intent = getIntent();
        String pinlock = intent.getExtras().getString("pinlock");
        insrting(pinlock);
        Toolbar toolbar  = findViewById(R.id.vaultsecond_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.actionbar_background);
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));
        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }
}