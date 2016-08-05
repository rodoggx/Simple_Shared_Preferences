package com.example.simplesharedpreferences;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import com.google.gson.reflect.TypeToken;

        import java.io.IOException;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.List;

        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    public static final String STUDENT_BUNDLE_KEY = "STUDENT_BUNDLE_KEY";

    private String mUrl = "http://www.mocky.io/v2/57a4dfb40f0000821dc9a3b8";

    private EditText mEditTextUser;
    private EditText mEditTextPassword;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mEditTextUser = (EditText) findViewById(R.id.inputUser);
        mEditTextPassword = (EditText) findViewById(R.id.inputPW);
    }

    public void doMagic(View view) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mUrl)
                .build();

        final String user = mEditTextUser.getText().toString();
        final String password = mEditTextPassword.getText().toString();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<List<Student>>() {
                }.getType();

                boolean authenticated = false;

                ArrayList<Student> students = gson.fromJson(json, listType);
                for (Student student : students) {
                    Log.d(TAG, "onResponse: " + student.toString() + " " + mContext);
                    if (compareUserCredentials(student, user, password)) {
                        goToDetailsActivity(student);
                        authenticated = true;
                        break;
                    }
                }
                if (!authenticated) {
                    showFailedCredentials();
                }
            }
        });
    }

    private void showFailedCredentials() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToDetailsActivity(Student student) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(STUDENT_BUNDLE_KEY, student);
        startActivity(intent);
    }

    private boolean compareUserCredentials(Student student, String user, String password) {
        if (!student.getName().equals(user)) {
            return false;
        }
        if (!student.getPassword().equals(password)) {
            return false;
        }
        return true;
    }
}