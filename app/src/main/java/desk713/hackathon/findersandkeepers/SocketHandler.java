package desk713.hackathon.findersandkeepers;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketHandler extends Application {
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.42.0.1:3000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public Socket getSocket() {
        return mSocket;
    }
}
