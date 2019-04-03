package com.hangman.hangmanfinal;


import android.content.Context;
import android.widget.Toast;


public class Messages {

    private Context context;

    public Messages(Context context){

        this.context = context;
    }


    public void wrongGuess() {
        Toast.makeText(context, "You guessed wrong ", Toast.LENGTH_SHORT).show();
    }


    public void correctGuess() {
        Toast.makeText(context, "You guessed right", Toast.LENGTH_SHORT).show();
    }




    public void usedLetter() {
        Toast.makeText(context, "You used this letter before.", Toast.LENGTH_SHORT).show();
    }

    public void oneLetter() {
        Toast.makeText(context, "One letter at the time", Toast.LENGTH_SHORT).show();
    }


}