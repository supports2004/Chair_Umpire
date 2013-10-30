package com.example.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 13.09.13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class PlayerNamesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);
        // вбиваем в поля по умолчанию имена, хранящиеся в myApp:
        TextView player_namel = (TextView) findViewById(R.id.player1);
        TextView player_name2 = (TextView) findViewById(R.id.player2);
        player_namel.setText(((String[]) myApp.get_umpire().request().get("names"))[0]);
        player_name2.setText(((String[]) myApp.get_umpire().request().get("names"))[1]);
        // =========================== from FormatActivity:
        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        RadioButton noAD = (RadioButton)findViewById(R.id.noAD);
        EditText games_in_set = (EditText) findViewById(R.id.games_in_set);
        // при возврате устанавливаем значения с учетом предыдущего выбора:
        AD.setChecked((Boolean) myApp.get_umpire().request().get("AD"));
        noAD.setChecked(((Boolean) myApp.get_umpire().request().get("AD")) ? false : true);
        games_in_set.setText(((Integer) myApp.get_umpire().request().get("games_in_set")).toString());
        // =========================== /from FormatActivity
    }

    public void onSubmit(View v)
    {
        String[] names = {((TextView) findViewById(R.id.player1)).getText().toString(), ((TextView) findViewById(R.id.player2)).getText().toString()};
        myApp.get_umpire().request().put("names", names);
        // =========================== from FormatActivity:
        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        EditText games_in_set = (EditText) findViewById(R.id.games_in_set);
        myApp.get_umpire().request().put("AD", AD.isChecked());
        myApp.get_umpire().request().put("games_in_set", Integer.valueOf(games_in_set.getText().toString()));
        // =========================== /from FormatActivity
        Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
        startActivity(intent);



    }

}
