package com.example.tictactoe.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tictactoe.ChessboardAdapter;
import com.example.tictactoe.R;

import java.util.ArrayList;

public class GameFragment extends Fragment {
    private RecyclerView rv_chessboard;
    private ChessboardAdapter chessboardAdapter;
    public static boolean turnO = true;
    public static TextView txt_turn, txt_win_x, txt_win_o, txt_win;
    private Button btn_reset, btn_again, btn_home;
    public static ImageView img_stroke, img_win;
    public static RelativeLayout rl_win;
    public static String TAG = GameFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        rv_chessboard = view.findViewById(R.id.rv_chessboard);
        txt_turn = view.findViewById(R.id.txt_turn);
        btn_reset = view.findViewById(R.id.btn_reset);
        btn_again = view.findViewById(R.id.btn_again);
        btn_home = view.findViewById(R.id.btn_home);
        img_stroke = view.findViewById(R.id.img_stroke);
        rl_win = view.findViewById(R.id.rl_win);
        txt_win_x = view.findViewById(R.id.txt_win_x);
        txt_win_o = view.findViewById(R.id.txt_win_o);
        txt_win = view.findViewById(R.id.txt_win);
        img_win = view.findViewById(R.id.img_win);

        ArrayList<Bitmap> arrBms = new ArrayList<>();
        for (int i=0; i < 9; i++){
            arrBms.add(null);
        }
        chessboardAdapter = new ChessboardAdapter(getContext(), arrBms);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rv_chessboard.setLayoutManager(layoutManager);
        rv_chessboard.setAdapter(chessboardAdapter);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_win.setVisibility(View.INVISIBLE);
                reset();
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void reset() {
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        for (int i=0; i < 9; i++){
            arrBms.add(null);
        }
        chessboardAdapter.setArrBms(arrBms);
        chessboardAdapter.notifyDataSetChanged();
        turnO = true;
        txt_turn.setText("turn of O");
        img_stroke.setImageBitmap(null);
    }
}