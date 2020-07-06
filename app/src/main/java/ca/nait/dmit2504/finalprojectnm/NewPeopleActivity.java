package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPeopleActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private EditText mFirstNameEdittext, mLastNameEdittext;
    private Button mSaveButton;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_people);
        mFirstNameEdittext = findViewById(R.id.activity_new_people_first_name_edittext);
        mLastNameEdittext = findViewById(R.id.activity_new_people_last_name_edittext);
        mSaveButton = findViewById(R.id.activity_new_people_save_button);
        mLayout = findViewById(R.id.activity_new_people_layout);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        androidx.preference.PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));



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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));
    }
}
