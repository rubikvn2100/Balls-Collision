package com.example.rubikvn2100.myfirstgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_menu );

        Button button1 = findViewById( R.id.button_1 );
        Button button2 = findViewById( R.id.button_2 );
        Button button3 = findViewById( R.id.button_3 );

        button1.setOnClickListener( this );
        button2.setOnClickListener( this );
        button3.setOnClickListener( this );
    }

    @Override
    public void onClick( View view )
    {
        switch( view.getId() )
        {
            case R.id.button_1:
            {
                openEmptyField();
                break;
            }

            case R.id.button_2:
            {
                openChaosField();
                break;
            }

            case R.id.button_3:
            {
                openCustomFieldGetData();
                break;
            }
        }
    }

    public void openEmptyField()
    {
        Intent intent = new Intent( this, EmptyField.class );
        startActivity( intent );
    }

    public void openChaosField()
    {
        Intent intent = new Intent( this, ChaosField.class );
        startActivity( intent );
    }

    public void openCustomFieldGetData()
    {
        Intent intent = new Intent( this, CustomFieldGetData.class );
        startActivity( intent );
    }
}
