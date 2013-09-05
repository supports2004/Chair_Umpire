package com.example.tennis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Vector;


public class Court extends Activity implements ICourt {
    private Umpire _umpire;
    private TableRow.LayoutParams _vertical_Lshift = new TableRow.LayoutParams();
    private TableRow.LayoutParams _vertical_Rshift = new TableRow.LayoutParams();
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
      //  TextView textPrim1 = new TextView(getApplicationContext());
      //  TableRow row = (TableRow) findViewById(R.id.score_line1);
    //    row.addView();
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
    //================================================== Таблица со счетом:
        IPlayer[] players = _umpire.get_players();
        ((TextView) findViewById(R.id.score_1player)).setText(players[0].get_name());
        ((TextView) findViewById(R.id.score_2player)).setText(players[1].get_name());
        // мячик подачи перед именем:
        if (players[0] == _umpire.get_serving_player())
        {
            ((ImageView) findViewById(R.id.score_1ball)).setImageResource(R.drawable.ball_small);
            ((ImageView) findViewById(R.id.score_2ball)).setImageResource(R.drawable.ball_small_empty);
        }
        else
        {
            ((ImageView) findViewById(R.id.score_1ball)).setImageResource(R.drawable.ball_small_empty);
            ((ImageView) findViewById(R.id.score_2ball)).setImageResource(R.drawable.ball_small);
        }
        // счет:
        ((TextView) findViewById(R.id.score_1sets)).setText(players[0].get_sets());
        ((TextView) findViewById(R.id.score_2sets)).setText(players[1].get_sets());
        Vector<String> games1 = (Vector<String>) players[0].get_games();
        ((TextView) findViewById(R.id.score_1games)).setText(games1.lastElement());
        Vector<String> rgames2 = (Vector<String>) players[1].get_games();
        ((TextView) findViewById(R.id.score_2games)).setText(rgames2.lastElement());
        ((TextView) findViewById(R.id.score_1points)).setText(players[0].get_points());
        ((TextView) findViewById(R.id.score_2points)).setText(players[1].get_points());
        //============================================== /Таблица со счетом
    }

    public void on_player_click(View v)
    {
        switch (v.getId())
        {
            case R.id.court_lplayer:
                _umpire.add_point(_umpire.get_left_player());
                break;
            case R.id.court_rplayer:
                _umpire.add_point(_umpire.get_right_player());
                break;
            case R.id.score_1player:
                _umpire.add_point(_umpire.get_players()[0]);
                break;
            case R.id.score_2player:
                _umpire.add_point(_umpire.get_players()[1]);
                break;
        }
    }


    public void onBackPressed()
    {
        if (!_umpire.undo())
        {
            // очки на нулях, минусовать некуда, откатываемся на пред. экран:
            super.onBackPressed();
        }
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
