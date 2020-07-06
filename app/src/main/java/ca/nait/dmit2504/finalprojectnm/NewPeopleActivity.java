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
                if (mFirstNameEdittext.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "First Name cannot be empty!", Toast.LENGTH_SHORT).show();
                } else if (mLastNameEdittext.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Last Name cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    String firstName = mFirstNameEdittext.getText().toString();
                    String lastName = mLastNameEdittext.getText().toString();
                    AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
                    People person = new People(firstName,lastName);
                    long test = appDB.peopleDao().singlePersonInsert(person);
                    Toast.makeText(getApplicationContext(), "New Person added. Id: " + test + ". Name: " + person.getFullName() + ".",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }
}
