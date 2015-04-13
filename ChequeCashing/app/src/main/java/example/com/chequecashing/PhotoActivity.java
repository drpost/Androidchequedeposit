package example.com.chequecashing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class PhotoActivity extends ActionBarActivity {

    private static final int CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE = 101;
    // private Uri fileUri;
    private String account;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Intent intent = this.getIntent();
        account = intent.getStringExtra("selectedAccount");
        amount = intent.getStringExtra("amount");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
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

    File imagesFolder = new File(Environment.getExternalStorageDirectory().getPath(), "ChequeCashing");

    public void takeFrontPhoto(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagesFolder.mkdirs(); // <----
        File image = new File(imagesFolder, "image_front.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage); // set the image file name
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void takeBackPhoto(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagesFolder.mkdirs(); // <----
        File image = new File(imagesFolder, "image_back.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage); // set the image file name
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void proceed(View view){
        Intent intent = new Intent(this,ConfirmDeposit_Activity.class);
        intent.putExtra("account",account);
        intent.putExtra("totalAmount",amount);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File imgFile;
        Button btnTakePic = (Button) findViewById(R.id.btnTakePicture);
        Button btnProceed = (Button) findViewById(R.id.btnCancelProceed);
        Button btnProceedFinal = (Button) findViewById(R.id.btnProceed);
        if (requestCode == CAPTURE_FRONT_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_front.jpg");
                btnTakePic.setText("Retake Picture");
                btnProceed.setVisibility(View.VISIBLE);
                btnProceed.setText("Take Back Picture");
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.camera_preview);

                    int nh = (int) ( myBitmap.getHeight() * (512.0 / myBitmap.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 512, nh, true);
                    myImage.setImageBitmap(scaled);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        } else if (requestCode == CAPTURE_BACK_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/ChequeCashing/image_back.jpg");
                btnTakePic.setText("Retake Back Picture");
                btnProceedFinal.setVisibility(View.VISIBLE);
                btnProceed.setVisibility(View.INVISIBLE);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.camera_preview);
                    int nh = (int) ( myBitmap.getHeight() * (512.0 / myBitmap.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 512, nh, true);
                    myImage.setImageBitmap(scaled);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }
}
