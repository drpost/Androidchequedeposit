package example.com.chequecashing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import example.com.chequecashing.Async.PhotoAsyncTask;


public class ConfirmDeposit_Activity extends ActionBarActivity {

    String amount;
    String account;
    String accountType;
    byte[] photo1;
    byte[] photo2;
    String sPhoto1;
    String sPhoto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_deposit_);

        File imgFileFront = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_front.jpg");
        File imgFileBack = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_back.jpg");
        if (imgFileFront.exists() && imgFileBack.exists()) {
            Bitmap myBitmapFront = BitmapFactory.decodeFile(imgFileFront.getAbsolutePath());
            ImageView myImageFront = (ImageView)  findViewById(R.id.imageViewFront);
            int nh = (int) ( myBitmapFront.getHeight() * (512.0 / myBitmapFront.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(myBitmapFront, 512, nh, true);
            myImageFront.setImageBitmap(scaled);

            Bitmap myBitmapBack = BitmapFactory.decodeFile(imgFileBack.getAbsolutePath());
            ImageView myImageBack = (ImageView)  findViewById(R.id.imageViewBack);
            int nh2 = (int) ( myBitmapBack.getHeight() * (512.0 / myBitmapBack.getWidth()) );
            Bitmap scaled2 = Bitmap.createScaledBitmap(myBitmapBack, 512, nh, true);
            myImageBack.setImageBitmap(scaled2);
            photo1 = getBytesFromBitmap(myBitmapFront);
            photo2 = getBytesFromBitmap(myBitmapBack);
            // get the base 64 string
            sPhoto1 = Base64.encodeToString(photo1,Base64.NO_WRAP);
            sPhoto2 = Base64.encodeToString(photo2,Base64.NO_WRAP);
        }
        Intent intent = this.getIntent();
        TextView txtAmount = (TextView) findViewById(R.id.amount);
        TextView txtAccount = (TextView) findViewById(R.id.account);
        accountType = intent.getStringExtra("account").split(" ")[0];
        account = intent.getStringExtra("account").replace(accountType,"");
        amount = intent.getStringExtra("totalAmount");
        txtAmount.setText(amount);
        txtAccount.setText(account);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirm_deposit_, menu);
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

    public void redo(View view){
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra("amount",amount);
        intent.putExtra("account",account);
        intent.putExtra("accountType",accountType);
        startActivity(intent);
    }

    public void submit(View view){
        Intent intent = new Intent(this, SubmitActivity.class);
        intent.putExtra("transactId",amount);
        SharedPreferences pref = getSharedPreferences("ChequeCashing", MODE_PRIVATE);
        String accessCard = pref.getString("CardNumber","empty");
        PhotoAsyncTask photoAsyncTask = new PhotoAsyncTask(accessCard,sPhoto1, sPhoto2, amount, account, accountType);
        photoAsyncTask.execute();
        startActivity(intent);
    }

    private static final int CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE = 101;
    File imagesFolder = new File(Environment.getExternalStorageDirectory().getPath(), "ChequeCashing");
    public void retakeFrontPhoto(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagesFolder.mkdirs(); // <----
        File image = new File(imagesFolder, "image_front.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage); // set the image file name
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void retakeBackPhoto(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagesFolder.mkdirs(); // <----
        File image = new File(imagesFolder, "image_back.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage); // set the image file name
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File imgFile;
        if (requestCode == CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_front.jpg");
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.imageViewFront);
                    myImage.setImageBitmap(myBitmap);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        } else if (requestCode == CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_back.jpg");
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.imageViewBack);
                    myImage.setImageBitmap(myBitmap);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    // convert from bitmap to byte array
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }


}
