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
