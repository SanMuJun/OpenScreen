package com.san.openscreen;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void button(View v){

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action", "play");
        startService(intent);

        Intent intent1 = new Intent(this, Main3Activity.class);
        startActivity(intent1);

        Toast.makeText(MainActivity.this, "已经进入备忘录", Toast.LENGTH_SHORT).show();
        finish();
    }
}
