package org.techtown.finalprojectv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondScreen extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN = 101;
    TextView resultView;
    Button codeBtn, placeBtn, backBtn;
    EditText typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);

        typeText = findViewById(R.id.editText);
        resultView = findViewById(R.id.resultText);
        codeBtn = findViewById(R.id.codeButton);
        placeBtn = findViewById(R.id.placeButton);

        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("38501");

            }
        });

        placeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText("Daegu");

            }
        });


        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAIN);
                intent.putExtra("message", "result message is OK!");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });





    }
}
