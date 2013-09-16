package com.example.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        // вбиваем в поля по умолчанию имена, хранящиеся в Umpire:
        TextView player_namel = (TextView) findViewById(R.id.player1);
        TextView player_name2 = (TextView) findViewById(R.id.player2);
        player_namel.setText(((String[]) Umpire.get_instance().request.get("names"))[0]);
        player_name2.setText(((String[]) Umpire.get_instance().request.get("names"))[1]);
    }

    public void onSubmit(View v)
    {
        String[] names = {((TextView) findViewById(R.id.player1)).getText().toString(), ((TextView) findViewById(R.id.player2)).getText().toString()};
        Umpire.get_instance().request.put("names", names);
        Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
        startActivity(intent);
    }

}
