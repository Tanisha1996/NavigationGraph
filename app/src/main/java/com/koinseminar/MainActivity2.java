package com.koinseminar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Indu Bala on 13/09/21.
 */
public class MainActivity2 extends Activity {


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpAutoCompleteTextView();

    }

    private void setUpAutoCompleteTextView() {
        String[] completions = getResources().getStringArray(R.array.bodies_of_water);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                completions);

        AutoCompleteTextView autoComplete =
                (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view);
        autoComplete.setAdapter(adapter);
    }
}
