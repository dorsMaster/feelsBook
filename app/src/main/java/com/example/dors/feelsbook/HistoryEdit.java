package com.example.dors.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryEdit extends AppCompatActivity {

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private final String MESSAGE_DATE = "com.example.myfirstapp.DATE";
    private String date;
    private  String message;

    /**
     * To edit data and save it again
     * @param view
     */

    public void editData(View view) {
        Intent intent = new Intent(getBaseContext(), Today.class);
        intent.putExtra(MESSAGE_DATE,date);
        Log.d("MESSAGE_DATE",date);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
        finish();
    }

    /**
     * to delete data
     * @param view
     */
    public void deleteData(View view) {
        String tmpDate = date.replace("/","");
        tmpDate = tmpDate.replace(" ","");
        tmpDate = tmpDate.replace(":","");
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        sharedPref.edit().remove(tmpDate).apply();
        goToNextActivity(History.class);
        finish();
    }

    /**
     * creates and replaces the emotions with an emoji
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_edit);

        // Get the Intent that started this activity and extract the string
        message = getIntent().getStringExtra(EXTRA_MESSAGE);
        Log.d("MESSAGEMI",message);
        String emoji = message.substring(0,1);
        date = (getIntent().getStringExtra(MESSAGE_DATE)).substring(0,19);
        ImageView imageView = findViewById(R.id.historyImage);
        switch (emoji){
            case "1":
                imageView.setImageResource(R.drawable.ic_003_emoji_2);
                break;
            case "2":
                imageView.setImageResource(R.drawable.ic_002_emoji);
                break;
            case "3":
                imageView.setImageResource(R.drawable.ic_004_emoji_3);
                break;
            case "4":
                imageView.setImageResource(R.drawable.ic_001_surprised);
                break;
            case "5":
                imageView.setImageResource(R.drawable.ic_005_emoji_1);
                break;
            case "6":
                imageView.setImageResource(R.drawable.ic_006_surprised_1);
                break;
            default:
                break;

        }


        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.plain_text_input_history);
        TextView historyDate = findViewById(R.id.historyDate);

        historyDate.setText(date);
        textView.setText(message.substring(1,message.length()));

    }

    /**
     * Goes to the next activity and closes the current one
     * @param nextActivity
     */
    public void goToNextActivity(Class nextActivity) {
        Intent intent = new Intent(this, nextActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

}

