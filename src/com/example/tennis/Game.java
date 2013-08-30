package com.example.tennis;

import android.util.Log;

import java.util.Hashtable;
import java.util.Map;

public class Game extends ACounter {
    static protected Map<String,  Integer> _visual_int = null;
    static protected Map<Integer, String>  _int_visual = null;

    public Game()
    {
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
        _set_start_score(new Runner() {public void run(IPlayer player){player.set_points("0");}});

    }

    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Integer w = _to_int(wplayer.get_points());
        Integer l = _to_int(lplayer.get_points());
        w ++;
        if (w > 3 && w - l > 1)
        {
            result = true;
            w = l = 0;
        }
        if (w == 4 && l == 4)
        {
            w --;
            l --;
        }
        if (w == 4 && l == 3)
        {
            l = 35;     // '40' : 'AD' => '' : 'AD'
        }

        wplayer.set_points(_to_visual(w));
        lplayer.set_points(_to_visual(l));
        if (result == false || Umpire.get_instance().get_serving_box() == 2)
        Umpire.get_instance().change_serving_box();
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
        if (!_int_visual.containsKey(points))
        {
         //   throw new Exception("Integer point value " + points + " doesn`t exist");
        }
        return _int_visual.get(points);
    }
}
