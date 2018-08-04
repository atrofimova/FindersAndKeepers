package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecurityQuestionsActivity extends AppCompatActivity {
    EditText editTextQuestion1;
    EditText editTextQuestion2;
    EditText editTextQuestion3;
    EditText editTextAnswer1;
    EditText editTextAnswer2;
    EditText editTextAnswer3;
    Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);
        initUI();

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundItem.setQuestion(0, editTextQuestion1.getText().toString());
                FoundItem.setQuestion(1, editTextQuestion2.getText().toString());
                FoundItem.setQuestion(2, editTextQuestion3.getText().toString());

                FoundItem.setAnswer(0, editTextAnswer1.getText().toString());
                FoundItem.setAnswer(1, editTextAnswer2.getText().toString());
                FoundItem.setAnswer(2, editTextAnswer3.getText().toString());
                Intent startContactDetailsActivity = new Intent(getApplicationContext(), ContactDetailsActivity.class);
                startActivity(startContactDetailsActivity);
            }
        });
    }

    private void initUI(){
        editTextQuestion1 = (EditText)findViewById(R.id.editTextQuestion1);
        editTextQuestion2 = (EditText)findViewById(R.id.editTextQuestion2);
        editTextQuestion3 = (EditText)findViewById(R.id.editTextQuestion3);
        editTextAnswer1 = (EditText)findViewById(R.id.editTextAnswer1);
        editTextAnswer2 = (EditText)findViewById(R.id.editTextAnswer2);
        editTextAnswer3 = (EditText)findViewById(R.id.editTextAnswer3);
        buttonDone = (Button)findViewById(R.id.buttonDone);
    }
}
