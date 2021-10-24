package com.example.prjtablelayoutoct7;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import model.Schedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView[] listOfTextViews;
    int widgets[] = {R.id.tvMondayMorning, R.id.tvMondayAfterEvening,R.id.tvTuesdayMornAfter, R.id.tvTuesdayEvenig};

    Schedule[] listOfSchedule;
    TextView clickedTv;

    //1: Declare an object from the class ActivityResultLauncher
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        listOfSchedule = new Schedule[4];
        listOfSchedule[0] = new Schedule(1, "Android", Color.RED);
        listOfSchedule[1] = new Schedule(1, "Work", Color.BLUE);
        listOfSchedule[2] = new Schedule(1, "Training");
        listOfSchedule[3] = new Schedule(1, "SQL", Color.MAGENTA);


        listOfTextViews = new TextView[4];
        for (int i = 0; i < 4; i++) {
            listOfTextViews[i] = findViewById(widgets[i]);
           listOfTextViews[i].setText(listOfSchedule[i].toString());
           listOfTextViews[i].setTextColor(listOfSchedule[i].getColor());
           listOfTextViews[i].setOnClickListener(this);

        }

        //--2 Register the activity result

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            // In this method we will process the result
                    // sent by Change Schedule activity

                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
    String newData = result.getData().getStringExtra("new_schedule");
    clickedTv.setText(newData);
}else{
    if(result.getResultCode() == RESULT_CANCELED){
        noResult();
          }
}
                    }
                }
        );
    }

    private void noResult() {
        Toast.makeText(MainActivity.this,"No change in the Schedule", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {

//        TextView tv = (TextView) view;
//        Toast.makeText(this,"You clicked: "+tv.getText().toString(), Toast.LENGTH_LONG).show();

    //--3 Launch the second acivity
        clickedTv = (TextView)view;
        Intent intent = new Intent(this,ChangeSchedule.class );
        intent.putExtra("schedule",clickedTv.getText().toString());
        activityResultLauncher.launch(intent);

    }
}