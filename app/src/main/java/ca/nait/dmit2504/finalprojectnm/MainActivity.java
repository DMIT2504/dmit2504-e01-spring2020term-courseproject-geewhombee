package ca.nait.dmit2504.finalprojectnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextview = findViewById(R.id.activity_main_textview);
        FloatingActionButton addNewFloatingButton = findViewById(R.id.floatingActionButton);
        addNewFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewIntent = new Intent(getApplicationContext(),NewPeopleActivity.class);
                startActivity(addNewIntent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());

        List<People> peopleList = appDB.peopleDao().getAll();
        String testString = "";
        for (People test : peopleList) {
            testString += test.getFullName() + "\n";

        }
        mTextview.setText(testString);
    }
}