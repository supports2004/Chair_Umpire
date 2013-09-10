package com.example.tennis;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 25.08.13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class ACounter {
    public ACounter get_child() {
        return _child;
    }

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
        IPlayer[] players = Umpire.get_instance().get_players();
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


