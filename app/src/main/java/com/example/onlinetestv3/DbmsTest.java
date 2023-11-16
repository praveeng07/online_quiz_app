package com.example.onlinetestv3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class DbmsTest extends Fragment {
    DatabaseHelper dbHelper;
    Context context;
    Button btnShowResult;
    SharedPrefHandler sharedPrefHandler;
    public DbmsTest() {
        // Required empty public constructor
    }
    public DbmsTest(Context context) {
        // Required empty public constructor
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_java_test, container, false);

        List<Question> questionList = dbHelper.getQuestionsByCategory("sql_questions.csv");
        QuestionAdapter adapter = new QuestionAdapter(context, questionList);
        RecyclerView recyclerView =  v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        sharedPrefHandler = new SharedPrefHandler(v.getContext());
        btnShowResult = v.findViewById(R.id.btnShowResult);
        btnShowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreActivity = new Intent(v.getContext(), ScoreCardActivity.class);
                scoreActivity.putExtra("correctAns", sharedPrefHandler.getCorrectAnswersCount());
                scoreActivity.putExtra("wrongAns", sharedPrefHandler.getWrongAnswersCount());
                startActivity(scoreActivity);
            }
        });

        return v;


    }
}