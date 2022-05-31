package com.example.msg_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String AUTH_KEY = "key=your-server-key";
    private TextView mytxtView;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mytxtView = findViewById(R.id.txt);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tmp = "";
            for (String key : bundle.keySet()) {
                Object value = bundle.keySet();
                tmp += key + ": " + value + "\n\n";
            }
            mytxtView.setText(tmp);
        }
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener((task) -> {
            if (!task.isSuccessful()) {
                token = task.getException().getMessage();
                Log.i("FCM Fail", task.getException().getMessage());
            } else {
                token = task.getResult();
                Log.i("FCM TOKEN", token);
            }
        });
    }
}