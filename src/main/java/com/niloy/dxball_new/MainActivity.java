package com.niloy.dxball_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume(){
        super.onResume();
        try{
            setContentView(new GameCanvas(this));
        }
        catch (Exception e){
            Log.d("Exception",e.getMessage());
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }


}
