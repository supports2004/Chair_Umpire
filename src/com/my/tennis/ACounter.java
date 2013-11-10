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


public abstract class ACounter {


    protected ACounter _child = null;

    public boolean finish(IPlayer wplayer, IPlayer lplayer)
    {
        if (_child == null || _child.finish(wplayer, lplayer))
        {
            return _count(wplayer, lplayer);
        }
        return false;
    }

    static void _set_start_score(Runner runner)
    {
        IPlayer[] players = myApp.get_umpire().get_players();
        for (Integer i = 0; i < players.length; i ++)
        {
            runner.run(players[i]);
        }
    }



    protected abstract boolean _count(IPlayer wplayer, IPlayer lplayer);

}

interface Runner {
public void run(IPlayer player);
}


