package com.example.tennis;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.08.13
 * Time: 1:26
 * To change this template use File | Settings | File Templates.
 */
public class Match extends ACounter {

    public Match(ACounter set)
    {
        _child = set;
        _set_start_score(new Runner() {public void run(IPlayer player){player.set_sets("0");}});
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
        return result;
    }
}
