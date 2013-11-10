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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class ChoiceActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        View.OnClickListener radioListener = new View.OnClickListener() {
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
        // имена игроков перед радиобатонами выбора подачи:
        // player's names in front of serve choice radiobuttons:
        TextView choice_namel = (TextView) findViewById(R.id.name1);
        TextView choice_name2 = (TextView) findViewById(R.id.name2);
        choice_namel.setText(((String[]) myApp.get_umpire().request().get("names"))[0]);
        choice_name2.setText(((String[]) myApp.get_umpire().request().get("names"))[1]);
        // ликвидируем множественный выбор в радиобаттонах подачи:
        RadioButton serve1 = (RadioButton) findViewById(R.id.serve1);
        RadioButton serve2 = (RadioButton)findViewById(R.id.serve2);
        // при возврате устанавливаем радиобатоны подачи в положение с учетом предыдущего выбора:
        serve1.setChecked(((Boolean) myApp.get_umpire().request().get("player1_is_serve")));
        serve2.setChecked(((Boolean) myApp.get_umpire().request().get("player1_is_serve")) ? false : true);
        Spinner spinner = (Spinner) findViewById(R.id.player1_side);
        spinner.setSelection(((Integer) myApp.get_umpire().request().get("player1_side")));

        serve1.setOnClickListener(radioListener);
        serve2.setOnClickListener(radioListener);
    }

    public void onSubmit(View v)
    {
        RadioButton serve1 = (RadioButton) findViewById(R.id.serve1);
        myApp.get_umpire().request().put("player1_is_serve", serve1.isChecked());
        Spinner spinner = (Spinner) findViewById(R.id.player1_side);
        myApp.get_umpire().request().put("player1_side", (int) spinner.getSelectedItemId());
        Intent intent;
/*        if (getResources().getConfiguration().locale.getLanguage().equals("ru"))
        {
            intent = new Intent(getApplicationContext(), FormatActivity.class);
        }
        else
        {
            intent = new Intent(getApplicationContext(), CourtActivity.class);
        }*/
        intent = new Intent(getApplicationContext(), FormatActivity.class);
        startActivity(intent);
    }
}
