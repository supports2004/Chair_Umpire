package com.example.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


public class FormatActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.format);

        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        RadioButton noAD = (RadioButton)findViewById(R.id.noAD);
        EditText games_in_set = (EditText) findViewById(R.id.games_in_set);
        // при возврате устанавливаем значения с учетом предыдущего выбора:
        AD.setChecked((Boolean) myApp.get_umpire().request.get("AD"));
        noAD.setChecked(((Boolean) myApp.get_umpire().request.get("AD")) ? false : true);
        games_in_set.setText(((Integer) myApp.get_umpire().request.get("games_in_set")).toString());
    }

    public void onSubmit(View v)
    {
        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        EditText games_in_set = (EditText) findViewById(R.id.games_in_set);
        myApp.get_umpire().request.put("AD", AD.isChecked());
        myApp.get_umpire().request.put("games_in_set", Integer.valueOf(games_in_set.getText().toString()));
        Intent intent = new Intent(getApplicationContext(), CourtActivity.class);
        startActivity(intent);
    }
}
