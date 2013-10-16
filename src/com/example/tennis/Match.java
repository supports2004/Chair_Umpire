package com.example.tennis;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.08.13
 * Time: 1:26
 * To change this template use File | Settings | File Templates.
 */
public class Match extends ACounter {

    public Match()
    {
        _set_start_score(new Runner() {public void run(IPlayer player){
            player.set_sets("0");
            Vector<String> games = new Vector<String>();
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
        wplayer.set_sets(String.valueOf(w));
        lplayer.set_sets(String.valueOf(l));
        _child = myApp.get_umpire().create_set();
        return result;
    }
}
