package com.example.junShape;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.example.junShape.GameManager;

public class MainActivity extends Activity {
    GameManager mGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.i("debug:", size.toString());

        mGameManager = new GameManager(this, size.x, size.y);
        setContentView(mGameManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameManager.resume();
    }

    @Override
    protected  void onPause() {
        super.onPause();
        mGameManager.pause();
    }
}
