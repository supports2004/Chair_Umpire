package com.my.tennis;


import android.util.Log;

import java.util.Hashtable;
import java.util.Map;
import java.util.Observable;
import java.util.Vector;



public class Umpire extends Observable implements IUmpire {
    public Map<String,  Object> request;
    private IPlayer[] _players = {null, null};
    private IPlayer   _left_player;
    private IPlayer   _right_player;
    private IPlayer   _serving_player;
    private IPlayer   _returning_player;
    private Integer  _serving_box;
    private Match _match;
    private ICourt _court;
    private Vector<Integer> _history;

    public Umpire()
    {
        request = new Hashtable<String, Object>();
        String[] names = {"", ""};
        request.put("names", names);
        request.put("player1_side", 0);
        request.put("player1_is_serve", true);
        request.put("AD", true);
        request.put("games_in_set", 6);
        request.put("toast", true);
     //   Log.w("Umpire construct", "launchered");
    }

    public Map<String,  Object> request()
    {
        return request;
    }


    @Override
    public void add_point(IPlayer win_player)
    {
        _history.add(win_player == _players[0] ? 0 : 1);
        _match.finish(win_player, _left_player == win_player ? _right_player : _left_player);
        notify(Event.NEW_POINT);
        _court.show();
        //Log.w("Empire add_point", "launchered");
    }

    @Override
    public boolean undo()
    {
        if (_history.size() >= 1)
        {
            notify(Event.UNDO_START);
            _init_start_conditions();
            if (_history.size() > 1)
            {
                for (Integer i = 0; i < _history.size() - 1; i ++)
                {
                    _match.finish(_players[_history.get(i)], _players[_history.get(i) == 1 ? 0 : 1]);
                    notify(Event.NEW_POINT);
                }
            }
            _history.remove(_history.size() - 1);
            notify(Event.UNDO_END);
            _court.show();
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void init(ICourt _court)
    {
        _history = new Vector<Integer>();
        this._court = _court;
        this._players[0] = myApp.create_player();
        this._players[1] = myApp.create_player();
        this._players[0].set_name(((String[]) request.get("names"))[0]);
        this._players[1].set_name(((String[]) request.get("names"))[1]);
        if (((Boolean) request.get("toast")))
        {
            myApp.create_toast();
        }
        _init_start_conditions();
        _court.show();
        //Log.w("init", "launchered");
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
        if (((Boolean) request.get("player1_is_serve")))
        {
            _serving_player = _players[0];
            _returning_player = _players[1];
        }
        else
        {
            _serving_player = _players[1];
            _returning_player = _players[0];
        }
        _serving_box = 1;
        _match = _create_match();
        notify(Event.NEW_POINT);

    }


    @Override
    public IPlayer[] get_players()
    {
        return _players;
    }

    @Override
    public IPlayer get_left_player() {
        return _left_player;
    }

    @Override
    public IPlayer get_right_player() {
        return _right_player;
    }

    @Override
    public IPlayer get_serving_player() {
        return _serving_player;
    }

    @Override
    public IPlayer get_returning_player() {
        return _returning_player;
    }

    @Override
    public Integer get_serving_box()
    {
        return _serving_box;
    }

    public ICourt get_court() {
        return _court;
    }

    @Override
    public void change_serve()
    {
        notify(Event.CHANGE_SERVE);
        if (_serving_player == _left_player)
        {
            _serving_player = _right_player;
            _returning_player = _left_player;
        }
        else
        {
            _serving_player = _left_player;
            _returning_player = _right_player;
        }
    }

    @Override
    public void change_serving_box()
    {
        _serving_box = _serving_box == 1 ? 2 : 1;
    }

    @Override
    public void change_sides()
    {
        notify(Event.CHANGE_SIDES);
        IPlayer left_player = _left_player;
        _left_player = _right_player;
        _right_player = left_player;
    }

    public void notify(Event event)
    {
        setChanged();
        notifyObservers(event);
    }

    private Match _create_match()
    {
        notify(Event.NEW_MATCH);
        return myApp.create_match();
    }

    @Override
    public Set create_set()
    {
        notify(Event.NEW_SET);
        return myApp.create_set((Integer) request.get("games_in_set"));
    }

    @Override
    public Game create_game()
    {
        notify(Event.NEW_GAME);
        return myApp.create_game((Boolean) request.get("AD"));
    }

    @Override
    public TieBreak create_tiebreak()
    {
        notify(Event.NEW_TIEBREAK);
        return myApp.create_tiebreak();
    }



}

