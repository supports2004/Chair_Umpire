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

import java.util.Hashtable;
import java.util.Map;

public class Game extends ACounter {
    private boolean _AD = true;
    static protected Map<String,  Integer> _visual_int = null;
    static protected Map<Integer, String>  _int_visual = null;

    public Game(boolean AD)
    {
        _AD = AD;
        if (_visual_int == null)
        {
            _visual_int = new Hashtable<String, Integer>();
            _visual_int.put("0",   0);
            _visual_int.put("15",  1);
            _visual_int.put("30",  2);
            _visual_int.put("40",  3);
            _visual_int.put("",    3);
            _visual_int.put("AD",  4);

            _int_visual = new Hashtable<Integer, String>();
            _int_visual.put(0, "0");
            _int_visual.put(1, "15");
            _int_visual.put(2, "30");
            _int_visual.put(3, "40");
            _int_visual.put(35, "");
            _int_visual.put(4, "AD");


        }
        _set_start_score(new Runner() {public void run(IPlayer player){player.set_points(0);}});

    }

    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Integer w = (wplayer.get_points());
        Integer l = (lplayer.get_points());
        w ++;
        if (!_AD)
        {
            if (w == 3 && l == 3)
            {
                myApp.get_umpire().notify(IUmpire.Event.DECIDING_POINT);
            }
            if (w > 3)
            {
                result = true;
            }
        }
        else
        {
            if (w > 3 && w - l > 1)
            {
                result = true;
            }
            if (w == 4 && l == 4)
            {
                w --;
                l --;
            }
        }

        wplayer.set_points(w);
        lplayer.set_points(l);
        if (result == false || myApp.get_umpire().get_serving_box() == 2)
        {
            myApp.get_umpire().change_serving_box();
        }
        if (result == true)
        {
            myApp.get_umpire().change_serve();
        }
        return result;
    }

    protected Integer _to_int(String points)
    {
        if (!_visual_int.containsKey(points))
        {
         //   throw new Exception ("Visual point value " + points + " doesn`t exist");
        }
        return _visual_int.get(points);
    }

    protected String _to_visual(Integer points)
    {
        return _int_visual.get(points);
    }
}
