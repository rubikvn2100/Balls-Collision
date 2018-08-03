package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class CustomFieldGetData extends AppCompatActivity implements View.OnClickListener
{
    private static final int N  = 4;
    private Button[] subButtons = new Button[N];
    private Button[] addButtons = new Button[N];
    private EditText[] editTextRadius = new EditText[N];
    private EditText[] editTextAmounts= new EditText[N];
    private Button generateButton;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        //turn title off
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        //set to full screen
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.activity_custom_field_get_data );

        String buttonId;
        String editTextId;
        for( int i = 0; i < 4; i++ )
        {
            buttonId = "button_subtract_" + i;
            int resId = getResources().getIdentifier( buttonId, "id", getPackageName() );
            subButtons[i] = findViewById( resId);
            subButtons[i].setOnClickListener( this );

            buttonId = "button_add_" + i;
            resId = getResources().getIdentifier( buttonId, "id", getPackageName() );
            addButtons[i] = findViewById( resId );
            addButtons[i].setOnClickListener( this );

            editTextId = "input_radius_" + i;
            resId = getResources().getIdentifier( editTextId, "id", getPackageName() );
            editTextRadius[i] = findViewById( resId );

            editTextId = "input_amount_" + i;
            resId = getResources().getIdentifier( editTextId, "id", getPackageName() );
            editTextAmounts[i] = findViewById( resId );
        }

        generateButton = findViewById( R.id.button_generate );
        generateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomField();
            }
        });
    }

    @Override
    public void onClick( View view )
    {
        int currentValue;
        int resId;
        String buttonId;
        for( int i = 0; i < N; i++ )
        {
            buttonId = "button_subtract_" + i;
            resId = getResources().getIdentifier( buttonId, "id", getPackageName() );

            if( resId == view.getId() )
            {
                currentValue = Integer.parseInt( editTextAmounts[i].getText().toString() );
                if( currentValue > 0 )
                {
                    editTextAmounts[i].setText( String.valueOf( --currentValue ) );
                }
            }


            buttonId = "button_add_" + i;
            resId = getResources().getIdentifier( buttonId, "id", getPackageName() );

            if( resId == view.getId() )
            {
                currentValue = Integer.parseInt( editTextAmounts[i].getText().toString() );
                if( currentValue < 300 )
                {
                    editTextAmounts[i].setText( String.valueOf( ++currentValue ) );
                }
            }
        }
    }

    public void openCustomField()
    {
        Intent intent = new Intent( this, CustomField.class );
        String name;
        int value;
        for( int i = 0 ; i < N; i++ )
        {
            name = "radius_" + i;
            value = Integer.parseInt( editTextRadius[i].getText().toString() );
            intent.putExtra( name, value );

            name = "amount_" + i;
            value = Integer.parseInt(editTextAmounts[i].getText().toString());
            intent.putExtra( name, value );
        }
        startActivity( intent );
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
