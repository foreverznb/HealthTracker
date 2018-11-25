package com.example.healthtracker;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.util.Objects;

public class TakePhotoActivity extends AppCompatActivity {

    Uri imageFileUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        ImageButton button = findViewById(R.id.take_photo_button);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v){
                takeAPhoto();
            }
        };
        button.setOnClickListener(listener);
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    public void takeAPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }

        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
        File imageFile = new File(imageFilePath);
        imageFileUri = Uri.fromFile(imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ImageButton button = findViewById(R.id.take_photo_button);
                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


