package com.example.onlinetestv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreCardActivity extends AppCompatActivity {
    private TextView txtCorrectAns;
    private TextView txtWrongAns;
    private TextView txtAccuracyAns;
    private Button btnBackHome;
    private SharedPrefHandler sharedPrefHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        sharedPrefHandler = new SharedPrefHandler(getApplicationContext());
        txtCorrectAns = findViewById(R.id.txtCorrectAns);
        txtWrongAns = findViewById(R.id.txtWrongAns);
        txtAccuracyAns = findViewById(R.id.txtAccuracyAns);
        btnBackHome = findViewById(R.id.btnBackHome);

        int correctAns = getIntent().getIntExtra("correctAns",-1);
        int wrongAns = getIntent().getIntExtra("wrongAns",-1);
        int totalAns = correctAns + wrongAns;
        txtCorrectAns.setText("Correct answers : "+correctAns);
        txtWrongAns.setText("Wrong answers : "+wrongAns);

        double percentage = ((double) correctAns/totalAns)*100;
        txtAccuracyAns.setText("Accuracy : "+ percentage + "%");

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                finish();
            }
        });
    }
}