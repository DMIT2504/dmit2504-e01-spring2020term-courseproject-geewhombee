package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditPeopleActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Button mUpdateButton;
    private Button mDeleteButton;
    private AppDatabase appDB;
    private People person;
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_people);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        androidx.preference.PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);

        TextView mPersonId = findViewById(R.id.activity_edit_people_id_textview);
        final EditText mPersonFirstName = findViewById(R.id.activity_edit_people_firstname_textview);
        final EditText mPersonLastName = findViewById(R.id.activity_edit_people_lastname_textview);
        mUpdateButton = findViewById(R.id.activity_edit_people_update_button);
        mDeleteButton = findViewById(R.id.activity_edit_people_delete_button);
        mLayout = findViewById(R.id.activity_edit_people_layout);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));




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
                if (mPersonFirstName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "First Name cannot be empty!", Toast.LENGTH_SHORT).show();
                } else if (mPersonLastName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Last Name cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    person.firstName = mPersonFirstName.getText().toString();
                    person.lastName = mPersonLastName.getText().toString();
                    appDB.peopleDao().updatePerson(person);
                    Toast.makeText(getApplicationContext(), person.getFullName() + " has been updated!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDB.peopleDao().delete(person);
                finish();
            }
        });

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));
    }
}
