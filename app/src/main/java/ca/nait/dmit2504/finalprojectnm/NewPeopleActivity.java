package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPeopleActivity extends AppCompatActivity {
    public static final String PEOPLE_firstName = "people_firstName";
    public static final String PEOPLE_lastName = "people_lastName";
    private EditText mFirstNameEdittext, mLastNameEdittext;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_people);
        mFirstNameEdittext = findViewById(R.id.activity_new_people_first_name_edittext);
        mLastNameEdittext = findViewById(R.id.activity_new_people_last_name_edittext);
        mSaveButton = findViewById(R.id.activity_new_people_save_button);



        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert the data
                String firstName = mFirstNameEdittext.getText().toString();
                String lastName = mLastNameEdittext.getText().toString();
                AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
                People person = new People(firstName,lastName);
                long test = appDB.peopleDao().singlePersonInsert(person);
                Toast.makeText(getApplicationContext(),test + "",Toast.LENGTH_SHORT).show();

                finish();

//                Intent resultIntent = new Intent();
//
//                if (mFirstNameEdittext.getText().toString().isEmpty()) {
//                    setResult(RESULT_CANCELED, resultIntent);
//                } else {
//                    String firstName = mFirstNameEdittext.getText().toString();
//                    String lastName = mLastNameEdittext.getText().toString();
//
//                    resultIntent.putExtra(PEOPLE_firstName, firstName);
//                    resultIntent.putExtra(PEOPLE_lastName, lastName);
//                    setResult(RESULT_OK, resultIntent);
//                }
//
//                finish();
            }
        });
    }
}
