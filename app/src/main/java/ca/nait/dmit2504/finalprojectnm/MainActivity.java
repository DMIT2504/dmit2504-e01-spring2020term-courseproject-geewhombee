package ca.nait.dmit2504.finalprojectnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mPeopleRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNewFloatingButton = findViewById(R.id.floatingActionButton);
        addNewFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewIntent = new Intent(getApplicationContext(),NewPeopleActivity.class);
                startActivity(addNewIntent);
            }
        });

        mPeopleRecycler = findViewById(R.id.activity_main_recyclerview);
        //mPeopleRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPeopleRecycler.setLayoutManager(linearLayoutManager);
        loadRecycler();
        mPeopleRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecycler();

    }
    private void loadRecycler() {
        AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
        List<People> peopleList = appDB.peopleDao().getAll();

        PeopleAdapter peopleAdapter = new PeopleAdapter(peopleList);
        mPeopleRecycler.setAdapter(peopleAdapter);


    }
}