package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckAnswersActivity extends AppCompatActivity {
    TextView textViewQ1;
    TextView textViewQ2;
    TextView textViewQ3;
    EditText editTextA1;
    EditText editTextA2;
    EditText editTextA3;
    ImageView imageViewPicture;
    ImageButton imageButtonSubmit;
    String id;
    Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answers);
        initUI();

        id = getIntent().getExtras().getString("id");
        SocketHandler socketHandler = (SocketHandler) getApplication();

        if (id == "0") {
            mSocket.emit("getNewItem", "");
        }
        if (id == "1") {
            imageViewPicture.setImageResource(R.drawable.anna);
        }
        if (id == "2") {
            imageViewPicture.setImageResource(R.drawable.matt);
        }
        mSocket = socketHandler.getSocket();

        mSocket.emit("getQuestions", id);
        mSocket.on("q1", setQ1);
        mSocket.on("q2", setQ2);
        mSocket.on("q3", setQ3);
        mSocket.on("setImage", setImage);
        mSocket.on("yep", yep);
        mSocket.on("nope", nope);

        imageButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
            }
        });
    }

    private void initUI(){
        textViewQ1 = (TextView)findViewById(R.id.textViewQ1);
        textViewQ2 = (TextView)findViewById(R.id.textViewQ2);
        textViewQ3 = (TextView)findViewById(R.id.textViewQ3);
        editTextA1 = (EditText)findViewById(R.id.editTextA1);
        editTextA2 = (EditText)findViewById(R.id.editTextA2);
        editTextA3 = (EditText)findViewById(R.id.editTextA3);
        imageViewPicture = (ImageView)findViewById(R.id.imageViewPic);
        imageButtonSubmit = (ImageButton)findViewById(R.id.imageButtonSubmit);
    }


    private Emitter.Listener setQ1 = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String q = (String) args[0];
                    textViewQ1.setText(q);
                }
            });
        }
    };

    private Emitter.Listener setQ2 = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String q = (String) args[0];
                    textViewQ2.setText(q);
                }
            });
        }
    };

    private Emitter.Listener setQ3 = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String q = (String) args[0];
                    textViewQ3.setText(q);
                }
            });
        }
    };

    private void addPicture(Bitmap bmp) {
        imageViewPicture.setImageBitmap(bmp);
    }

    private Emitter.Listener setImage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String data = (String) args[0];
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

    private void checkAnswers() {
        JSONObject json = new JSONObject();
        try {
            json.put("a1", editTextA1.getText().toString());
            json.put("a2", editTextA2.getText().toString());
            json.put("a3", editTextA3.getText().toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mSocket.emit("checkAnswers", json);
    }


    private Emitter.Listener yep = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent startGetAccessActivity = new Intent(getApplicationContext(), GetAccessActivity.class);
                    startActivity(startGetAccessActivity);
                }
            });
        }
    };

    private Emitter.Listener nope = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            CheckAnswersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent startFinalActivity = new Intent(getApplicationContext(), FinalActivity.class);
                    startActivity(startFinalActivity);
                }
            });
        }
    };

}
