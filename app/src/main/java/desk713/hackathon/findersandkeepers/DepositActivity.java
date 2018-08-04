package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.nkzawa.socketio.client.Socket;

public class DepositActivity extends AppCompatActivity {
    Button buttonLock;
    Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        initUI();
        SocketHandler socketHandler = (SocketHandler) getApplication();
        mSocket = socketHandler.getSocket();

        buttonLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.emit("picture", FoundItem.picture);
                mSocket.emit("id", FoundItem.id);
                mSocket.emit("q1", FoundItem.questions[0]);
                mSocket.emit("q2", FoundItem.questions[1]);
                mSocket.emit("q3", FoundItem.questions[2]);
                mSocket.emit("a1", FoundItem.answers[0]);
                mSocket.emit("a1", FoundItem.answers[1]);
                mSocket.emit("a1", FoundItem.answers[2]);
                mSocket.emit("create", true);

                Intent goBacktoMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBacktoMain);
            }
        });
    }

    private void initUI(){
        buttonLock = (Button)findViewById(R.id.buttonLockBox);
    }




}
