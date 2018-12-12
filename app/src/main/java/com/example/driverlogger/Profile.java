package com.example.driverlogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private Button save;
    private Button cancel;
    private DBHelper newHelper;
    private EditText user;
    private EditText password;
    private EditText repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_entry);
        Intent i = getIntent();

        newHelper = new DBHelper(this);

        save = findViewById(R.id.save_profile);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);
                repeat = (EditText) findViewById(R.id.repeatPass);

                String username = user.getText().toString();
                String passwrd = password.getText().toString();

                if(username.equals("") || passwrd.equals("")){
                    Toast.makeText(Profile.this, "Fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(passwrd.compareTo(repeat.getText().toString()) == 0)
                {
                    boolean chk = newHelper.insertDriver(username, passwrd);
                    if(chk)
                        Toast.makeText(Profile.this, "Data is saved", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(Profile.this, MainActivity.class);
                    startActivity(it);
                }
                else
                    Toast.makeText(Profile.this, "Confirm the correct password", Toast.LENGTH_SHORT).show();

            }
        });
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Profile.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send:
                newHelper = new DBHelper(getApplicationContext());
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure? This will delete all entries")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                newHelper.deleteAll("entries");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();

                alert.show();
                return true;

            case R.id.save:

                return true;

            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                return true;
        }

        return true;
    }
}
