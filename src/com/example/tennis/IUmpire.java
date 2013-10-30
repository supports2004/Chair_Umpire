package com.example.tennis;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 01.10.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public interface IUmpire {

    public enum Event
    {
        NEW_MATCH, NEW_SET, NEW_GAME, NEW_TIEBREAK, NEW_POINT, CHANGE_SERVE, CHANGE_SIDES, UNDO_START, UNDO_END, DECIDING_POINT
    }

    Map<String,  Object> request();
    void add_point(IPlayer win_player);

    boolean undo();

    IPlayer[] get_players();

    IPlayer get_left_player();

    IPlayer get_right_player();

    IPlayer get_serving_player();

    IPlayer get_returning_player();

    public ICourt get_court();

    Integer get_serving_box();

    void change_serve();

    void change_serving_box();

    void change_sides();

    Set create_set();

    Game create_game();

    TieBreak create_tiebreak();

    void init(ICourt _court);

    public void notify(Event event);
    public void addObserver(java.util.Observer observer);
    public void deleteObservers();

}
