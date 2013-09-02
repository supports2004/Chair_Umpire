package com.example.tennis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Vector;


public class Court  extends Activity implements ICourt {
    private Umpire _umpire;
    private TableRow.LayoutParams _vertical_Lshift = new TableRow.LayoutParams();
    private TableRow.LayoutParams _vertical_Rshift = new TableRow.LayoutParams();
    private Integer[] _pixels = {null, null};
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
    //================== расположение игроков в нужные квадраты:
        if (_umpire.get_serving_box() == 1)
        {     Log.w("serving_box=1", "");
            _vertical_Lshift.topMargin = _vertical_Rshift.bottomMargin = 30;
            _vertical_Rshift.topMargin = _vertical_Lshift.bottomMargin = 0;
        }
        else
        {
            _vertical_Lshift.topMargin = _vertical_Rshift.bottomMargin = 0;
            _vertical_Rshift.topMargin = _vertical_Lshift.bottomMargin = 30;
        }
        ((TextView) findViewById(R.id.court_lplayer)).setLayoutParams(_vertical_Lshift);
        ((TextView) findViewById(R.id.court_rplayer)).setLayoutParams(_vertical_Rshift);
    //================== /расположение игроков в нужные квадраты
    //================== Имена игроков по бокам от корта:
        ((TextView) findViewById(R.id.court_lplayer)).setText(this._get_lplayer_name());
        ((TextView) findViewById(R.id.court_rplayer)).setText(this._get_rplayer_name());
    //================== /Имена игроков по бокам от корта
        IPlayer[] players = _umpire.get_players();
        ((TextView) findViewById(R.id.score_lplayer)).setText(players[0].get_name());
        ((TextView) findViewById(R.id.score_rplayer)).setText(players[1].get_name());
        ((ImageView) findViewById(R.id.score_lball)).setImageResource(R.drawable.ball_small);
        ((TextView) findViewById(R.id.score_lsets)).setText(_umpire.get_left_player().get_sets());
        ((TextView) findViewById(R.id.score_rsets)).setText(_umpire.get_right_player().get_sets());
        Vector<String> lgames = (Vector<String>) _umpire.get_left_player().get_games();
        ((TextView) findViewById(R.id.score_lgames)).setText(lgames.lastElement());
        Vector<String> rgames = (Vector<String>) _umpire.get_right_player().get_games();
        ((TextView) findViewById(R.id.score_rgames)).setText(rgames.lastElement());


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
