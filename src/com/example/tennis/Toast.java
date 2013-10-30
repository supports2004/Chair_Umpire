package com.example.tennis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;


import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * generates floating messages on court screen
 */

public class Toast implements Observer {
    private String _msg;
    private String _alertmsg;
    private Integer[] _games = {null, null};
    private String _change_sides_msg;
    private String _deciding_point_msg;
    private boolean _undo = false;
    private IUmpire _umpire;
    private  boolean _is_tie = false;
    private Hashtable<IUmpire.Event, Boolean> _event = new Hashtable<IUmpire.Event, Boolean>();
    private int _setsnum;
    private String _winner_name;
    private IPlayer _won_player;
    private IPlayer _lost_player;
    private android.widget.Toast _toast;
    Toast()
    {
        _umpire = myApp.get_umpire();
        _umpire.addObserver(this);
        _toast = android.widget.Toast.makeText(myApp.get_instance().getApplicationContext(), "", android.widget.Toast.LENGTH_SHORT);
        _toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        _toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public void update(Observable observable, Object event)
    {
        _msg = "";
        _alertmsg = "";
        switch ((IUmpire.Event) event)
        {
            case UNDO_START:
                _undo = true;
                break;
            case UNDO_END:
                _undo = false;
                break;
            case NEW_MATCH:
  //              _event.put(IUmpire.Event.NEW_MATCH, true);

                _setsnum = 0;
                break;
            case NEW_SET:
                _games[0] = 0;
                _games[1] = 0;
                if (_setsnum > 0)
                {   //гейм и %1$s сет %2$s, %3$s-%4$s. :
                    if (_umpire.get_players()[0].get_games().lastElement() > _umpire.get_players()[1].get_games().lastElement())
                    {
                        _won_player  = _umpire.get_players()[0];
                        _lost_player = _umpire.get_players()[1];
                    }
                    else
                    {
                        _won_player  = _umpire.get_players()[1];
                        _lost_player = _umpire.get_players()[0];
                    }
                    _alertmsg = String.format(myApp.get_instance().getString(R.string.game_and_set_player), Helper.spell_the_digit(_setsnum, R.array.kakoi_po_schetu), _won_player.get_name(), Helper.spell_the_digit(_won_player.get_games().lastElement(), R.array.skolko), Helper.spell_the_digit(_lost_player.get_games().lastElement(), R.array.skolko));
                    // %1$s-%1$s по сетам :
                    if (_umpire.get_players()[0].get_sets() == _umpire.get_players()[1].get_sets())
                    {
                        _alertmsg += String.format(myApp.get_instance().getString(R.string.equal_in_sets), Helper.spell_the_digit(_umpire.get_players()[0].get_sets(), R.array.skolko));
                    }
                    else
                    {   // %1$s ведет %2$s-%3$s по сетам:
                        if (_umpire.get_players()[0].get_sets() > _umpire.get_players()[1].get_sets())
                        {
                            _won_player  = _umpire.get_players()[0];
                            _lost_player = _umpire.get_players()[1];
                        }
                        else
                        {
                            _won_player  = _umpire.get_players()[1];
                            _lost_player = _umpire.get_players()[0];
                        }
                        _alertmsg += String.format(myApp.get_instance().getString(R.string.player_leads_in_sets), _won_player.get_name(), Helper.spell_the_digit(_won_player.get_sets(), R.array.skolko), Helper.spell_the_digit(_lost_player.get_sets(), R.array.skolko));
                    }
                }
                _event.put(IUmpire.Event.NEW_SET, true);
                _setsnum ++;
/*                if (_event.get(IUmpire.Event.NEW_MATCH) == true)
                {
                    _event.put(IUmpire.Event.NEW_MATCH, false);
                }
                else
                {

                }*/
                _toast.setDuration(android.widget.Toast.LENGTH_LONG);
                _msg += String.format(myApp.get_instance().getString(R.string.serve_play), Helper.spell_the_digit(_setsnum, R.array.kakoi_po_schetu),  _umpire.get_serving_player().get_name());
                break;
            case NEW_GAME:
            case NEW_TIEBREAK:
                Log.w("toast", "NEW_GAME") ;
                _event.put(IUmpire.Event.NEW_GAME, true);
                if (_event.get(IUmpire.Event.NEW_SET) != null && _event.get(IUmpire.Event.NEW_SET) == true)
                {
                    _event.put(IUmpire.Event.NEW_SET, false);
                }
                else
                {
                    _toast.setDuration(android.widget.Toast.LENGTH_LONG);
                    if (_umpire.get_players()[0].get_games().lastElement() > _games[0])
                    {
                        _winner_name = _umpire.get_players()[0].get_name();
                        _games[0] = _umpire.get_players()[0].get_games().lastElement();
                    }
                    else
                    {
                        _winner_name = _umpire.get_players()[1].get_name();
                        _games[1] = _umpire.get_players()[1].get_games().lastElement();
                    }
                    if (_games[0] == _games[1])
                    {
                        _msg = String.format(myApp.get_instance().getString(R.string.game_end_equal), _winner_name, Helper.spell_the_digit(_games[0], R.array.po_skolki), Helper.spell_the_digit(_setsnum, R.array.v_kakom_po_schetu) );
                    }
                    else if (_games[0] > _games[1])
                    {  //гейм %1$s, %2$s ведет %3$s-%4$s в %5$s сете
                        _msg = String.format(myApp.get_instance().getString(R.string.game_end), _winner_name, _umpire.get_players()[0].get_name(), Helper.spell_the_digit(_games[0], R.array.skolko), Helper.spell_the_digit(_games[1],R.array.skolko), Helper.spell_the_digit(_setsnum, R.array.v_kakom_po_schetu) );
                    }
                    else
                    {
                        _msg = String.format(myApp.get_instance().getString(R.string.game_end), _winner_name, _umpire.get_players()[1].get_name(),  Helper.spell_the_digit(_games[1], R.array.skolko), Helper.spell_the_digit(_games[0],R.array.skolko), Helper.spell_the_digit(_setsnum, R.array.v_kakom_po_schetu) );
                    }


                }
                if (event == IUmpire.Event.NEW_TIEBREAK)
                {
                    _is_tie =  true;
                    _msg += ", " + myApp.get_instance().getString(R.string.tiebreak);
                }
                else
                {
                    _is_tie = false;
                }
                break;
/*            case NEW_TIEBREAK:
                _event.put(IUmpire.Event.NEW_TIEBREAK, true);
                if (_event.get(IUmpire.Event.NEW_SET) != null && _event.get(IUmpire.Event.NEW_SET) == true)
                {
                    _event.put(IUmpire.Event.NEW_SET, false);
                }
                else
                {

                }
                _is_tie = true;
                break;*/
            case NEW_POINT:
                Log.w("toast", "NEW_POINT") ;
                _event.put(IUmpire.Event.NEW_POINT, true);
                if (_event.get(IUmpire.Event.NEW_GAME) != null && _event.get(IUmpire.Event.NEW_GAME) == true)
                {
                    _event.put(IUmpire.Event.NEW_GAME, false);
                    _toast.setDuration(android.widget.Toast.LENGTH_SHORT);
                }
/*                else if (_event.get(IUmpire.Event.NEW_TIEBREAK) != null && _event.get(IUmpire.Event.NEW_TIEBREAK) == true)
                {
                    _event.put(IUmpire.Event.NEW_TIEBREAK, false);
                }*/
                else
                {
                    _msg = _spellpoints_msg();
                }

                break;
            case CHANGE_SERVE:
                break;
            case CHANGE_SIDES:
                if (!_undo)
                {
                    _change_sides_msg = myApp.get_instance().getString(R.string.change_sides);
                }
                break;
            case DECIDING_POINT:
                if (!_undo)
                {
                    _deciding_point_msg = myApp.get_instance().getString(R.string.deciding_point);
                }
                break;
        }

        if (_msg != "" && !_undo)
        {
            if (_change_sides_msg != null)
            {
                _msg += ", " + _change_sides_msg;
                _change_sides_msg = null;
            }
            if (_deciding_point_msg != null)
            {
                _msg += ". " + _deciding_point_msg;
                _deciding_point_msg = null;
            }
            if (_alertmsg != "")
            {
                final String msg = _msg;
                final TextView countdown = new TextView((Context) _umpire.get_court());
                countdown.setGravity(1);
                countdown.setTextSize(18);
                new CountDownTimer(1000 * 120, 1000) {
                    private int _left = 120;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        countdown.setText(Integer.toString(_left --));
                    }
                    @Override
                    public void onFinish() {
                      //  countdownDisplay.setText("KABOOM!");
                    }
                }.start();
                AlertDialog.Builder ad = new AlertDialog.Builder((Context) _umpire.get_court());
                ad.setTitle(_alertmsg);  // заголовок
                ad.setMessage(myApp.get_instance().getString(R.string.pause)); // сообщение
                ad.setView(countdown);
                ad.setPositiveButton(myApp.get_instance().getString(R.string.Continue), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        _show_toast(msg);
                    }
                });
                ad.setCancelable(false);
                ad.show();
                return;
            }
            _show_toast(_msg);
        }
    }

    private String _spellpoints_msg()
    {
        if (_is_tie)
        {
            return Helper.spell_the_digit(_umpire.get_serving_player().get_points(), R.array.skolko) + " " + Helper.spell_the_digit(_umpire.get_returning_player().get_points(), R.array.skolko);
        }
        if (_umpire.get_serving_player().get_points() == _umpire.get_returning_player().get_points()
            && (_umpire.get_serving_player().get_points() == 2  || _umpire.get_serving_player().get_points() == 3))
        {   // ровно:
            return Helper.spell_the_digit(5, R.array.spell_points);
        }
        if (_umpire.get_returning_player().get_points() - _umpire.get_serving_player().get_points() == 1 && _umpire.get_serving_player().get_points() >/*=*/ 2)
        {   // меньше:
            return Helper.spell_the_digit(4, R.array.spell_points);
        }
        if (_umpire.get_serving_player().get_points() - _umpire.get_returning_player().get_points() == 1 && _umpire.get_returning_player().get_points() >/*=*/ 2)
        {   // больше:
            return Helper.spell_the_digit(6, R.array.spell_points);
        }
        if (_umpire.get_serving_player().get_points() == 1 &&  _umpire.get_returning_player().get_points() == 1)
        {   // по пятнадцати:
            return Helper.spell_the_digit(7, R.array.spell_points);
        }
        return Helper.spell_the_digit(_umpire.get_serving_player().get_points(), R.array.spell_points) + " " + Helper.spell_the_digit(_umpire.get_returning_player().get_points(), R.array.spell_points);
    }


    private void _show_toast(String msg)
    {
        _toast.setText(msg);
        _toast.show();
    }
}
