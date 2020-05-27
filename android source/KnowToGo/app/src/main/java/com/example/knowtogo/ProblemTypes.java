package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.MappedByteBuffer;

public class ProblemTypes extends AppCompatActivity {

    //UI elements
    CheckBox addBox;
    CheckBox subBox;
    CheckBox mulBox;
    CheckBox divBox;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Render problem types view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_types_view);

        //Get UI elements
        addBox = (CheckBox)findViewById(R.id.AdditionCheckBox);
        subBox = (CheckBox)findViewById(R.id.SubtractionCheckbox);
        mulBox = (CheckBox)findViewById(R.id.MultiplicationCheckBox);
        divBox = (CheckBox)findViewById(R.id.DivisionCheckBox);
        continueButton = (Button)findViewById(R.id.ProblemTypesContinueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(addBox.isChecked() || subBox.isChecked() || mulBox.isChecked() || divBox.isChecked()){
                    if(addBox.isChecked())
                        ((Globals) getApplication()).addOperator(" + ");
                    if(subBox.isChecked())
                        ((Globals) getApplication()).addOperator(" - ");
                    if(mulBox.isChecked())
                        ((Globals) getApplication()).addOperator(" * ");
                    if(divBox.isChecked())
                        ((Globals) getApplication()).addOperator(" / ");

                    // Make an intent to start next activity.
                    Intent i = new Intent(ProblemTypes.this, Difficulty.class);
                    startActivity(i);
                }
            }
        });
    }
}
