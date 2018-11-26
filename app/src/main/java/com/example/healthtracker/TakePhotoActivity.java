package com.example.healthtracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TakePhotoActivity extends AppCompatActivity {

    Uri imageFileUri;
    TextView textTargetUri;
    ImageView imageView;
    String imageName;
    Integer number = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);
        Button loadPhotoButton = findViewById(R.id.photo_from_library_button);
        textTargetUri = findViewById(R.id.test);
        imageView = findViewById(R.id.imageView);

        takePhotoButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeAPhoto();
            }
        });

        loadPhotoButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAPhoto();
            }
        });
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    public void takeAPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CANADA).format(new Date());
        imageName = "test_"+timeStamp+".jpg";
        File imageFile = new File(folder, imageName);
        imageFileUri = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void loadAPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 50);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            // Can't display the photo in a text view for whatever reason
            if (resultCode == RESULT_OK) {

                String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
                File imageFile = new File(folder, imageName);
                imageFileUri = Uri.fromFile(imageFile);

                // update photo galleries
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
                Toast.makeText(this, "Photo Saved", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == 50) {
            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
                assert targetUri != null;
                Bitmap bitmap;

                try {
                    // Rotate the imageView displayed since camera produces an incorrectly orientated image
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    String bytes= String.valueOf(bitmap.getByteCount());
                    textTargetUri.setText(bytes);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                    imageView.setImageBitmap(rotatedBitmap);
                    //rotatedBitmap.getByteCount();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this, "Load Photo Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


