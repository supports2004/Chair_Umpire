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


public class Match extends ACounter {

    public Match()
    {
        _set_start_score(new Runner() {public void run(IPlayer player){
            player.set_sets(0);
            Vector<Integer> games = new Vector<Integer>();
            player.set_games(games);
        }});
        _child = myApp.get_umpire().create_set();
    }


    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Integer w = Integer.valueOf(wplayer.get_sets());
        Integer l = Integer.valueOf(lplayer.get_sets());
        w ++;
        if (w == 2)
        {
            result = true;
        }
        wplayer.set_sets(w);
        lplayer.set_sets(l);
        _child = myApp.get_umpire().create_set();
        return result;
    }
}
