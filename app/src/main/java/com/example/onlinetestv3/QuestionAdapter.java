package com.example.onlinetestv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;



public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private List<Question> questionList;
    private Context context;
    SharedPrefHandler sharedPrefHandler;

    public QuestionAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
        this.sharedPrefHandler = new SharedPrefHandler(context.getApplicationContext());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questionList.get(holder.getAdapterPosition());
        holder.txtQuestion.setText(question.getQuestion());
        holder.rdOption1.setText(question.getOptA());
        holder.rdOption2.setText(question.getOptB());
        holder.rdOption3.setText(question.getOptC());
        holder.rdOption4.setText(question.getOptD());

        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = holder.rdOptions.getCheckedRadioButtonId();
                if(selected==-1){
                    Toast.makeText(context.getApplicationContext(), "Select option",Toast.LENGTH_SHORT).show();
                } else{
                    RadioButton selectedRadioButton = holder.rdOptions.findViewById(selected);
                    String data = selectedRadioButton.getText().toString();
                    sharedPrefHandler.saveQuizAnswer(holder.getAdapterPosition(), questionList.get(holder.getAdapterPosition()).getAnswer().contains(data));
                    holder.btnSubmit.setEnabled(false);
                    holder.btnSubmit.setText("Submitted");
//                    holder.btnSubmit.setVisibility(View.INVISIBLE);
                    for (int i = 0; i <  holder.rdOptions.getChildCount(); i++) {
                        holder.rdOptions.getChildAt(i).setEnabled(false);
                    }
                }
            }
        });

        holder.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnSubmit.setEnabled(true);
                holder.btnSubmit.setText("Submit");
                for (int i = 0; i <  holder.rdOptions.getChildCount(); i++) {
                    holder.rdOptions.getChildAt(i).setEnabled(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion;
        RadioButton rdOption1, rdOption2, rdOption3, rdOption4;
        RadioGroup rdOptions;
        Button btnSubmit,btnReset;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            rdOptions = itemView.findViewById(R.id.rdOptions);
            rdOption1 = itemView.findViewById(R.id.rdOption1);
            rdOption2 = itemView.findViewById(R.id.rdOption2);
            rdOption3 = itemView.findViewById(R.id.rdOption3);
            rdOption4 = itemView.findViewById(R.id.rdOption4);
            btnSubmit = itemView.findViewById(R.id.btnSubmit);
            btnReset = itemView.findViewById(R.id.btnReset);
        }
    }
}
