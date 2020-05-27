package com.example.knowtogo;

import android.content.Intent;
import android.media.MediaCodecInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    //UI elements
    Button beginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_view);

        //Get UI elements
        beginButton = (Button)findViewById(R.id.HomePageContinueButton);

        resetGlobals();

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(((Globals) getApplication()).getBtSocket() == null){
                    // Go to Paired Devices
                    Intent i = new Intent(HomePage.this, PairedDevices.class);
                    startActivity(i);
                }
                else{
                    // Go to Problem Types
                    Intent i = new Intent(HomePage.this, ProblemTypes.class);
                    startActivity(i);
                }
            }
        });

    }

    private void resetGlobals(){
        ((Globals) getApplication()).clearOperators();
    }
}