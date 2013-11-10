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


public class Helper {
    static private Hashtable<Integer, String[]> _spellings = new Hashtable<Integer, String[]>();

    static public String spell_the_digit(Integer d, int resource_id)
    {
        if (!_spellings.containsKey(resource_id))
        {
            _spellings.put(resource_id, myApp.get_instance().getResources().getStringArray(resource_id));
        }

        //  d = -- d;
        if (d >= _spellings.get(resource_id).length )
        {
            return d.toString();
        }
        else
        {
            return _spellings.get(resource_id)[d];
        }
    }
}
