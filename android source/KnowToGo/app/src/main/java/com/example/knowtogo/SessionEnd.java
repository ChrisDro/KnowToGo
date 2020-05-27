package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SessionEnd extends AppCompatActivity {

    //UI elements
    Button returnButton;
    TextView finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_end_view);

        //Get UI elements
        returnButton = (Button)findViewById(R.id.SessionEndReturnButton);
        finalScore = (TextView)findViewById(R.id.FinalScoreTextView);

        finalScore.setText(((Globals) getApplication()).getFinalScore() + "/" + ((Globals) getApplication()).getSessionLength());

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(SessionEnd.this, HomePage.class);
                startActivity(i);
            }
        });
    }
}
