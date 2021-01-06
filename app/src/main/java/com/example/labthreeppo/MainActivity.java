package com.example.labthreeppo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView iv_dice_p1, iv_dice_p2, iv_lives_p1, iv_lives_p2 ;
    TextView tv_player1, tv_player2;
    Random r;
    int livesP1, livesP2;
    int rolledP1, rolledP2;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        iv_dice_p1 = findViewById(R.id.iv_dice_p1);
        iv_dice_p2 = findViewById(R.id.iv_dice_p2);

        iv_lives_p1 = findViewById(R.id.iv_lives_p1);
        iv_lives_p2 = findViewById(R.id.iv_lives_p2);

        tv_player1 = findViewById(R.id.tv_player1);
        tv_player2 = findViewById(R.id.tv_player2);

        tv_player1.setText("PLAYER 1 ROLL");
        tv_player2.setText("PLAYER 2 ROLL");

        livesP1 = 6;
        livesP2 = 6;
        setDiceImage(livesP1, iv_lives_p1);
        setDiceImage(livesP2, iv_lives_p2);


        iv_dice_p1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rolledP1 = r.nextInt(6) + 1;
                setDiceImage(rolledP1, iv_dice_p1);
                iv_dice_p1.startAnimation(animation);

                if(rolledP2 != 0) {
                    tv_player1.setText("PLAYER 1 ROLL");
                    tv_player2.setText("PLAYER 2 ROLL");

                    if(rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, iv_lives_p2);

                        Toast.makeText(MainActivity.this, "Player 1 WIN", Toast.LENGTH_SHORT).show();
                    }
                    if(rolledP2 > rolledP1){
                        livesP1--;
                        setDiceImage(livesP1, iv_lives_p1);
                        Toast.makeText(MainActivity.this, "Player 2 WIN", Toast.LENGTH_SHORT).show();
                    }
                    if(rolledP1 == rolledP2) {
                        Toast.makeText(MainActivity.this, "Draw ", Toast.LENGTH_SHORT).show();
                    }

                    rolledP2 = 0;
                    rolledP1 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);
                    checkEndGame();

                } else {
                    tv_player1.setText("PLAYER 1 ROLLED!");
                    iv_dice_p1.setEnabled(false);
                }
            }
        });

        iv_dice_p2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rolledP2 = r.nextInt(6) + 1;
                setDiceImage(rolledP2, iv_dice_p2);
                iv_dice_p2.startAnimation(animation);
                if(rolledP1 != 0) {
                    tv_player1.setText("PLAYER 1 ROLL");
                    tv_player1.setText("PLAYER 2 ROLL");
                    if(rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, iv_lives_p2);
                        Toast.makeText(MainActivity.this, "Player 1 WIN", Toast.LENGTH_SHORT).show();
                    }
                    if(rolledP2 > rolledP1){
                        livesP1--;
                        setDiceImage(livesP1, iv_lives_p1);
                        Toast.makeText(MainActivity.this, "Player 2 WIN", Toast.LENGTH_SHORT).show();
                    }

                    if(rolledP1 == rolledP2) {
                        Toast.makeText(MainActivity.this, "Draw ", Toast.LENGTH_SHORT).show();
                    }

                    rolledP1 = 0;
                    rolledP2 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);
                    checkEndGame();

                } else {
                    tv_player2.setText("PLAYER 2 ROLLED!");
                    iv_dice_p2.setEnabled(false);
                }
            }
        });

    }
    private  void setDiceImage(int dice, ImageView image) {
        switch (dice) {
            case 1:
                image.setImageResource(R.drawable.dice1);

                break;
            case 2:
                image.setImageResource(R.drawable.dice2);

                break;
            case 3:
                image.setImageResource(R.drawable.dice3);

                break;
            case 4:
                image.setImageResource(R.drawable.dice4);

                break;
            case 5:
                image.setImageResource(R.drawable.dice5);

                break;
            case 6:
                image.setImageResource(R.drawable.dice6);

                break;
            default:
                image.setImageResource(R.drawable.krest);

        }
    }
    private void  checkEndGame() {
        if (livesP1 == 0 || livesP2 == 0) {
            iv_dice_p1.setEnabled(false);
            iv_dice_p2.setEnabled(false);

            String text = "";
            if(livesP1 != 0) {
                text = "Winner Player 1!";
            }
            if(livesP2 != 0) {
                text = "Winner Player 2!";
            }
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage(text);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}