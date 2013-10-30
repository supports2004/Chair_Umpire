package com.example.tennis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;


public class CourtActivity extends Activity implements ICourt, Observer {
    private IUmpire _umpire;
    private Vector<TextView[]> _games = new Vector<TextView[]>();
    private TableRow.LayoutParams _vertical_Lshift = new TableRow.LayoutParams();
    private TableRow.LayoutParams _vertical_Rshift = new TableRow.LayoutParams();
    private Integer[] _court_images = {R.drawable.court_left_1,R.drawable.court_left_2,R.drawable.court_right_1,R.drawable.court_right_2};
    private TableRow[] _score_lines = {null, null};
    private ImageView _court_img;
    private int _setsnum;
    private int _gamessnum;
    private String _header;
    private String[] _str_points = {null,null};
    private boolean _new_game_event;
    private boolean _change_serve_event;
    private boolean _change_sides_event;
    private  boolean _is_tie = false;



    public void update(Observable observable, Object event)
    {
        switch ((IUmpire.Event) event)
        {
            case NEW_MATCH:
                _setsnum = 0;
                _change_serve_event = true;
                _change_sides_event = true;
                break;
            case NEW_SET:
                _setsnum ++;
                _gamessnum = 0;
                break;
            case NEW_GAME:
                _new_game_event = true;
                _gamessnum ++;
                _header = String.format(getString(R.string.set_game), Helper.spell_the_digit(_setsnum, R.array.kakoi_po_schetu), Helper.spell_the_digit(_gamessnum, R.array.kakoi_po_schetu));
                _is_tie = false;
                break;
            case NEW_TIEBREAK:
                _new_game_event = true;
                _header = String.format(getString(R.string.set_tiebreak), Helper.spell_the_digit(_setsnum, R.array.kakoi_po_schetu));
                _is_tie = true;
                break;
            case CHANGE_SERVE:
                _change_serve_event = true;
                break;
            case CHANGE_SIDES:
                _change_sides_event = true;
                break;
        }
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.court);
        _score_lines[0] = (TableRow) findViewById(R.id.score_line1);
        _score_lines[1] = (TableRow) findViewById(R.id.score_line2);
        _court_img = (ImageView) findViewById(R.id.court);
        _umpire = myApp.get_umpire();
        // from resume:
        _umpire.deleteObservers();
        _umpire.addObserver(this);
        _umpire.init(this);
        ((TextView) findViewById(R.id.score_1player)).setText(_umpire.get_players()[0].get_name());
        ((TextView) findViewById(R.id.score_2player)).setText(_umpire.get_players()[1].get_name());
        Log.w("Court", "onCreate");
    }

    public void show()
    {
       // _show_toast(_spellpoints_msg());
        //((TextView) findViewById(R.id.header)).setText(_header);
        setTitle(_header);
/*        Resources mRes = getApplicationContext().getResources();
        Integer identifierID = mRes.getIdentifier("picture", "drawable", getApplicationContext().getPackageName());*/
        // выбираем одно из черырех изображений корта:
        _court_img.setImageResource(_court_images[_umpire.get_serving_box() + (_umpire.get_left_player() == _umpire.get_serving_player() ? -1 : 1)]);


      //  TextView textPrim1 = new TextView(getApplicationContext());
      //  TableRow row = (TableRow) findViewById(R.id.score_line1);
    //    row.addView();
    //================== расположение игроков в нужные квадраты:
        if (_umpire.get_serving_box() == 1)
        {
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
        if (_change_sides_event || _change_serve_event)
        {
            ((TextView) findViewById(R.id.court_lplayer)).setText(this._get_lplayer_name());
            ((TextView) findViewById(R.id.court_rplayer)).setText(this._get_rplayer_name());
            _change_sides_event = false;
        }
    //================== /Имена игроков по бокам от корта
    //================================================== Таблица со счетом:
        IPlayer[] players = _umpire.get_players();
        // мячик подачи перед именем:
        if (_change_serve_event)
        {
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
            _change_serve_event = false;
        }
        // счет:
        if (_new_game_event)
        {
            _dynamic_sets();
            _new_game_event = false;
        }
/*        ((TextView) findViewById(R.id.score_1sets)).setText(players[0].get_sets());
        ((TextView) findViewById(R.id.score_2sets)).setText(players[1].get_sets());
        Vector<String> games1 = (Vector<String>) players[0].get_games();
        ((TextView) findViewById(R.id.score_1games)).setText(games1.lastElement());
        Vector<String> rgames2 = (Vector<String>) players[1].get_games();
        ((TextView) findViewById(R.id.score_2games)).setText(rgames2.lastElement());*/
        if (_is_tie)
        {
            _str_points[0] = players[0].get_points().toString();
            _str_points[1] = players[1].get_points().toString();
        }
        else
        {
            _str_points[0] = players[1].get_points() == 4 ? "" : Helper.spell_the_digit(players[0].get_points(), R.array.points);
            _str_points[1] = players[0].get_points() == 4 ? "" : Helper.spell_the_digit(players[1].get_points(), R.array.points);
        }
        ((TextView) findViewById(R.id.score_1points)).setText(_str_points[0]);
        ((TextView) findViewById(R.id.score_2points)).setText(_str_points[1]);
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
            case R.id.score_line1:
                _umpire.add_point(_umpire.get_players()[0]);
                break;
            case R.id.score_2player:
            case R.id.score_line2:
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
        return _umpire.get_serving_player() == _umpire.get_left_player() ? "*" + _umpire.get_left_player().get_name() :  _umpire.get_left_player().get_name();
    }
    private String _get_rplayer_name()
    {
        return _umpire.get_serving_player() == _umpire.get_right_player() ? "*" + _umpire.get_right_player().get_name() : _umpire.get_right_player().get_name();
    }

    private void _dynamic_sets()
    {
        int sets_amount = ((Vector) _umpire.get_players()[0].get_games()).size(); // сыгранные + текущий сет
        int games_size = _games.size();
        if (sets_amount < games_size)
        {    // в результате undo уменьшилось количество сетов:
             for (int i = sets_amount; i < games_size; i ++)
             {
                 _score_lines[0].removeView(_games.lastElement()[0]);
                 _score_lines[1].removeView(_games.lastElement()[1]);
                 _games.remove(_games.lastElement());
             }
        }
        else if (sets_amount > games_size)
        {   // игроки сыграли очередной сет или игра только началась:
            for (int i = games_size; i < sets_amount; i ++)
            {
                TextView[] column = {new TextView(getApplicationContext()), new TextView(getApplicationContext())};
                for (int row = 0; row < 2; row ++)
                {
                    _score_lines[row].addView(column[row], 2 + i);
                    column[row].setTextAppearance(this, R.style.score);
                    column[row].setPadding(10,0,10,0);

                }
                _games.add(column);
            }
        }
        for (int i = 0; i < _games.size(); i ++)
        {
            for (int row = 0; row < 2; row ++)
            {
                _games.get(i)[row].setText(_umpire.get_players()[row].get_games().get(i).toString());
            }

        }
    }
}
