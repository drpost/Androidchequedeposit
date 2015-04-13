package example.com.chequecashing;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainScreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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

    public void DepositAction(View view){
        Intent depositIntent = new Intent(this, DepositActivity.class);
        depositIntent.putExtra("UserInfo",getIntent().getSerializableExtra("UserInfo"));
        startActivity(depositIntent);
    }

    public void MessageAction(View view){
        Intent messagesIntent = new Intent(this,MessagesActivity.class);
        messagesIntent.putExtra("UserInfo",getIntent().getSerializableExtra("UserInfo"));
        startActivity(messagesIntent);
    }
    public void LanguageAction(View view){
        Intent languageIntent = new Intent(this,LanguageActivity.class);
        languageIntent.putExtra("UserInfo",getIntent().getSerializableExtra("UserInfo"));
        startActivity(languageIntent);
    }

    public void HelpAction(View view){
        Intent helpIntent = new Intent(this,HelpActivity.class);
        helpIntent.putExtra("UserInfo",getIntent().getSerializableExtra("UserInfo"));
        startActivity(helpIntent);
    }
}
