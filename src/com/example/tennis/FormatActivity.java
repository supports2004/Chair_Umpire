package com.example.tennis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;


public class FormatActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.format);

        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        RadioButton noAD = (RadioButton)findViewById(R.id.noAD);
        // при возврате устанавливаем радиобатоны подачи в положение с учетом предыдущего выбора:
        AD.setChecked((Boolean) myApp.get_umpire().request.get("AD"));
        noAD.setChecked(((Boolean) myApp.get_umpire().request.get("AD")) ? false : true);
    }

    public void onSubmit(View v)
    {
        RadioButton AD = (RadioButton) findViewById(R.id.AD);
        myApp.get_umpire().request.put("AD", AD.isChecked());
        Intent intent = new Intent(getApplicationContext(), CourtActivity.class);
        startActivity(intent);
    }
}
