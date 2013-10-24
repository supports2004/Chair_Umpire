package com.example.tennis;

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
        NEW_MATCH, NEW_SET, NEW_GAME, NEW_TIEBREAK, CHANGE_SERVE, CHANGE_SIDES
    }
    void add_point(IPlayer win_player);

    boolean undo();

    IPlayer[] get_players();

    IPlayer get_left_player();

    IPlayer get_right_player();

    IPlayer get_serving_player();

    Integer get_serving_box();

    void change_serve();

    void change_serving_box();

    void change_sides();

    Set create_set();

    Game create_game();

    TieBreak create_tiebreak();

    void init_court(ICourt _court);

}
