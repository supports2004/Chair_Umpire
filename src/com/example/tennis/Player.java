package com.example.tennis;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 10.08.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 * List<List<String>> words = new ArrayList<>();
 */
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
