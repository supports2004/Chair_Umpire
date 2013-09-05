package com.example.tennis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import static android.view.View.OnClickListener;

public class InstallScreen extends Activity {
    public String[] names = {"", ""};
    public int player1_side;
    public boolean player1_is_serve;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPlayersScreen(null);
    }

    public void onChoiceInput(View v)
    {
        OnClickListener radioListener = new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                RadioButton clicked = (RadioButton)v;
                RadioButton serve1 = (RadioButton)findViewById(R.id.serve1);
                RadioButton serve2 = (RadioButton)findViewById(R.id.serve2);
                if (clicked == serve1)
                {
                    serve2.setChecked(false);
                }
                else
                {
                    serve1.setChecked(false);
                }
            }
        };


        EditText edittext;
        TextView choice_namel;
        TextView choice_name2;
        edittext = (EditText)findViewById(R.id.player1);
        this.names[0] = edittext.getText().toString();
        edittext = (EditText)findViewById(R.id.player2);
        this.names[1] = edittext.getText().toString();

        setContentView(R.layout.choice);
        choice_namel = (TextView) findViewById(R.id.name1);
        choice_name2 = (TextView) findViewById(R.id.name2);
        choice_namel.setText(this.names[0]);
        choice_name2.setText(this.names[1]);
        RadioButton serve1 = (RadioButton) findViewById(R.id.serve1);
        RadioButton serve2 = (RadioButton)findViewById(R.id.serve2);
        serve1.setOnClickListener(radioListener);
        serve2.setOnClickListener(radioListener);
    }

    public void onPlayersScreen(View v)
        {
        setContentView(R.layout.players_screen);
        TextView player_namel = (TextView) findViewById(R.id.player1);
        TextView player_name2 = (TextView) findViewById(R.id.player2);
        player_namel.setText(this.names[0]);
        player_name2.setText(this.names[1]);
    }

    public void onCourt(View v)
    {



        Spinner spinner = (Spinner) findViewById(R.id.player1_side);
        RadioButton serve1 = (RadioButton) findViewById(R.id.serve1);
        this.player1_is_serve = serve1.isChecked();
        //String result = spinner.getSelectedItem().toString();
        this.player1_side = (int) spinner.getSelectedItemId();
        Umpire.get_instance().handle_from_installScreen(this);
     //   setContentView(R.layout.court_collapsed_sets);
   //     TextView sideresult = (TextView) findViewById(R.id.sideresult);
   //     sideresult.setText(String.valueOf(player1_side));
    }
}

