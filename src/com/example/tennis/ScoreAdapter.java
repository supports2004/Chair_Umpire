package com.example.tennis;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 24.08.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class ScoreAdapter extends BaseAdapter
{
    private Context mContext;
    private Integer mCols, mRows;

    public ScoreAdapter(Context context, int cols, int rows)
    {
        mContext = context;
        mCols = cols;
        mRows = rows;
    }

    @Override
    public int getCount() {
        return mCols * mRows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView view; // выводиться у нас будет картинка

        if (convertView == null)
            view = new TextView(mContext);
        else
            view = (TextView)convertView;

        view.setText("hello");

        return view;
    }
}

