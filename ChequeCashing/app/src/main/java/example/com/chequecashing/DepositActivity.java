package example.com.chequecashing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.chequecashing.account.Account;
import example.com.chequecashing.account.UserInfo;


public class DepositActivity extends ActionBarActivity {

    UserInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        SharedPreferences pref = getSharedPreferences("ChequeCashing", MODE_PRIVATE);
        //Communicate with server to retrieve info

        user = (UserInfo)getIntent().getSerializableExtra("UserInfo");
        //user = new UserInfo();
        //user.setAccessCard(pref.getString("CardNumber","empty"));
//        ArrayList<Account> accounts = new ArrayList<Account>();
//        Account account1 = new Account();
//        account1.setAccountNumber("11112222333444");
//        account1.setAccountType("Chequings");
//        accounts.add(account1);
//        Account account2 = new Account();
//        account2.setAccountNumber("4444555566661111");
//        account2.setAccountType("Savings");
//        accounts.add(account2);
//        user.setAccountList(accounts);
        createRadioButton();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deposit, menu);
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

    private void createRadioButton() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioAccounts);
        rg.setOrientation(RadioGroup.VERTICAL);
        int i = 0;
        for (Account account : user.getAccountList()){
            RadioButton rb  = new RadioButton(this);
            rb.setText(account.getAccountNumber() + " "+ account.getAccountType());
            rg.addView(rb);
        }
    }

    public void makeDeposit(View view){
        Intent photoIntent = new Intent(this,PhotoActivity.class);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioAccounts);
        TextView txtamount = (TextView)findViewById(R.id.amount);
        if(radioGroup.getCheckedRadioButtonId() < 0){
            Toast.makeText(this,"Please select an Account",Toast.LENGTH_LONG).show();
        }else if (txtamount.getText().length() == 0){
            Toast.makeText(this,"Please input the Amount",Toast.LENGTH_LONG).show();
        }else {

            RadioButton selectedRadio = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            photoIntent.putExtra("selectedAccount", selectedRadio.getText());

            photoIntent.putExtra("amount", txtamount.getText().toString());
            startActivity(photoIntent);
        }
    }

}
