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
    private PeopleAdapter.RecyclerViewClickListener listener;
    private List<People> peopleList;



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

        loadRecycler();



    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecycler();

    }
    private void loadRecycler() {



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPeopleRecycler.setLayoutManager(linearLayoutManager);


        AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
        peopleList = appDB.peopleDao().getAll();

        PeopleAdapter peopleAdapter = new PeopleAdapter(peopleList,listener);
        mPeopleRecycler.setAdapter(peopleAdapter);
        setOnClickListener();


    }

    private void setOnClickListener() {
        listener = new PeopleAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                int personId = peopleList.get(position).getId();
                Intent intent = new Intent(getApplicationContext(),EditPeopleActivity.class);
                intent.putExtra("personId", personId);
                startActivity(intent);
            }
        };
    }

}