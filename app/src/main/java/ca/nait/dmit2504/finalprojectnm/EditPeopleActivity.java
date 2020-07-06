package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditPeopleActivity extends AppCompatActivity {
    private Button mUpdateButton;
    private AppDatabase appDB;
    private People person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_people);
        TextView mPersonId = findViewById(R.id.activity_edit_people_id_textview);
        final EditText mPersonFirstName = findViewById(R.id.activity_edit_people_firstname_textview);
        final EditText mPersonLastName = findViewById(R.id.activity_edit_people_lastname_textview);
        mUpdateButton = findViewById(R.id.activity_edit_people_update_button);


        Intent getIntent = getIntent();
        int testint = getIntent.getIntExtra("personId", -1);
        appDB = AppDatabase.getDatabase(getApplicationContext());
        person = appDB.peopleDao().findById(testint);
        mPersonId.setText(person.getId() + "");
        mPersonFirstName.setText(person.getFirstName());
        mPersonLastName.setText(person.getLastName());


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.firstName = mPersonFirstName.getText().toString();
                person.lastName = mPersonLastName.getText().toString();
                appDB.peopleDao().updatePerson(person);
                finish();
            }
        });

    }
}
