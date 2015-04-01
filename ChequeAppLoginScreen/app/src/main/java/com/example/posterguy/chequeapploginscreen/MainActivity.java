package com.example.posterguy.chequeapploginscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkTermsFlag();
        checkLangflag();
        Intent i = new Intent(MainActivity.this, TermsandCondActivity.class);
        startActivity(i);

    }
    private void checkLangflag()
    {
        SharedPreferences pref = getSharedPreferences("ChequeInfo", MODE_PRIVATE);
        String extractedText = pref.getString("LangFlag","");
        if (!extractedText.equals("F"))
        {
            return;

        }
        else
        {
            TextView cardText = (TextView) findViewById(R.id.cardText);
            cardText.setText("Se il vous plaît entrer votre numéro de carte de convenince");
            TextView passwordText = (TextView) findViewById(R.id.passwordTxt);
            passwordText.setText("Se il vous plaît entrer votre mot de passe en ligne");
            Button submitBtn = (Button) findViewById(R.id.submitBtn);
            submitBtn.setText("Soumettre");
            Button languageBtn = (Button) findViewById(R.id.languageBtn);
            languageBtn.setText("Langue");
            Button helpBtn = (Button) findViewById(R.id.helpBtn);
            helpBtn.setText("Aidez");

        }


    }
    public void startActivity(Intent intent)
    {}
    private void checkTermsFlag()
    {
        SharedPreferences pref = getSharedPreferences("ChequeInfo", MODE_PRIVATE);
        String extractedText = pref.getString("TermsFlag","");

        if (!extractedText.equals("Y"))
        {
           Intent g = new Intent(MainActivity.this, TermsandCondActivity.class);
            startActivity(g);

        }
        else
        {
            return;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void submitClick(View V)
    {



        //need an http client - using the URL to send it.  Need to pass the information.
        //some needs to supply the url for the web server.  Address parameter.
        //pictures - convert the jpeg to binary to string. i.e. decode jpeg to binary, convert binary to string, convert string to binary.
        //posting image from android to wcf rest service


    }
}
