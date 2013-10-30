package com.example.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


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
