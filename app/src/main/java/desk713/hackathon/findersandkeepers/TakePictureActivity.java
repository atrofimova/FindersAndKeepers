package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakePictureActivity extends AppCompatActivity {
    Button buttonTakePicture;
    Button buttonAcceptPicture;
    Button buttonSkipPicture;
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
                    FoundItem.setPicture(pictureBitmap);
                    Intent startAddDescriptionActivity = new Intent(getApplicationContext(), AddDescriptionActivity.class);
                    startActivity(startAddDescriptionActivity);
                } else {
                    //TODO popup: please take a picture of the item
                }
            }
        });
    }

    private void initUI(){
        buttonTakePicture = (Button)findViewById(R.id.buttonTakePicture);
        imageViewPictureFromCamera = (ImageView) findViewById(R.id.imageViewPictureFromCamera);
        buttonAcceptPicture = (Button)findViewById(R.id.buttonAcceptPicture);
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


}
