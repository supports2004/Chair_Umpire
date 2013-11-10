package com.my.tennis;



import java.util.Vector;


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
