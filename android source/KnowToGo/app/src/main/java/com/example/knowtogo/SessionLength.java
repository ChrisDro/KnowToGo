package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SessionLength extends AppCompatActivity {

    //UI elements
    RadioButton length10Radio;
    RadioButton length15Radio;
    RadioButton length20Radio;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_length_view);

        //Get UI elements
        length10Radio = (RadioButton)findViewById(R.id.Length10Button);
        length15Radio = (RadioButton)findViewById(R.id.Length15Button);
        length20Radio = (RadioButton)findViewById(R.id.Length20Button);
        continueButton = (Button)findViewById(R.id.SessionLengthContinueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(length10Radio.isChecked() || length15Radio.isChecked() || length20Radio.isChecked()){
                    if(length10Radio.isChecked()){
                        ((Globals) getApplication()).setSessionLength(10);
                    }
                    else if(length15Radio.isChecked()){
                        ((Globals) getApplication()).setSessionLength(15);
                    }
                    else{
                        ((Globals) getApplication()).setSessionLength(20);
                    }
                    // Make an intent to start next activity.
                    Intent i = new Intent(SessionLength.this, ReadyPage.class);
                    startActivity(i);
                }
            }
        });
    }
}
