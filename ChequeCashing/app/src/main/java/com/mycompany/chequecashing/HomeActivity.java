package com.mycompany.chequecashing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;
import android.view.View;

public class HomeActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RadioGroup rgroup=(RadioGroup)findViewById(R.id.rgAccountNumbers);
        // query account from database
        for(int i=0; i<6; i++)
        {   // loop and add account to radio group
            RadioButton rb=new RadioButton(getApplicationContext());
            rb.setText("rdo"+i);
            rb.setId(i);
            rgroup.addView(rb);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void btn_photo_click(View view) {
        Intent intent = new Intent(this, ActivityPhotoFront.class);
        startActivity(intent);
    }
}
