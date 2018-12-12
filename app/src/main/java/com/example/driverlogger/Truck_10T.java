package com.example.driverlogger;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Truck_10T extends Fragment {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button save;
    private Button show;
    private Button prev;
    private Button next;
    private Button home;
    private EditText name;
    private EditText rego;
    private TextView stime;
    private TextView setime;
    private TextView etime;
    private TextView ftime;
    private DBHelper newHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.truck_10t, container, false);
        Intent i = getActivity().getIntent();

        name = (EditText) view.findViewById(R.id.driverName);
        rego = (EditText) view.findViewById(R.id.rego);
        b1 = view.findViewById(R.id.startButton);
        b2 = view.findViewById(R.id.firstBreakButton);
        b3 = view.findViewById(R.id.secondBreakButton);
        b4 = view.findViewById(R.id.endButton);
        save = view.findViewById(R.id.save);
        show = view.findViewById(R.id.show);
        prev = view.findViewById(R.id.prev);
        next = view.findViewById(R.id.next);
        home = view.findViewById(R.id.home);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                stime = view.findViewById(R.id.startTime);
                b1.setVisibility(View.INVISIBLE);
                stime.setText(currentDateandTime);
                stime.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                ftime = view.findViewById(R.id.firstBreak);
                b2.setVisibility(View.INVISIBLE);
                ftime.setText(currentDateandTime);
                ftime.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                setime = view.findViewById(R.id.secondBreak);
                b3.setVisibility(View.INVISIBLE);
                setime.setText(currentDateandTime);
                setime.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                etime = view.findViewById(R.id.endTime);
                b4.setVisibility(View.INVISIBLE);
                etime.setText(currentDateandTime);
                etime.setVisibility(View.VISIBLE);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etime == null || name.getText().toString().equals("")|| rego.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Entry not saved as not all data entered. Complete all entries and try again ", Toast.LENGTH_SHORT).show();

                else
                {
                    String sname = name.getText().toString() + " ";
                    String srego = rego.getText().toString() + " ";
                    String startTime = stime.getText().toString() + " ";
                    String firstBrk = ftime.getText().toString() + " ";
                    String secBrk = setime.getText().toString() + " ";
                    String endTime = etime.getText().toString() + " ";
                    newHelper = new DBHelper(getActivity());
                    newHelper.insertLog("truck_10t", sname, srego, startTime, firstBrk, secBrk, endTime);
                    Toast.makeText(getActivity(), "data saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), EntryLogs.class);
//                i.putExtra("vehicle","truck_10t" );
//                startActivity(i);

                Bundle bundle = new Bundle();
                bundle.putString("vehicle","truck_10t");

                Fragment fragment= new EntryLogs();

                fragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment1, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentManager fm = getFragmentManager();
//                if (fm.getBackStackEntryCount() > 0) {
//                    Log.i("MainActivity", "popping backstack");
//                    fm.popBackStack();
//                } else {
//                    Log.i("MainActivity", "nothing on backstack, calling super");
//                    Intent it = new Intent(getActivity(), MainActivity.class);
//                    startActivity(it);
//                }
                Fragment fragment= new Truck_5T();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment1, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getActivity(), MainActivity.class);
                startActivity(it);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment= new Tipper();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment1, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_items, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.send:
                newHelper = new DBHelper(getActivity());
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(getContext());
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
                startActivity(new Intent(getActivity(), Profile.class));
                return true;
        }
        return true;

    }

}
