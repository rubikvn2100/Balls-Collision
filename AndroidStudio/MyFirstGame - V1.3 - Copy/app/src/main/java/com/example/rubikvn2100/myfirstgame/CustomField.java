package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class CustomField extends AppCompatActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        //turn title off
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        //set to full screen
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.activity_custom_field );
        //setContentView( new CustomFieldPanel(this ) );
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if( id == R.id.action_settings )
        //{
        //    return true;
        //}

        return super.onOptionsItemSelected( item );
    }
}
