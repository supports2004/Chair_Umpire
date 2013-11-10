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

public class Set extends ACounter {
    private int _games_to_win;  // default = 6
    public Set(int games_to_win)
    {
        _set_start_score(new Runner() {
            public void run(IPlayer player) {
                Vector<Integer> games = player.get_games();
                games.add(0);
                player.set_games(games);
            }
        });
        _child = myApp.get_umpire().create_game();
        _games_to_win = games_to_win;
    }


    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Vector<Integer> w_games =  wplayer.get_games();
        Vector<Integer> l_games =  lplayer.get_games();
        int set_number = w_games.size() - 1;
        int w = w_games.elementAt(set_number);
        int l = l_games.elementAt(set_number);
        w ++;
        w_games.set(set_number, w);
        l_games.set(set_number, l);
        wplayer.set_games(w_games);
        lplayer.set_games(l_games);
        if ((w + l) % 2 != 0)
        {
            myApp.get_umpire().change_sides();
        }

        if ((w == _games_to_win && w - l > 1) || w == _games_to_win + 1)
        {
            result = true;
        }
        else if(w == _games_to_win && l == _games_to_win)
        {
            _child = myApp.get_umpire().create_tiebreak();
        }
        else
        {
            _child = myApp.get_umpire().create_game();
        }



        return result;
    }


}