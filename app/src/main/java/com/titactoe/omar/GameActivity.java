package com.titactoe.omar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private boolean isGameAtTheEnd;
    private String PlayerMode = "person";
    private MyMatrixAlgorithm arrangemetOfCells;
    private int personsTurn;
    private TextView winnerDisplay;
    private int currentCount;
    private GridLayout gridLayout;
    private Handler myHandler= new Handler();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        arrangemetOfCells = new MyMatrixAlgorithm();
        winnerDisplay = findViewById(R.id.winnerTv);
        gridLayout = findViewById(R.id.layout);
        currentCount = 0;
        personsTurn = 1;
        isGameAtTheEnd = false;
        winnerDisplay.setText("Player 1 turn");
    }

    public void buttonClicked(View view) {
        Button mBtn = (Button) view;
        if (mBtn.getText().equals("")) {

            if (!isGameAtTheEnd) {
                gameButtonClicked(view);
            }
        }else{
            Toast.makeText(this, "This is selected already!", Toast.LENGTH_SHORT).show();
        }
    }
    private void gameButtonClicked(View view) {
        currentCount++;
        Button mBtn = (Button) view;
        mBtn.setBackgroundColor(getResources().getColor(personsTurn ==1?R.color.btn_red:R.color.btn_blue));
        mBtn.setText(personsTurn ==1?"X":"O");
        int id = Integer.parseInt((String) view.getTag());
        int rowIndex = (id - 1) / 3;
        int colIndex = (id - 1) % 3;
        arrangemetOfCells.set(rowIndex, colIndex, personsTurn);

        int winner = checkWinner();

        if (winner == 1) {
            winnerDisplay.setText("Player 1 wins");
            isGameAtTheEnd = true;
        } else if (winner == 2) {
            winnerDisplay.setText((PlayerMode.equals("computer") ? "Computer Wins" : "Player 2 wins"));
            isGameAtTheEnd = true;
        }

        if (currentCount == 9 && winner == 0) {
            winnerDisplay.setText("Draw!");
            isGameAtTheEnd = true;
        }

        if (!isGameAtTheEnd) {
            if (personsTurn == 1) {
                personsTurn = 2;
                winnerDisplay.setText((PlayerMode.equals("computer") ? "Computer turns" : "Player 2 turns"));
                if (personsTurn == 2 && PlayerMode.equals("computer")){
                    // simulate the play by delaying
                    myHandler.postDelayed(() -> computerPlay(), 1000);
                }
            } else {
                personsTurn = 1;
                winnerDisplay.setText("Player 1 turns");
            }
        }
    }
    //0 - no winner, 1 - player 1 is the winner, 2 - player 2 is the winner
    private int checkWinner() {

        int winner = 0;
        if (checkIfWinner(1)) {
            winner = 1;
        } else if (checkIfWinner(2)) {
            winner = 2;
        }

        //check for player 2
        return winner;
    }
    private void computerPlay(){
        ArrayList<Button> buttons = new ArrayList<>();
        int size = gridLayout.getChildCount();
        // add all buttons that have not yet been clicked
        for (int h = 0; h <  size; h++){
            Button btn = (Button) gridLayout.getChildAt(h);
            if (btn.getText() == ""){
                buttons.add(btn);
            }
        }
        // randomize the choice
        if (buttons.size() > 0) {
            Random r = new Random();
            int rand = r.nextInt(buttons.size());
            Button btn = buttons.get(rand);
            gameButtonClicked(btn);
        }

    }

    private boolean checkIfWinner(int playerNumber) {

        boolean win = false;
        //not a good method as it only support 3x3 cells - use nested for loop
        boolean row1 = (arrangemetOfCells.get(0, 0) == playerNumber && arrangemetOfCells.get(1, 0) == playerNumber && arrangemetOfCells.get(2, 0) == playerNumber);
        boolean row2 = (arrangemetOfCells.get(0, 1) == playerNumber && arrangemetOfCells.get(1, 1) == playerNumber && arrangemetOfCells.get(2, 1) == playerNumber);
        boolean row3 = (arrangemetOfCells.get(0, 2) == playerNumber && arrangemetOfCells.get(1, 2) == playerNumber && arrangemetOfCells.get(2, 2) == playerNumber);

        boolean col1 = (arrangemetOfCells.get(0, 0) == playerNumber && arrangemetOfCells.get(0, 1) == playerNumber && arrangemetOfCells.get(0, 2) == playerNumber);
        boolean col2 = (arrangemetOfCells.get(1, 0) == playerNumber && arrangemetOfCells.get(1, 1) == playerNumber && arrangemetOfCells.get(1, 2) == playerNumber);
        boolean col3 = (arrangemetOfCells.get(2, 0) == playerNumber && arrangemetOfCells.get(2, 1) == playerNumber && arrangemetOfCells.get(2, 2) == playerNumber);

        boolean diag1 = (arrangemetOfCells.get(0, 0) == playerNumber && arrangemetOfCells.get(1, 1) == playerNumber && arrangemetOfCells.get(2, 2) == playerNumber);
        boolean diag2 = (arrangemetOfCells.get(0, 2) == playerNumber && arrangemetOfCells.get(1, 1) == playerNumber && arrangemetOfCells.get(2, 0) == playerNumber);

        //not a good way as it only supports 3x3 cells
        if (row1 || row2 || row3 || col1 || col2 || col3 || diag1 || diag2) {
            win = true;
        }
        return win;
    }

}