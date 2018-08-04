package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DepositActivity extends AppCompatActivity {
    Button buttonLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        initUI();

        buttonLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Intent goBacktoMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBacktoMain);
            }
        });
    }

    private void initUI(){
        buttonLock = (Button)findViewById(R.id.buttonLockBox);
    }




}
