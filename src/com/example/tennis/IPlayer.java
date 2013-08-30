package com.example.tennis;

import android.os.Parcelable;

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

    void set_points(String points);
    String get_points();

    void set_games(String games);
    String get_games();

    void set_sets(String sets);
    String get_sets();


}
