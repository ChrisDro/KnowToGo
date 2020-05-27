package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Difficulty extends AppCompatActivity {

    //UI elements
    RadioButton easyRadio;
    RadioButton medRadio;
    RadioButton hardRadio;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_view);

        //Get UI elements
        easyRadio = (RadioButton)findViewById(R.id.EasyRadioButton);
        medRadio = (RadioButton)findViewById(R.id.MediumRadioButton);
        hardRadio = (RadioButton)findViewById(R.id.HardRadioButton);
        continueButton = (Button)findViewById(R.id.DifficultyContinueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(easyRadio.isChecked() || medRadio.isChecked() || hardRadio.isChecked()){
                    if(easyRadio.isChecked()){
                        ((Globals) getApplication()).setDifficulty(Globals.EASY_MODE);
                    }
                    else if(medRadio.isChecked()){
                        ((Globals) getApplication()).setDifficulty(Globals.MEDIUM_MODE);
                    }
                    else{
                        ((Globals) getApplication()).setDifficulty(Globals.HARD_MODE);
                    }
                    // Make an intent to start next activity.
                    Intent i = new Intent(Difficulty.this, SessionLength.class);
                    startActivity(i);
                }
            }
        });
    }
}
