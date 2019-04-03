package com.hangman.hangmanfinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar();

        Button startGameBtn = (Button) findViewById(R.id.startButton);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startGameBtn = new Intent(MainActivity.this, Game.class);
                startActivity(startGameBtn);
            }
        });

        Button aboutButton = (Button) findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutButton = new Intent(MainActivity.this, About.class);
                startActivity(aboutButton);

            }
        });
    }



    public void toolbar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }


    public void gameStart(){
        Intent game = new Intent(this, Game.class);
        startActivity(game);
    }


    public void aboutActivity(){
        Intent about = new Intent(this, About.class);
        startActivity(about);
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
}

