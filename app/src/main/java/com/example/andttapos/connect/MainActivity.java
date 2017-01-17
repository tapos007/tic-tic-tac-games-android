package com.example.andttapos.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red
    private int activePlayer = 1;
    private int[][] board = new int[3][3];
    private int gameStatus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void resetGame(View view) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        activePlayer = 1;
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);


        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        gameStatus = 1;
    }

    public boolean checkPlayerIsWin(int playerNumber) {

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == playerNumber && board[i][1] == playerNumber && board[i][2] == playerNumber) {
                return true;
            }

            if (board[0][i] == playerNumber && board[1][i] == playerNumber && board[2][i] == playerNumber) {
                return true;
            }
        }

        if (board[0][0] == playerNumber && board[1][1] == playerNumber && board[2][2] == playerNumber) {
            return true;
        }

        if (board[0][2] == playerNumber && board[1][1] == playerNumber && board[2][0] == playerNumber) {
            return true;
        }

        return false;


    }

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int position = Integer.parseInt(counter.getTag().toString());

        if (board[position / 3][position % 3] == 0 && gameStatus == 1) {

            counter.setTranslationY(-1000f);
            board[position / 3][position % 3] = activePlayer;
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            }


            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
        } else {
            Toast.makeText(MainActivity.this, "already value this column, Try another", Toast.LENGTH_LONG).show();
        }
        String winner = null;
        if (checkPlayerIsWin(1) || checkPlayerIsWin(2)) {
            if (checkPlayerIsWin(1)) {
                winner = "player 1";
                Toast.makeText(this, "player 1 already win no need to play", Toast.LENGTH_LONG).show();
            } else {
                winner = "player 2";
                Toast.makeText(this, "player 2 already win no need to play", Toast.LENGTH_LONG).show();
            }

            LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
            winnerMessage.setText(winner + " win the game!!!");
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            winnerMessage.startAnimation(anim);
            playAgain.setVisibility(View.VISIBLE);
            gameStatus = 0;
        }


    }


}
