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
        _set_start_score(new Runner() {
            public void run(IPlayer player) {
                Vector<String> games = new Vector<String>();
                games.add("0");
                player.set_games(games);
            }
        });
    }


    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Vector<String> w_games = (Vector<String>) wplayer.get_games();
        Vector<String> l_games = (Vector<String>) lplayer.get_games();
        int _set_number = w_games.size() - 1;
        int w = (int) Integer.valueOf(w_games.elementAt(_set_number));
        int l = (int) Integer.valueOf(l_games.elementAt(_set_number));
        w ++;
        if ((w + l) % 2 != 0)
        {
            Umpire.get_instance().change_sides();
        }

        if ((w == 6 && w - l > 1) || w == 7)
        {
            if (w == 7)
            {
                // закончился тай брейк, следующий гейм - обычный:
                _child = new Game();
            }
            w_games.set(_set_number, String.valueOf(w));
            w = l = 0;
            _set_number ++;
            w_games.add(null);
            l_games.add(null);
            result = true;
        }
        else if(w == 6 && l == 6)
        {
            _child = new TieBreak();
        }


        w_games.set(_set_number, String.valueOf(w));
        l_games.set(_set_number, String.valueOf(l));
        wplayer.set_games(w_games);
        lplayer.set_games(l_games);
        return result;
    }


}