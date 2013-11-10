package com.my.tennis;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.10.13
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
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
