package com.my.tennis;
import android.app.Application;


public class myApp extends Application {
    private static IUmpire _umpire;
    private static myApp _myapp;

    public final void onCreate()
    {
        super.onCreate();
        _umpire = new Umpire();
        _myapp = this;
    }

    public static myApp get_instance()
    {
        return _myapp;
    }

    public static IUmpire get_umpire()
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

    public static Set create_set(int games_in_set)
    {
        return new Set(games_in_set);
    }

    public static Game create_game(boolean AD)
    {
        return new Game(AD);
    }

    public static TieBreak create_tiebreak()
    {
        return new TieBreak();
    }

    public static Toast create_toast()
    {
        if (myApp.get_instance().getResources().getConfiguration().locale.getLanguage().equals("ru"))
        {
            return new Toast();
        }
        else
        {
            return new Toast_en();
        }
    }

}

