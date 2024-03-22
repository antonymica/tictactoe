package com.example.tictactoe.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;

public class StartFragment extends Fragment {

    private Button btn_start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        btn_start = view.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MainActivity.scoreO = 0;
                MainActivity.scoreX = 0;
                transaction.addToBackStack(GameFragment.TAG);
                transaction.replace(R.id.main_frame, new GameFragment());
                transaction.commit();
            }
        });
        return view;
    }
}