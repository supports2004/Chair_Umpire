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
    private String _points;
    private Object _games;
    private String _sets;
    Player(String name)
    {
        this.set_name(name);
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_name() {
        return _name;
    }

    public String get_points() {
        return _points;
    }

    public void set_points(String points) {
        this._points = points;
    }

    public Object get_games() {
        return _games;
    }


    public void set_games(Object games) {
        _games = games;
    }

    public String get_sets() {
        return _sets;
    }

    public void set_sets(String sets) {
        this._sets = sets;
    }
}
