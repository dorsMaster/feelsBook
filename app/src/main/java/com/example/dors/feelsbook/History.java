package com.example.dors.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.dors.feelsbook.R.layout.single_item;

public class History extends AppCompatActivity {

    private final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private final String MESSAGE_DATE = "com.example.myfirstapp.DATE";

    ListView listView;
    ArrayList<String> myEnteries;
    ArrayList<String> navEnteries;
    ArrayAdapter adapter;

    String dateToShow;
    String dateToInspect;

    public String readData(String date) {
//        TODO inmplement a try and catch error
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String value = sharedPref.getString(date," ");
        Log.d("READDATA",value);
        return value;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        myEnteries = new ArrayList<String>();
        navEnteries =new ArrayList<String>();

        Map<String, ?> allEntries = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            String Myentry = String.valueOf(entry).substring(0,13);
//            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String formatted_entry = String.format(Myentry, myFormat);
            String formatted_entry = String.valueOf(entry).substring(0,4)+'/'+ String.valueOf(entry).substring(4,6)+'/'+ String.valueOf(entry).substring(6,8) + ' '+ String.valueOf(entry).substring(8,10)+':'+ String.valueOf(entry).substring(10,12)+':' +String.valueOf(entry).substring(12,14);
            myEnteries.add(formatted_entry);
            navEnteries.add(String.valueOf(entry));
        }

        Collections.reverse(myEnteries);
        Collections.reverse(navEnteries);
        adapter = new ArrayAdapter<String>(this, R.layout.single_item,myEnteries);
        listView = findViewById(R.id.ListOfItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                Log.d("ONITEM", navEnteries.get(position));
                dateToShow = myEnteries.get(position);
                dateToInspect = (navEnteries.get(position)).substring(0,14);
//                Log.d("ONITEM", String.valueOf(((TextView)view).getText()));
                Intent intent = new Intent(getBaseContext(), HistoryEdit.class);
                intent.putExtra(MESSAGE_DATE,dateToShow);
                intent.putExtra(EXTRA_MESSAGE,readData(dateToInspect));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });




    }

}