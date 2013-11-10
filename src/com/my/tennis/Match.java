package com.my.tennis;

import java.util.Vector;


public class Match extends ACounter {

    public Match()
    {
        _set_start_score(new Runner() {public void run(IPlayer player){
            player.set_sets(0);
            Vector<Integer> games = new Vector<Integer>();
            player.set_games(games);
        }});
        _child = myApp.get_umpire().create_set();
    }


    protected boolean _count (IPlayer wplayer, IPlayer lplayer)
    {
        boolean result = false;
        Integer w = Integer.valueOf(wplayer.get_sets());
        Integer l = Integer.valueOf(lplayer.get_sets());
        w ++;
        if (w == 2)
        {
            result = true;
        }
        wplayer.set_sets(w);
        lplayer.set_sets(l);
        _child = myApp.get_umpire().create_set();
        return result;
    }
}
