package com.example.driverlogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class driver_Entry extends AppCompatActivity {

    String head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car);
        //Intent i = getIntent();
        //head = i.getStringExtra("heading");
        TextView text = (TextView) findViewById(R.id.vehicleEntry);
        text.setText(head);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    public void showLog(){

    }

    public void saveLog(){

    }

    public void previous(View view){
        if(head.equalsIgnoreCase("car")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","ARTICULATED");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("t5")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","CAR");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("t10")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","T5");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("tipper")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","T10");
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","TIPPER");
            startActivity(intent);
        }
    }

    public void next(View view){
        if(head.equalsIgnoreCase("car")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","T5");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("t5")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","T10");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("t10")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","TIPPER");
            startActivity(intent);
        }

        else if(head.equalsIgnoreCase("tipper")){
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","ARTICULATED");
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, driver_Entry.class);
            intent.putExtra("heading","CAR");
            startActivity(intent);
        }
    }

    public void home(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void startTime(View view){
        TextView startT = (TextView) findViewById(R.id.startTime);
        Button b1 = (Button) findViewById(R.id.startButton);
        b1.setVisibility(View.INVISIBLE);
        String currentDateTimeString = "Start: " + DateFormat.getDateTimeInstance().format(new Date());
        startT.setText(currentDateTimeString);

        Button b2 = (Button) findViewById(R.id.firstBreakButton);
        b2.setVisibility(View.VISIBLE);
    }

    public void firstBreak(View view){

        TextView firstB = (TextView) findViewById(R.id.firstBreak);
        Button b1 = (Button) findViewById(R.id.firstBreakButton);
        b1.setVisibility(View.INVISIBLE);
        String currentDateTimeString = "1st Break: " + DateFormat.getDateTimeInstance().format(new Date());
        firstB.setText(currentDateTimeString);

        Button b2 = (Button) findViewById(R.id.secondBreakButton);
        b2.setVisibility(View.VISIBLE);
    }

    public void secondBreak(View view){
        TextView secondB = (TextView) findViewById(R.id.secondBreak);

        Button b1 = (Button) findViewById(R.id.secondBreakButton);
        b1.setVisibility(View.INVISIBLE);
        String currentDateTimeString = "2nd Break: " + DateFormat.getDateTimeInstance().format(new Date());
        secondB.setText(currentDateTimeString);

        Button b2 = (Button) findViewById(R.id.endButton);
        b2.setVisibility(View.VISIBLE);
    }

    public void endTime(View view){
        TextView endT = (TextView) findViewById(R.id.endTime);
        Button b1 = (Button) findViewById(R.id.endButton);
        b1.setVisibility(View.INVISIBLE);
        String currentDateTimeString = "End: " + DateFormat.getDateTimeInstance().format(new Date());
        endT.setText(currentDateTimeString);
    }
}
