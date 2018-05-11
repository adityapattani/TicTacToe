package com.aditya.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TwoPlayer extends Fragment{

    EditText playerOneName, playerTwoName;
    Button startGame;

    boolean flag1 = true;
    boolean flag2 = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twoplayer, container, false);
        playerOneName = view.findViewById(R.id.edt_first_name);
        playerTwoName = view.findViewById(R.id.edt_second_name);
        startGame = view.findViewById(R.id.btn_double_begin);



        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = playerOneName.getText().toString().trim();
                String secondName = playerTwoName.getText().toString().trim();
                if(firstName.length() == 0) {
                    playerOneName.setError("Please provide a name");
                    flag1 = false;
                } else if (firstName.toLowerCase().equals("cpu")) {
                    playerTwoName.setError("You cannot use this name");
                    flag1 = false;
                } else {
                    flag1 = true;
                }
                if(secondName.length() == 0) {
                    playerTwoName.setError("Please provide a name");
                    flag2 = false;
                } else if (secondName.toLowerCase().equals("cpu")) {
                    playerTwoName.setError("You cannot use this name");
                    flag2 = false;
                } else {
                    flag2 = true;
                }

                if (flag1 && flag2) {
                    Intent intent = new Intent(getActivity(), GameActivity.class);
                    intent.putExtra("player1", firstName);
                    intent.putExtra("player2", secondName);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
