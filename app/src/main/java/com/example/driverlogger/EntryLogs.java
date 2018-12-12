package com.example.driverlogger;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//public class EntryLogs extends AppCompatActivity {

    public class EntryLogs extends Fragment{
    ArrayList<String> StoreEntries;
    private DBHelper newHelper;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.entries);


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.entries,container,false);


//        Intent i = getIntent();
//        final String vehicle = i.getStringExtra("vehicle");
            final String vehicle = getArguments().getString("vehicle");
//        Button b = (Button) findViewById(R.id.entryButton);
        Button b = (Button) view.findViewById(R.id.entryButton);
        b.setText("Return to " + vehicle);

//        newHelper = new DBHelper(EntryLogs.this);
            newHelper = new DBHelper(getActivity());
        Cursor cursor = newHelper.getData(vehicle);

        if (cursor.getCount() != 0) {

            StoreEntries = new ArrayList<String>();
//            Toast.makeText(this, "loading from database", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "loading from database", Toast.LENGTH_SHORT).show();
            Log.e("cursor", String.valueOf(cursor.getCount()));

            if (cursor.moveToFirst()) {
                do {
                    String fin = "";
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String rego = cursor.getString(cursor.getColumnIndex("rego"));
                    String startTime = "Start: " + cursor.getString(cursor.getColumnIndex("start"));
                    String firstBrkTime = "1st break: " + cursor.getString(cursor.getColumnIndex("first_brk"));
                    String secBrkTime = "2nd break: " + cursor.getString(cursor.getColumnIndex("sec_brk"));
                    String endTime = "End: " + cursor.getString(cursor.getColumnIndex("end"));

                    fin += name + rego + startTime + firstBrkTime + secBrkTime + endTime;

                    StoreEntries.add(fin);

                } while (cursor.moveToNext());
            }
            cursor.close();


            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.row, R.id.log_entry, StoreEntries);
            ListView listView = (ListView) view.findViewById(R.id.lv_name);
            listView.setAdapter(adapter);
        } else {
//            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vehicle.equalsIgnoreCase("car")) {
                    Fragment newFragment = new Car();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                    FrameLayout fl = (FrameLayout) view.findViewById(R.id.relative);
//                    fl.removeAllViews();

                    transaction.replace(R.id.fragment1, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
                else if (vehicle.equalsIgnoreCase("truck_5t")) {
                    Fragment newFragment = new Truck_5T();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                    FrameLayout fl = (FrameLayout) view.findViewById(R.id.relative);
//                    fl.removeAllViews();

                    transaction.replace(R.id.fragment1, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
                else if (vehicle.equalsIgnoreCase("truck_10t")) {
                    Fragment newFragment = new Truck_10T();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                    FrameLayout fl = (FrameLayout) view.findViewById(R.id.relative);
//                    fl.removeAllViews();

                    transaction.replace(R.id.fragment1, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else if (vehicle.equalsIgnoreCase("tipper")) {
                    Fragment newFragment = new Tipper();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                    FrameLayout fl = (FrameLayout) view.findViewById(R.id.relative);
//                    fl.removeAllViews();

                    transaction.replace(R.id.fragment1, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    Fragment newFragment = new Articulated();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                    FrameLayout fl = (FrameLayout) view.findViewById(R.id.relative);
//                    fl.removeAllViews();

                    transaction.replace(R.id.fragment1, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            }
        });
        return view;
    }
}
