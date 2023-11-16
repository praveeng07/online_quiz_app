package com.example.onlinetestv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText usernameEditText;
    EditText passwordEditText;
    CheckBox rememberMeCheckBox;
    TextView forgotPasswordTextView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(getApplicationContext());
        loadQuizData();

        SharedPrefHandler sharedPrefHandler = new SharedPrefHandler(getApplicationContext());

        loginButton = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

        if(sharedPrefHandler.getRememberMe()){
            usernameEditText.setText(sharedPrefHandler.getLoginUsername());
            passwordEditText.setText(sharedPrefHandler.getLoginPassword());
            rememberMeCheckBox.setChecked(true);
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String pass = Objects.requireNonNull(passwordEditText.getText()).toString();

                if(sharedPrefHandler.getLoginUsername().equals(username) && sharedPrefHandler.getLoginPassword().equals(pass)){
                    Intent homeScreen = new Intent(getApplicationContext(),HomeScreen.class);
                    startActivity(homeScreen);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rememberMeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefHandler.setRememberMe(true);
                } else {
                    sharedPrefHandler.setRememberMe(false);
                }
            }
        });

    }

    public void loadQuizData(){



        // List of CSV files to process
        String[] csvFiles = {
                "communication_skills_questions.csv",
                "java_questions.csv",
                "music_quiz_questions.csv",
                "objective_c_questions.csv",
                "python_questions.csv",
                "quiz_questions.csv",
                "software_testing_questions.csv",
                "sql_questions.csv",
                "ui_design_questions.csv",
                "interview_questions.csv"
        };

        // Read data from each CSV file and insert into the database
        for (String csvFile : csvFiles) {
            try {
                List<String[]> csvData = CsvFileReader.readCsvFile(this, csvFile);

                if (csvData != null) {
                    // Process the CSV data
                    for (String[] data : csvData) {
                        String question = data[0].trim();
                        String optA = data[1].trim();
                        String optB = data[2].trim();
                        String optC = data[3].trim();
                        String optD = data[4].trim();
                        String answer = data[5].trim();
                        dbHelper.insertData(csvFile, question, optA, optB, optC, optD, answer);
                    }
                } else {
                    // Handle the case where reading CSV data fails
                    Log.e("CSV Data", "Failed to read CSV file");
                }
            } catch (Exception e) {
                Log.e("CSVReader", "Error reading CSV file: " + e.getMessage());
            }
        }
    }
}