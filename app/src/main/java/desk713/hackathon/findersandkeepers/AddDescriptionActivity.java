package desk713.hackathon.findersandkeepers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddDescriptionActivity extends AppCompatActivity {

    Button buttonGoToQuestions;
    EditText editTextDescription;
    Spinner spinnerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);
        init();

        buttonGoToQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoundItem.setDescription(editTextDescription.getText().toString());
                FoundItem.setType(spinnerType.getSelectedItem().toString());
                Intent startSecurityQuestionActivity = new Intent(getApplicationContext(), SecurityQuestionsActivity.class);
                startActivity(startSecurityQuestionActivity);
            }
        });
    }

    private void init(){
        buttonGoToQuestions = (Button)findViewById(R.id.buttonGoToQuestions);
        editTextDescription = (EditText)findViewById(R.id.editTextDescription);
        spinnerType = (Spinner)findViewById(R.id.spinnerType);


        List<String> list = new ArrayList<String>();
        list.add("Wallet");
        list.add("Phone");
        list.add("Keys");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);
    }
}
