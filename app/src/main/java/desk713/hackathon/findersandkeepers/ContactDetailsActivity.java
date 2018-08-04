package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactDetailsActivity extends AppCompatActivity {
    Button buttonFinish;
    EditText editTextPhone;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        initUI();

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundItem.setName(editTextName.getText().toString());
                FoundItem.setPhone(editTextPhone.getText().toString());
                Intent startDepositActivity = new Intent(getApplicationContext(), DepositActivity.class);
                startActivity(startDepositActivity);
            }
        });
    }

    private void initUI(){
        buttonFinish = (Button)findViewById(R.id.buttonFinish);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
    }
}
