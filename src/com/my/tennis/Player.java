package com.my.tennis;

import java.util.Vector;


public class Player implements IPlayer {
    private String _name;
    private Integer _points;
    private Vector<Integer> _games;
    private Integer _sets;

    public void set_name(String name) {
        this._name = name;
    }

    public String get_name() {
        return _name;
    }

    public Integer get_points() {
        return _points;
    }

    public void set_points(Integer points) {
        this._points = points;
    }

    public Vector<Integer> get_games() {
        return _games;
    }


    public void set_games(Vector<Integer> games) {
        _games = games;
    }

    public Integer get_sets() {
        return _sets;
    }

    public void set_sets(Integer sets) {
        this._sets = sets;
    }
}
