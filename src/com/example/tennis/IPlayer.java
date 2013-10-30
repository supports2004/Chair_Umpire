package com.example.tennis;

import android.os.Parcelable;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.08.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
interface IPlayer  {
    void set_name(String name);
    String get_name();

    void set_points(Integer points);
    Integer get_points();

    void set_games(Vector<Integer> games);
    Vector<Integer> get_games();

    void set_sets(Integer sets);
    Integer get_sets();


}
