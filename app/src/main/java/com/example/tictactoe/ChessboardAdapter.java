package com.example.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.Fragments.GameFragment;

import java.util.ArrayList;

public class ChessboardAdapter extends RecyclerView.Adapter<ChessboardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Bitmap> arrBms, arrStrokes;
    private Bitmap bmX, bmO, draw;
    private Animation anim_x_o, anim_stroke, anim_win;
    private String winCharacter = "o";
    public ChessboardAdapter(Context context, ArrayList<Bitmap> arrBms) {
        this.context = context;
        this.arrBms = arrBms;
        bmO = BitmapFactory.decodeResource(context.getResources(), R.drawable.o);
        bmX = BitmapFactory.decodeResource(context.getResources(), R.drawable.x);
        draw = BitmapFactory.decodeResource(context.getResources(), R.drawable.draw);
        arrStrokes = new ArrayList<>();
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke1));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke2));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke3));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke4));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke5));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke6));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke7));
        arrStrokes.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.stroke8));
        anim_stroke = AnimationUtils.loadAnimation(context, R.anim.anim_stroke);
        GameFragment.img_stroke.setAnimation(anim_stroke);
        anim_win = AnimationUtils.loadAnimation(context, R.anim.anim_win);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img_cell_chessboard.setImageBitmap(arrBms.get(position));
        anim_x_o = AnimationUtils.loadAnimation(context, R.anim.anim_x_o);
        holder.img_cell_chessboard.setAnimation(anim_x_o);
        holder.img_cell_chessboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrBms.get(position)==null && !checkWin()){
                    if (GameFragment.turnO){
                        arrBms.set(position, bmO);
                        GameFragment.turnO = false;
                        GameFragment.txt_turn.setText("turn of X");
                    } else {
                        arrBms.set(position, bmX);
                        GameFragment.turnO = true;
                        GameFragment.txt_turn.setText("turn of O");
                    }
                    holder.img_cell_chessboard.setAnimation(anim_x_o);
                    if (checkWin()){
                        win();
                    }
                    notifyItemChanged(position);
                }
            }
        });
        if (!checkWin()){
            checkDraw();
        }
    }

    private void checkDraw(){
        int count = 0;
        for (int i = 0; i < arrBms.size(); i++){
            if (arrBms.get(i)!=null){
                count++;
            }
        }
        if (count == 9){
            GameFragment.rl_win.setVisibility(View.VISIBLE);
            GameFragment.rl_win.startAnimation(anim_win);
            GameFragment.img_win.setImageBitmap(draw);
            GameFragment.txt_win.setText("draw");
        }
    }

    private void win() {
        GameFragment.img_stroke.startAnimation(anim_stroke);
        GameFragment.rl_win.startAnimation(anim_win);
        GameFragment.rl_win.setVisibility(View.VISIBLE);
        GameFragment.rl_win.startAnimation(anim_win);
        if (winCharacter.equals("o")){
            GameFragment.img_win.setImageBitmap(bmO);
            MainActivity.scoreO++;
            GameFragment.txt_win_o.setText("O: "+MainActivity.scoreO);
        } else {
            GameFragment.img_win.setImageBitmap(bmX);
            MainActivity.scoreX++;
            GameFragment.txt_win_x.setText("X: "+MainActivity.scoreX);
        }
        GameFragment.txt_win.setText("win");
    }

    private boolean checkWin() {
        if (arrBms.get(0)==arrBms.get(3) && arrBms.get(3)==arrBms.get(6) && arrBms.get(0)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(2));
            checkWinCharacter(0);
            return true;
        } else if (arrBms.get(1)==arrBms.get(4) && arrBms.get(4)==arrBms.get(7) && arrBms.get(1)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(3));
            checkWinCharacter(1);
            return true;
        } else if (arrBms.get(2)==arrBms.get(5) && arrBms.get(5)==arrBms.get(8) && arrBms.get(2)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(4));
            checkWinCharacter(2);
            return true;
        } else if (arrBms.get(0)==arrBms.get(1) && arrBms.get(1)==arrBms.get(2) && arrBms.get(0)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(5));
            checkWinCharacter(0);
            return true;
        } else if (arrBms.get(3)==arrBms.get(4) && arrBms.get(4)==arrBms.get(5) && arrBms.get(3)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(6));
            checkWinCharacter(3);
            return true;
        } else if (arrBms.get(6)==arrBms.get(7) && arrBms.get(7)==arrBms.get(8) && arrBms.get(6)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(7));
            checkWinCharacter(6);
            return true;
        } else if (arrBms.get(0)==arrBms.get(4) && arrBms.get(4)==arrBms.get(8) && arrBms.get(0)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(1));
            checkWinCharacter(0);
            return true;
        } else if (arrBms.get(2)==arrBms.get(4) && arrBms.get(4)==arrBms.get(6) && arrBms.get(2)!=null){
            GameFragment.img_stroke.setImageBitmap(arrStrokes.get(0));
            checkWinCharacter(2);
            return true;
        }
        return false;
    }

    private void checkWinCharacter(int i) {
        if (arrBms.get(i)==bmO){
            winCharacter = "o";
        } else {
            winCharacter = "x";
        }
    }

    @Override
    public int getItemCount() {
        return arrBms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_cell_chessboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cell_chessboard = itemView.findViewById(R.id.img_cell_chessboard);
        }
    }

    public ArrayList<Bitmap> getArrBms() {
        return arrBms;
    }

    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;
    }
}
