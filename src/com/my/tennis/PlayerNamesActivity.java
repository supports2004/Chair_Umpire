/*
Copyright © 2013 Alexei A
supports2004@mail.ru

This file is part of Chair Umpire(tennis score).

    Chair Umpire(tennis score) is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Chair Umpire(tennis score) is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Chair Umpire(tennis score).  If not, see <http://www.gnu.org/licenses/>.
*/

package com.my.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class PlayerNamesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);
        // put names, stored in myApp as default values:
        TextView player_namel = (TextView) findViewById(R.id.player1);
        TextView player_name2 = (TextView) findViewById(R.id.player2);
        player_namel.setText(((String[]) myApp.get_umpire().request().get("names"))[0]);
        player_name2.setText(((String[]) myApp.get_umpire().request().get("names"))[1]);
        // =========================== from FormatActivity:
        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        RadioButton noAD = (RadioButton)findViewById(R.id.noAD);
        EditText games_in_set = (EditText) findViewById(R.id.games_in_set);
        // if we got here after go back, put values according to previous choice:
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
