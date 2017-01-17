package com.example.andttapos.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red
    private int activePlayer = 1;
    private int[][] board = new int[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean checkPlayerIsWin(int playerNumber){

        for (int i = 0; i < board.length; i++) {
            if(board[i][0]==playerNumber && board[i][1]==playerNumber && board[i][2]==playerNumber){
                return  true;
            }

            if(board[0][i]==playerNumber && board[1][i]==playerNumber && board[2][i]==playerNumber){
                return  true;
            }
        }

        if(board[0][0]==playerNumber && board[1][1]==playerNumber && board[2][2]==playerNumber){
            return  true;
        }

        if(board[0][2]==playerNumber && board[1][1]==playerNumber && board[2][0]==playerNumber){
            return  true;
        }

        return  false;


    }

    public void dropIn(View view){


        if(checkPlayerIsWin(1) || checkPlayerIsWin(2)){
            if(checkPlayerIsWin(1)){
                Toast.makeText(this,"player 1 already win no need to play",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"player 2 already win no need to play",Toast.LENGTH_LONG).show();
            }
        }else {
            ImageView counter = (ImageView) view;

            int position = Integer.parseInt(counter.getTag().toString());

            if(board[position/3][position%3]==0) {

                counter.setTranslationY(-1000f);
                board[position/3][position%3] = activePlayer;
                if (activePlayer == 1) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 2;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 1;
                }


                counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            }else {
                Toast.makeText(MainActivity.this,"already value this column, Try another",Toast.LENGTH_LONG).show();
            }
        }



    }



}
