package com.san.openscreen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
        Log.e("MyService", "MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("onBind", "onBind");
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand", "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate", "onCreate");


        ScreenListener l = new ScreenListener(MyService.this);
        l.begin(new ScreenListener.ScreenStateListener() {


            //屏幕打开时
            @Override
            public void onUserPresent() {
                Log.e("onUserPresent", "onUserPresent");


                Intent intent = new Intent(MyService.this, Main3Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            //屏幕亮着时
            @Override
            public void onScreenOn() {
                Log.e("onScreenOn", "onScreenOn");

//                Intent intent = new Intent(MyService.this, Main2Activity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }


            //屏幕关闭时
            @Override
            public void onScreenOff() {
                Log.e("onScreenOff", "onScreenOff");


//                Intent intent = new Intent(MyService.this, Main4Activity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }

        });




    }


}
