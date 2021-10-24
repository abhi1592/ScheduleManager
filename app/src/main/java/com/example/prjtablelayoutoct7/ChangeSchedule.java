package com.example.prjtablelayoutoct7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeSchedule extends AppCompatActivity implements View.OnClickListener {
EditText edDescription;
Button btnReturn;
String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_schedule);
        intialize();
    }

    private void intialize() {
        edDescription = findViewById(R.id.edDescription);
        btnReturn = findViewById(R.id.btnReturn);
        data = getIntent().getStringExtra("schedule");
        edDescription.setText(data);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       // In this class, we decide if we return a result or not
        String newData = edDescription.getText().toString();
        Intent intent = new Intent();
        if (data.equals(newData)){
            setResult(RESULT_CANCELED,intent);
        }
        else{
            intent.putExtra("new_schedule",newData);
            setResult(RESULT_OK,intent);

        }


        finish();
    }
}