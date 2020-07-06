package ca.nait.dmit2504.finalprojectnm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditPeopleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_people);
        TextView personId = findViewById(R.id.activity_edit_people_id_textview);
        EditText personName = findViewById(R.id.activity_edit_people_fullname_textview);

        Intent getIntent = getIntent();
        int testint = getIntent.getIntExtra("personId", -1);
        AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
        People person = appDB.peopleDao().findById(testint);
        personId.setText(person.getId() + "");
        personName.setText(person.getFullName());





    }
}
