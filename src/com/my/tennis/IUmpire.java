/*
Copyright © 2013 Alexei A
supports2004@mail.ru

This file is part of Chair Umpire(tennis score).

    Chair Umpire(tennis score) is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Chair Umpire(tennis score) is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Chair Umpire(tennis score).  If not, see <http://www.gnu.org/licenses/>.
*/

package com.my.tennis;

import java.util.Map;


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
