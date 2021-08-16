package com.titactoe.omar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
Button start,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        start= findViewById(R.id.startBtn);
        quit= findViewById(R.id.quitBtn);
        start.setOnClickListener(
                view -> {
                    startActivity(new Intent(WelcomeActivity.this, GameActivity.class));
                }
        );
        quit.setOnClickListener(
                view -> {
                    AlertDialog.Builder l= new AlertDialog.Builder(this);
                    l.setMessage("Are you sure you want to quit?");
                    l.setPositiveButton("Yes",(dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        finish();
                    });
                    l.setNegativeButton("No",(dialogInterface, i) -> {
                       dialogInterface.dismiss();
                    });
                    l.show();
                }
        );
    }
}