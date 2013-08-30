package com.example.tennis;

import android.util.Log;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.08.13
 * Time: 0:57
 * To change this template use File | Settings | File Templates.
 */
public class Set extends ACounter {

    public Set(ACounter game)
    {
        _child = game;
        _set_start_score(new Runner() {public void run(IPlayer player){player.set_games("0");}});
    }

    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Integer w = (Integer) Integer.valueOf(wplayer.get_games());
        Integer l = (Integer) Integer.valueOf(lplayer.get_games());
        w ++;
        if (w >= 6)
        {
            if (w - l > 1 || w == 7)
            {
                w = l = 0;
                result = true;
            }
        }
        if (w == 6 && l == 6)
        {
            _child = new TieBreak();
        }
        wplayer.set_games(String.valueOf(w));
        lplayer.set_games(String.valueOf(l));
        return result;
    }
}