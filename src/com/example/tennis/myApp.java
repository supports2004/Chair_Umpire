package com.example.tennis;
import android.app.Application;


public class myApp extends Application {
    private static Umpire _umpire;

    public final void onCreate()
    {
        super.onCreate();
        _umpire = new Umpire();
    }

    public static Umpire get_umpire()
    {
        return _umpire;
    }

    public static IPlayer create_player()
    {
        return new Player();
    }


    public static Match create_match()
    {
        return new Match();
    }

    public static Set create_set()
    {
        return new Set();
    }

    public static Game create_game()
    {
        return new Game();
    }

    public static TieBreak create_tiebreak()
    {
        return new TieBreak();
    }

}

