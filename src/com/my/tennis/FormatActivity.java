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
import android.widget.CheckBox;



public class FormatActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.format);

        CheckBox Toast = (CheckBox) findViewById(R.id.toast);
        Toast.setChecked((Boolean) myApp.get_umpire().request().get("toast"));
    }

    public void onSubmit(View v)
    {
        CheckBox Toast = (CheckBox) findViewById(R.id.toast);
        myApp.get_umpire().request().put("toast", Toast.isChecked());
        Intent intent = new Intent(getApplicationContext(), CourtActivity.class);
        startActivity(intent);
    }
}
