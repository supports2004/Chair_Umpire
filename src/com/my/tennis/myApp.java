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
import android.app.Application;


public class myApp extends Application {
    private static IUmpire _umpire;
    private static myApp _myapp;

    public final void onCreate()
    {
        super.onCreate();
        _umpire = new Umpire();
        _myapp = this;
    }

    public static myApp get_instance()
    {
        return _myapp;
    }

    public static IUmpire get_umpire()
    {
        return _umpire;
    }

    public static IPlayer create_player()
    {
        return new Player();
    }


    public static Match create_match()
    {
        return new Match();
    }

    public static Set create_set(int games_in_set)
    {
        return new Set(games_in_set);
    }

    public static Game create_game(boolean AD)
    {
        return new Game(AD);
    }

    public static TieBreak create_tiebreak()
    {
        return new TieBreak();
    }

    public static Toast create_toast()
    {
        if (myApp.get_instance().getResources().getConfiguration().locale.getLanguage().equals("ru"))
        {
            return new Toast();
        }
        else
        {
            return new Toast_en();
        }
    }

}

