package com.example.f1.a08_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.f1.a08_database.model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityMain extends AppCompatActivity {

    private Realm realm;
    private Button add;
    private EditText edt_name;
    private EditText edt_age;

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();

        // Find views
        add = (Button) findViewById(R.id.add_new);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_age = (EditText) findViewById(R.id.edt_age);

        // Setup the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); // this setting should improve performance
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityMain.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Handle add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = edt_name.getText().toString();
                edt_name.setText("");
                final String age = edt_age.getText().toString();
                edt_age.setText("");
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Student student = realm.createObject(Student.class);
                        student.setName(name);
                        student.setAge(Integer.parseInt(age));
                        adapter.notifyDataSetChanged();
                        Log.v("Add new student", "Added");
                    }
                });
            }
        });

        // Get list of all students
        RealmResults<Student> listOfStudents = realm.where(Student.class).findAll();

        // Specify the Adapter fot the RecyclerView
        adapter = new MyAdapter(listOfStudents);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
