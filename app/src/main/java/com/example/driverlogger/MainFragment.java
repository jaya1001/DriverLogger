package com.example.driverlogger;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        super.onCreate(savedInstanceState);
        Button b1 = view.findViewById(R.id.car);
        Button b2 = view.findViewById(R.id.truck5);
        Button b3 = view.findViewById(R.id.truck10);
        Button b4 = view.findViewById(R.id.tipper);
        Button b5 = view.findViewById(R.id.art);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment newFragment = new Car();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack

                transaction.remove(getFragmentManager().findFragmentById(R.id.fragment1));
                transaction.replace(R.id.fragment1, newFragment);

                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Truck_5T();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Truck_10T();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Tipper();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Articulated();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return view;
    }
}
