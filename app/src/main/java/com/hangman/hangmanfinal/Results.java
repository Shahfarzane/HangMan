package com.hangman.hangmanfinal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    private boolean wonLost;
    private TextView finalResultTextView;
    private TextView leftTextView;
    private TextView currnetTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        actionBar();
        textView();

        if (wonLost)
            finalResultTextView.setText("Congrats , YOU WON THE BIG PRIZE !");
        else
            finalResultTextView.setText("Better Luck Next Time .");
    }

    public void textView(){
        Intent i = getIntent();
        wonLost = i.getBooleanExtra("win", true);
        String word = i.getStringExtra("correctWord");
        int tries = i.getIntExtra("tries", 0);
        finalResultTextView = (TextView) findViewById(R.id.finalResult);
        leftTextView = (TextView) findViewById(R.id.triesLeftTextView);
        currnetTextView = (TextView) findViewById(R.id.current_wordTextView);

        leftTextView.setText("Tries Left: " + i.getIntExtra("tries", 0));
        currnetTextView.setText("Magic Word: " + i.getStringExtra("correctWord"));
    }


    public void actionBar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void goToMainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuStartGame:
                gameStart();
                break;
            case R.id.menuInfo:
                aboutActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void gameStart(){
        Intent game = new Intent(this, Game.class);
        startActivity(game);
    }

    public void aboutActivity(){
        Intent about = new Intent(this, About.class);
        startActivity(about);
    }
}
