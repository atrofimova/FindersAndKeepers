package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonFound;
    Button buttonLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        // Go to I found item section
        buttonFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCreateLostItemActivity = new Intent(getApplicationContext(), TakePictureActivity.class);
                startActivity(startCreateLostItemActivity);
            }
        });
    }

    private void initUI() {
        buttonFound = (Button)findViewById(R.id.buttonFound);
        buttonLost = (Button)findViewById(R.id.buttonLost);
    }


}
