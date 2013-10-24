package com.example.tennis;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.08.13
 * Time: 0:57
 * To change this template use File | Settings | File Templates.
 */
public class Set extends ACounter {
    private int _games_to_win;  // default = 6
    public Set(int games_to_win)
    {
        _set_start_score(new Runner() {
            public void run(IPlayer player) {
                Vector<String> games = (Vector<String>) player.get_games();
                games.add("0");
                player.set_games(games);
            }
        });
        _child = myApp.get_umpire().create_game();
        _games_to_win = games_to_win;
    }


    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Vector<String> w_games = (Vector<String>) wplayer.get_games();
        Vector<String> l_games = (Vector<String>) lplayer.get_games();
        int set_number = w_games.size() - 1;
        int w = (int) Integer.valueOf(w_games.elementAt(set_number));
        int l = (int) Integer.valueOf(l_games.elementAt(set_number));
        w ++;
        if ((w + l) % 2 != 0)
        {
            myApp.get_umpire().change_sides();
        }

        if ((w == _games_to_win && w - l > 1) || w == _games_to_win + 1)
        {
            result = true;
        }
        else if(w == _games_to_win && l == _games_to_win)
        {
            _child = myApp.get_umpire().create_tiebreak();
        }
        else
        {
            _child = myApp.get_umpire().create_game();
        }


        w_games.set(set_number, String.valueOf(w));
        l_games.set(set_number, String.valueOf(l));
        wplayer.set_games(w_games);
        lplayer.set_games(l_games);
        return result;
    }


}