package com.example.intent123;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.net.Uri;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_intent), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClicked_intent(View view){
        Intent intent = null;

        if(view.getId() == R.id.btn_web) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        }

        if(view.getId() == R.id.btn_call) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-5678"));
        }
        //z 조절하면 크게 보기 작게 보기 가능
        if(view.getId() == R.id.btn_map) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.30, 127.2?z=5"));
        }

        if(view.getId() == R.id.btn_num) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contact/people"));
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}