package com.hangman.hangmanfinal;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class Game extends AppCompatActivity {



    private String hiddenWord;
    private String[] wordArrayList;
    private TypedArray hangmanImages;
    private ArrayList<String> usedLetters = new ArrayList<>();
    private int triesLeft = 10;
    private char[] wordHider;
    private String guess;

    private Random random = new Random();

    private Messages toast = new Messages(this);


    private ImageView hangmanImageView;
    private EditText enterLetterEditText;
    private TextView guessesLeftTextView;
    private TextView currentWordTextView;
    private TextView usedTextView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man_game);
        initialize();
        toolbar();




        wordArrayList = getResources().getStringArray(R.array.wordArrayList);
        hangmanImages = getResources().obtainTypedArray(R.array.hangmanImages);


        randomizer();


        hideTheWord();
        currentWordTextView.setText(String.valueOf(wordHider));

    }


    public void onClick(View v) {
        initialize();
        boolean isCorrect = handleGuess(guess);
        currentWordTextView.setText(String.valueOf(wordHider));
        usedTextView.setText(String.valueOf(usedLetters)); //Get the UsedLetters-Array

        if (!isCorrect) {
            guessesLeftTextView.setText(triesLeft + " guesses left");
            hangmanImageView.setImageResource(hangmanImages.getResourceId(triesLeft, -1));
            toast.wrongGuess();
        }
        lostOrWon();
        enterLetterEditText.setText("");
    }



    public void toolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }


    public void initialize() {
        enterLetterEditText = (EditText) findViewById(R.id.guessWordEditText);
        hangmanImageView = (ImageView) findViewById(R.id.hangmanImageView);
        guessesLeftTextView = (TextView) findViewById(R.id.guessesLeftTextView);
        currentWordTextView = (TextView) findViewById(R.id.currentWordTextView);
        usedTextView = (TextView) findViewById(R.id.usedTextView);
        guess = enterLetterEditText.getText().toString().toLowerCase();
    }



    public void randomizer() {
        int randomInt = random.nextInt(wordArrayList.length);
        hiddenWord = wordArrayList[randomInt];
    }


    public void hideTheWord() {
        wordHider = new char[hiddenWord.length()];
        for (int i = 0; i < hiddenWord.length(); i++) {
            wordHider[i] = '-';
        }
    }


    public boolean handleUsedLetters(String guess){
        for (int i = 0; i < usedLetters.size(); i++) {
            if (usedLetters.get(i).equals(guess)){
                toast.usedLetter();
                return true;
            }
        } return false;
    }


    public boolean handleGuess(String guess) {
        boolean isCorrect = false;
        if (!isTooLong(guess)){
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (guess.charAt(0) == hiddenWord.charAt(i))  {
                    isCorrect = true;
                    wordHider[i] = guess.charAt(0);
                    toast.correctGuess();
                }
            } if(!isCorrect){
                if (!handleUsedLetters(guess)){
                    usedLetters.add(guess);
                    triesLeft--;
                }
            }
        }  return isCorrect;
    }


    public boolean isTooLong(String guess){
        if (guess.length() > 1){
            toast.oneLetter();
            return true;
        } return false;
    }


    public boolean hasWon() {
        int numOfLine = hiddenWord.length();
        for(int i = 0 ; i < hiddenWord.length() ; i++){
            if (wordHider[i] != '-'){
                numOfLine--;
            }
        } return numOfLine == 0;
    }

    public boolean hasLost() {
        return triesLeft == 0;
    }


    public void lostOrWon(){
        Intent lostWon = new Intent(this, Results.class);
        lostWon.putExtra("correctWord", hiddenWord);
        lostWon.putExtra("tries", triesLeft);

        if (hasWon()) {
            lostWon.putExtra("win", true);
            startActivity(lostWon);
        } else if (hasLost()) {
            lostWon.putExtra("win", false);
            startActivity(lostWon);
        }
    }}

