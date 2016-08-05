package com.example.simplesharedpreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.dTxt1);

        Student student = getIntent()
                .getExtras()
                .getParcelable(MainActivity.STUDENT_BUNDLE_KEY);

        textView.setText("Welcome! " + student.toString());

    }
}