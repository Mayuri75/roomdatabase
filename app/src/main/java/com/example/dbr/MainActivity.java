package com.example.dbr;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private SampleDatabase sampleDatabase;
    List<University> listRecords=new ArrayList<>();
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleDatabase = Room.databaseBuilder(getApplicationContext(),
                SampleDatabase.class, "sample-db").build();

        new DatabaseAsync().execute();
        LiveData<List<University>> universityLiveData = sampleDatabase.daoAccess().fetchAllData();
       txt=findViewById(R.id.txt);
        universityLiveData.observe(this, new Observer<List<University>>() {
            @Override
            public void onChanged(@Nullable List<University> universities) {
                txt.setText(universities.get(0).getName());
                Log.i("sizeRecords",""+universities.size());
            }
        });
     //   listRecords=sampleDatabase.daoAccess().fetchAllData();
      ///  Log.i("sizeRecords",""+listRecords.size());
    }
    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Perform pre-adding operation here.
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Let's add some dummy data to the database.
            University university = new University();
            university.setName("MyUniversity");

            College college = new College();
            college.setId(1);
            college.setName("MyCollege");

            university.setCollege(college);

            //Now access all the methods defined in DaoAccess with sampleDatabase object
            sampleDatabase.daoAccess().insertOnlySingleRecord(university);
//To update only name of university, change it and pass the object along with the primary key value.
           /* university.setSlNo(1);
            university.setName("ABCUniversity");
            sampleDatabase.daoAccess().updateRecord(university);
*/
            //To delete this record set the primary key and this will delete the record matching that primary key value.
         /*   University university = new University();
            university.setSlNo(1);
            sampleDatabase.daoAccess().deleteRecord(university);*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //To after addition operation here.
        }
    }

}
