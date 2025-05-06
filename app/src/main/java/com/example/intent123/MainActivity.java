package com.example.intent123;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText j_emailEditText, j_passwordEditText;
    private TextView j_statusText;
    private Button j_loginButton;
    private Button j_btn_main;
    String tag = "LoginCheck";
    ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        j_emailEditText = findViewById(R.id.editTextID);
        j_passwordEditText = findViewById(R.id.editTextPassword);
        j_statusText = findViewById(R.id.textView4);
        j_loginButton = findViewById(R.id.buttonLogin);

        j_loginButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                //아이디와 비밀번호 입력값 가져오기
                String email = j_emailEditText.getText().toString();
                String password = j_passwordEditText.getText().toString();

                Log.d(tag, "입력 아이디: " + email);
                Log.d(tag, "입력 비밀번호: " + password);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("ID", email);
                intent.putExtra("Password", password);
                //런처 실행
                launcher.launch(intent);
            }
        });

        //launcher

        launcher =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == MainActivity.RESULT_OK) {
                Intent data = result.getData();
                j_statusText.setText(data.getStringExtra("status"));
            }
            j_btn_main = findViewById(R.id.btn_main);
            //j_status 로그인 성공
            if("로그인성공".equals(j_statusText.getText().toString())){
                j_btn_main.setVisibility(View.VISIBLE);
            }else{
                j_btn_main.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onClicked_main(View view) {
        Intent intent = new Intent(MainActivity.this, IntentActivity.class);
        startActivity(intent);
    }
}