package com.my.tennis;


public abstract class ACounter {


    protected ACounter _child = null;

    public boolean finish(IPlayer wplayer, IPlayer lplayer)
    {
        if (_child == null || _child.finish(wplayer, lplayer))
        {
            return _count(wplayer, lplayer);
        }
        return false;
    }

    static void _set_start_score(Runner runner)
    {
        IPlayer[] players = myApp.get_umpire().get_players();
        for (Integer i = 0; i < players.length; i ++)
        {
            runner.run(players[i]);
        }
    }



    protected abstract boolean _count(IPlayer wplayer, IPlayer lplayer);

}

interface Runner {
public void run(IPlayer player);
}


