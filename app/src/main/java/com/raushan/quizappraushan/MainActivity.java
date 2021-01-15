package com.raushan.quizappraushan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private int mUserScore;


    private QuizModel[] questionCollection = new QuizModel[]{


            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, false),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, false),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),

    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtQuestion = findViewById(R.id.txtQuestion);

        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        btnTrue = findViewById(R.id.btnTrue);


        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);


        btnTrue.setOnClickListener(v -> {

            evaluateUserAnswer(true);
            changeQuestionOnButtonClick();


        });
        btnWrong = findViewById(R.id.btnWrong);
        // btnWrong.setOnClickListener(myClickListener);

        btnWrong.setOnClickListener(v -> {
            evaluateUserAnswer(false);
            changeQuestionOnButtonClick();

        });
    }

    private void changeQuestionOnButtonClick() {

        //modular operator always gives remainder
        //20%15=5, 25%20=5
        //50%60=50,20%23=20


        mQuestionIndex = (mQuestionIndex + 1) % 10;

        if (mQuestionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(MainActivity.this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The Quiz is finished");
            quizAlert.setMessage("Your Score is :" + mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore + " ");

    }

    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestion = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestion == userGuess) {
            Toast toastObject12 = Toast.makeText(getApplicationContext(), R.string.correct_text, Toast.LENGTH_SHORT);
            toastObject12.show();
            mUserScore = mUserScore + 1;

        } else {
            Toast toastObject13 = Toast.makeText(getApplicationContext(), R.string.incorrect_text, Toast.LENGTH_SHORT);
            toastObject13.show();
        }
    }
}