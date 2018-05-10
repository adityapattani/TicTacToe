package com.aditya.tic_tac_toe;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    ConstraintLayout background;

    int[][] gridArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int totalMoves = 0;
    int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        image1 = findViewById(R.id.img_grid_1);
        image2 = findViewById(R.id.img_grid_2);
        image3 = findViewById(R.id.img_grid_3);
        image4 = findViewById(R.id.img_grid_4);
        image5 = findViewById(R.id.img_grid_5);
        image6 = findViewById(R.id.img_grid_6);
        image7 = findViewById(R.id.img_grid_7);
        image8 = findViewById(R.id.img_grid_8);
        image9 = findViewById(R.id.img_grid_9);
        background = findViewById(R.id.layout_bg_game);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_grid_1:
                processInput(0, 0, image1);
                break;
            case R.id.img_grid_2:
                processInput(0, 1, image2);
                break;
            case R.id.img_grid_3:
                processInput(0, 2, image3);
                break;
            case R.id.img_grid_4:
                processInput(1, 0, image4);
                break;
            case R.id.img_grid_5:
                processInput(1, 1, image5);
                break;
            case R.id.img_grid_6:
                processInput(1, 2, image6);
                break;
            case R.id.img_grid_7:
                processInput(2, 0, image7);
                break;
            case R.id.img_grid_8:
                processInput(2, 1, image8);
                break;
            case R.id.img_grid_9:
                processInput(2, 2, image9);
                break;
        }
    }

    private boolean checkDiagonals() {
        if (gridArray[0][0] == gridArray[1][1] &&
                gridArray[1][1] == gridArray[2][2] &&
                gridArray[0][0] != 0)
            return true;

        if (gridArray[0][2] == gridArray[1][1] &&
                gridArray[1][1] == gridArray[2][0] &&
                gridArray[0][2] != 0)
            return true;

        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (gridArray[i][0] == gridArray[i][1] &&
                    gridArray[i][1] == gridArray[i][2] &&
                    gridArray[i][0] != 0)
                return true;
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (gridArray[0][i] == gridArray[1][i] &&
                    gridArray[1][i] == gridArray[2][i] &&
                    gridArray[0][i] != 0)
                return true;
        }
        return false;
    }

    private boolean isBoardFilled() {
        return totalMoves != 9;
    }

    private void processInput(int row, int col, ImageView image) {
        if (gridArray[row][col] == 0) {
            if (turn % 2 == 0) {
                image.setImageResource(R.drawable.ic_cross);
                gridArray[row][col] = 1;
            } else {
                image.setImageResource(R.drawable.ic_circle);
                gridArray[row][col] = 2;
            }
            turn += 1;
            totalMoves += 1;
            checkGrid();
        } else {
            image.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
            Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkGrid() {
        if (!isBoardFilled()) {
            Toast.makeText(getApplicationContext(), "Board filled up", Toast.LENGTH_SHORT).show();
            disableInput();
        }
        if (checkColumns() || checkDiagonals() || checkRows()) {
            if ((turn + 1) % 2 == 0) {
                Toast.makeText(getApplicationContext(), "Player 1 wins", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Player 2 wins", Toast.LENGTH_SHORT).show();
            }
            disableInput();
        }
    }

    private void disableInput() {
        image1.setClickable(false);
        image2.setClickable(false);
        image3.setClickable(false);
        image4.setClickable(false);
        image5.setClickable(false);
        image6.setClickable(false);
        image7.setClickable(false);
        image8.setClickable(false);
        image9.setClickable(false);
    }
}
