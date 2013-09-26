package com.example.tennis;


import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;


public class Umpire extends Application {
    private static Umpire _instance;
    public Map<String,  Object> request;
    private IPlayer[] _players = {null, null};
    private IPlayer   _left_player;
    private IPlayer   _right_player;
    private IPlayer   _serving_player;
    private Integer  _serving_box;
    private Match _match;
    private ICourt _court;
    private Vector<Integer> _history = new Vector<Integer>();
    private boolean _is_tiebreak = false;


    public void add_point(IPlayer win_player)
    {
        _history.add(win_player == _players[0] ? 0 : 1);
        _match.finish(win_player, _left_player == win_player ? _right_player : _left_player);
        _court.show();
        Log.w("Empire add_point", "launchered");
    }

    public boolean undo()
    {
        if (_history.size() >= 1)
        {
            _init_start_conditions();
            if (_history.size() > 1)
            {
                for (Integer i = 0; i < _history.size() - 1; i ++)
                {
                    _match.finish(_players[_history.get(i)], _players[_history.get(i) == 1 ? 0 : 1]);
                }
            }
            _history.remove(_history.size() - 1);
            _court.show();
            return true;
        }
        else
        {
            return false;
        }
    }

    private void _init_start_conditions()
    {
        if (((Integer)request.get("player1_side")) != 0)
        {
            this._left_player  = this._players[1];
            this._right_player = this._players[0];
        }
        else
        {
            this._left_player  = this._players[0];
            this._right_player = this._players[1];
        }
        this._serving_player = ((Boolean) request.get("player1_is_serve")) ? this._players[0] : this._players[1];
        _serving_box = 1;
        _match = new Match(new Set(new Game()));
    }


    public IPlayer[] get_players()
    {
        return _players;
    }

    public IPlayer get_left_player() {
        return _left_player;
    }

    public IPlayer get_right_player() {
        return _right_player;
    }

    public IPlayer get_serving_player() {
        return _serving_player;
    }

    public Integer get_serving_box()
    {
        return _serving_box;
    }

    public void change_serve()
    {
        _serving_player = _serving_player == _left_player ? _right_player : _left_player;
    }

    public void change_serving_box()
    {
        _serving_box = _serving_box == 1 ? 2 : 1;
    }

    public void change_sides()
    {
        IPlayer left_player = _left_player;
        _left_player = _right_player;
        _right_player = left_player;
    }


    public static Umpire get_instance()
    {
        return _instance;
    }
    @Override
    public final void onCreate()
    {

        request = new Hashtable<String, Object>();
        String[] names = {"", ""};
        request.put("names", names);
        request.put("player1_side", 0);
        request.put("player1_is_serve", true);
        super.onCreate();
        _instance = this;
    }

    public void init_court(ICourt _court) {
        this._court = _court;
        this._players[0] = new Player(((String[])request.get("names"))[0]);
        this._players[1] = new Player(((String[])request.get("names"))[1]);
        _init_start_conditions();
        _court.show();
        Log.w("init_court", "launchered");
    }



    public void set_is_tiebreak(boolean _is_tiebreak) {
        this._is_tiebreak = _is_tiebreak;
    }

}

