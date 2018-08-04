package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class TakePictureActivity extends AppCompatActivity {
    ImageButton buttonTakePicture;
    ImageButton buttonAcceptPicture;
    ImageButton buttonSkipPicture;
    ImageView imageViewPictureFromCamera;
    Bitmap pictureBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        initUI();

        // set button functionality
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        buttonAcceptPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pictureBitmap != null){
                    FoundItem.setId();
                    FoundItem.setPicture(encodeImage(pictureBitmap));
                    Intent startAddDescriptionActivity = new Intent(getApplicationContext(), AddDescriptionActivity.class);
                    startActivity(startAddDescriptionActivity);
                } else {
                    //TODO popup: please take a picture of the item
                }

            }
        });
    }

    private void initUI(){
        buttonTakePicture = (ImageButton)findViewById(R.id.buttonTakePicture);
        imageViewPictureFromCamera = (ImageView) findViewById(R.id.imageViewPictureFromCamera);
        buttonAcceptPicture = (ImageButton)findViewById(R.id.buttonAcceptPicture);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            pictureBitmap = (Bitmap) extras.get("data");
            imageViewPictureFromCamera.setImageBitmap(pictureBitmap);
        }
    }

    protected String encodeImage(Bitmap pictureBitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pictureBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


}
