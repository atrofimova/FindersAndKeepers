package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

public class ListItems extends AppCompatActivity {
    ImageView imageViewNewPicture;
    ImageView imageView1;
    ImageView imageView2;
    Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        SocketHandler socketHandler = (SocketHandler) getApplication();
        mSocket = socketHandler.getSocket();

        imageViewNewPicture = (ImageView)findViewById(R.id.imageViewNewPicture);
        imageViewNewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCheckAnswersActivity = new Intent(getApplicationContext(), CheckAnswersActivity.class);
                startCheckAnswersActivity.putExtra("id", "0");
                startActivity(startCheckAnswersActivity);
            }
        });

        imageView1 = (ImageView)findViewById(R.id.imageViewL1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCheckAnswersActivity = new Intent(getApplicationContext(), CheckAnswersActivity.class);
                startCheckAnswersActivity.putExtra("id", "1");
                startActivity(startCheckAnswersActivity);
            }
        });


        imageView2 = (ImageView)findViewById(R.id.imageViewL2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCheckAnswersActivity = new Intent(getApplicationContext(), CheckAnswersActivity.class);
                startCheckAnswersActivity.putExtra("id", "2");
                startActivity(startCheckAnswersActivity);
            }
        });



        mSocket.emit("getNewItem", "");
        mSocket.on("setImage", setImage);
    }

    private void addPicture(Bitmap bmp) {
        imageViewNewPicture.setImageBitmap(bmp);
    }

    private Emitter.Listener setImage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            ListItems.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String data = (String) args[0];
                    if (data == null){
                        return;
                    }
                    Bitmap bmp = decodeImage(data);
                    addPicture(bmp);
                }
            });
        }
    };

    protected Bitmap decodeImage(String string){
        byte[] b = Base64.decode(string, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(b,0,b.length);
        return bmp;
    }
}
