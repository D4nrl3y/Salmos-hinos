package com.goplaychess.gobible;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jonc on 5/22/2016.
 */
public class ReadBook extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);

        Activity activity = (Activity)ReadBook.this;
        TextView textView = (TextView)activity.findViewById(R.id.book_text_read);
        Bundle b = getIntent().getExtras();
        String bookTitle = ""; // or other values
        if(b != null){
            bookTitle = b.getString("key");
        }
        //allows text view to be scrollable
        textView.setMovementMethod(new ScrollingMovementMethod());

        //reading from file and displaying in the textview
        StringBuilder stringBuilder = new StringBuilder();
        String bookText = bookTitle + "/" + bookTitle +"1.txt";
        InputStream inputStream = null;
        try {
            inputStream = getApplicationContext().getAssets().open(bookText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            bufferedReader.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }



        textView.setText(stringBuilder.toString());
    }

}
