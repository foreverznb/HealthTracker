package com.example.healthtracker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    String pathTaken;
    String pathLoaded;
    String path = "/storage/self/primary/Download/";
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
                Toast.makeText(this, "Photo Saved", Toast.LENGTH_SHORT).show();
                Bitmap image = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                assert image != null;
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, 300, 300, true);
                Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                pathLoaded=saveToInternalStorage(rotatedBitmap);
                loadImageFromStorage(pathLoaded);
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
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                    pathLoaded=saveToInternalStorage(rotatedBitmap);
                    loadImageFromStorage(pathLoaded);
                    textTargetUri.setText(pathLoaded);
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

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/user/0/com.example.healthtracker/app_imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CANADA).format(new Date());
        imageName = "test_"+timeStamp+".jpg";
        File mypath=new File(directory,imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path) {
        try {
            File f=new File(path, imageName);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=findViewById(R.id.imageView);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private String imageToString(String path, String imageName){
        String filepath = path+imageName;
        File imagefile = new File(filepath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private Bitmap stringToImage(String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}


