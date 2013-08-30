package com.example.tennis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Court  extends Activity implements ICourt {
    private Umpire _umpire;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Umpire.get_instance().set_court(this);
        Log.w("Court.onCreate", "launchered");
        _umpire = Umpire.get_instance();
        setContentView(R.layout.court);
        show();
    }

    public void show()
    {
        ((TextView) findViewById(R.id.court_lplayer)).setText(this._get_lplayer_name());
        ((TextView) findViewById(R.id.court_rplayer)).setText(this._get_rplayer_name());

        ((TextView) findViewById(R.id.score_lplayer)).setText(_umpire.get_left_player().get_name());
        ((TextView) findViewById(R.id.score_rplayer)).setText(_umpire.get_right_player().get_name());
        ((ImageView) findViewById(R.id.score_lball)).setImageResource(R.drawable.ball_small);
        ((TextView) findViewById(R.id.score_lsets)).setText(_umpire.get_left_player().get_sets());
        ((TextView) findViewById(R.id.score_rsets)).setText(_umpire.get_right_player().get_sets());
        ((TextView) findViewById(R.id.score_lgames)).setText(_umpire.get_left_player().get_games());
        ((TextView) findViewById(R.id.score_rgames)).setText(_umpire.get_right_player().get_games());
        ((TextView) findViewById(R.id.score_lpoints)).setText(_umpire.get_left_player().get_points());
        ((TextView) findViewById(R.id.score_rpoints)).setText(_umpire.get_right_player().get_points());
    }

    public void on_left_click(View v)
    {
        _umpire.add_point(_umpire.get_left_player());
    }
    public void on_right_click(View v)
    {
        _umpire.add_point(_umpire.get_right_player());
    }


    private String _get_lplayer_name()
    {
        return _umpire.get_serving_player() == _umpire.get_left_player() ? "*" + _umpire.get_left_player().get_name() : _umpire.get_left_player().get_name();
    }
    private String _get_rplayer_name()
    {
        return _umpire.get_serving_player() == _umpire.get_right_player() ? "*" + _umpire.get_right_player().get_name() : _umpire.get_right_player().get_name();
    }
}
