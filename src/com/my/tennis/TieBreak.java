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


public class TieBreak extends ACounter {

    public TieBreak()
    {
        _set_start_score(new Runner() {public void run(IPlayer player){player.set_points(0);}});
    }

    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;

        int w = wplayer.get_points();
        int l = lplayer.get_points();
        w ++;
        if ((w + l) % 2 != 0)
        {
            myApp.get_umpire().change_serve();
        }

        if ((w + l) % 6 == 0)
        {
            // change sides after every six points:
            myApp.get_umpire().change_sides();
        }

        if (w >= 7 && w - l > 1)
        {
            result = true;
            // тот кто подавал в тай брейке первым, в след. гейме должен принимать:
            // player who was serving the first in tiebreak,  is to serve in next game:
            if ((w + l) % 4 == 0 || (w + l) % 4 == 3 )
            {
                // сет закончился на подаче игрока, который первый подавал, передаем подачу:
                // set has finished on that player's serve, who was serving the first, change serve:
                myApp.get_umpire().change_serve();
            }
            // возвращаем расположение сторон в исходную перед началом тай брейка:
            // get back player's sides to the state before tie break:
            if (((int)Math.floor(((double)(w + l)) / 6)) % 2 == 1 )
            {
                // было нечетное число смен сторон в течение тайбрейка:
                // there was odd amount of side changes during tie break:
                myApp.get_umpire().change_sides();
            }
        }
        wplayer.set_points(w);
        lplayer.set_points(l);
        if (result == false || myApp.get_umpire().get_serving_box() == 2)
        {
            myApp.get_umpire().change_serving_box();
        }
        return result;
    }

}
