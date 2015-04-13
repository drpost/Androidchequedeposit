package example.com.chequecashing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;


public class TermsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terms, menu);
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

    public void Accept (View view){
        String textDate = Calendar.getInstance().getTime().toString();
        SharedPreferences pref =
                getSharedPreferences("ChequeCashing", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("AcceptTime", textDate );
        edit.putString("CardNumber",this.getIntent().getStringExtra("CARD_NUMBER"));
        edit.commit();
        Intent mainScreenIntent = new Intent(this,MainScreenActivity.class);
        startActivity(mainScreenIntent);

    }

    public void Cancel(View view){
        Intent back = new Intent(this,LoginActivity.class);
        startActivity(back);
    }
}
