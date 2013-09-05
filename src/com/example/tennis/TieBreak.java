package com.example.tennis;

import com.example.tennis.ACounter;
import com.example.tennis.IPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.08.13
 * Time: 1:08
 * To change this template use File | Settings | File Templates.
 */
public class TieBreak extends ACounter {



    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;

        int w = Integer.valueOf(wplayer.get_points());
        int l = Integer.valueOf(lplayer.get_points());
        w ++;
        if ((w + l) % 2 != 0)
        {
            Umpire.get_instance().change_serve();
        }

        if ((w + l) % 6 == 0)
        {   // меняем сторонами после каждых 6 розыгрышей:
            Umpire.get_instance().change_sides();
        }

        if (w >= 7 && w - l > 1)
        {
            w = l = 0;
            result = true;
            // тот кто подавал в тай брейке первым, в след. гейме должен принимать:
            if ((w + l) % 4 == 0 || (w + l) % 4 == 3 )
            {
                // сет закончился на подаче игрока, который первый подавал, передаем подачу:
                Umpire.get_instance().change_serve();
            }
            // возвращаем расположение сторон в исходную перед началом тай брейка:
            if (((int)Math.floor(((double)(w + l)) / 6)) % 2 == 1 )
            {
                // было нечетное число смен сторон в течение тайбрейка:
                Umpire.get_instance().change_sides();
            }
        }
        wplayer.set_points(String.valueOf(w));
        lplayer.set_points(String.valueOf(l));
        if (result == false || Umpire.get_instance().get_serving_box() == 2)
        {
            Umpire.get_instance().change_serving_box();
        }
        return result;
    }

}
