package com.example.labthreeppo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView iv_dice_p1, iv_dice_p2, iv_lives_p1, iv_lives_p2 ;
    TextView tv_player1, tv_player2;
    Random r;
    int livesP1, livesP2;
    int rolledP1, rolledP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

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
                rolledP1 = r.nextInt(7) + 1;
                setDiceImage(rolledP1, iv_dice_p1);

                if(rolledP2 != 0) {
                    tv_player1.setText("PLAYER 1 ROLL");
                    tv_player2.setText("PLAYER 2 ROLL");

                    if(rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, iv_lives_p2);
                    } else {
                        livesP1--;
                        setDiceImage(livesP1, iv_lives_p1);
                    }
                    rolledP2 = 0;
                    rolledP1 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);

                } else {
                    tv_player1.setText("PLAYER 1 ROLLED!");
                    iv_dice_p1.setEnabled(false);
                }
            }
        });

        iv_dice_p2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rolledP2 = r.nextInt(7) + 1;
                setDiceImage(rolledP1, iv_dice_p1);
                if(rolledP1 != 0) {
                    tv_player1.setText("PLAYER 1 ROLL");
                    tv_player1.setText("PLAYER 2 ROLL");
                    if(rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, iv_lives_p2);
                    } else {
                        livesP1--;
                        setDiceImage(livesP1, iv_lives_p1);
                    }
                    rolledP1 = 0;
                    rolledP2 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);

                } else {
                    tv_player1.setText("PLAYER 2 ROLLED!");
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
}