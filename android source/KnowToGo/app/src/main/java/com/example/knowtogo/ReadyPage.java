package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReadyPage extends AppCompatActivity {

    //UI elements
    Button beginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_page_view);

        //Get UI elements
        beginButton = (Button)findViewById(R.id.ReadyPageBeginButton);

                beginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent(ReadyPage.this, SessionUI.class);
                        startActivity(i);
                    }
                });
    }
}
