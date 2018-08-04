package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.nkzawa.socketio.client.Socket;

public class GetAccessActivity extends AppCompatActivity {
    Button buttonCollect;
    Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_access);

        buttonCollect = (Button)findViewById(R.id.buttonUnlock);
        buttonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketHandler socketHandler = (SocketHandler) getApplication();
                mSocket = socketHandler.getSocket();
                mSocket.emit("collect");
                Intent startGoBackTomain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startGoBackTomain);
            }
        });
    }
}
