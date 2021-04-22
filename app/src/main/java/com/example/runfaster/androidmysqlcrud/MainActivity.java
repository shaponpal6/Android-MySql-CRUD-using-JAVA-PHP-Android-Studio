package com.example.runfaster.androidmysqlcrud;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText mName, mDesignation, mSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText View
        mName = (EditText) findViewById(R.id.name);
        mDesignation = (EditText) findViewById(R.id.designation);
        mSalary = (EditText) findViewById(R.id.salary);

    }

    // Create
    public void createEmployee(View view){

        String name = mName.getText().toString();
        String designation = mDesignation.getText().toString();
        String salary = mSalary.getText().toString();

        HashMap<String, String> requestedParams = new HashMap<>();
        requestedParams.put("name", name);
        requestedParams.put("designation", designation);
        requestedParams.put("salary", salary);
        Log.d("HashMap", requestedParams.get("salary"));
              Toast.makeText(getApplicationContext(), "Clicked : " + requestedParams.get("name"), Toast.LENGTH_LONG).show();


        PostRequestHandler postRequestHandler = new PostRequestHandler(Constant.CREATE_URL, requestedParams);
        postRequestHandler.execute();
    }



        // ASyncTask Class
        class PostRequestHandler extends AsyncTask<Void, Void, String>{
            // Request URL
            String url;
            // Key, Value pair
            HashMap<String, String> requestedParams;

            PostRequestHandler(String url, HashMap<String, String> params){
                this.url = url;
                this.requestedParams = params;
               // Log.d("Input Box", designation);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {

                // Now Send a post request
                BackgroundWorker backgroundWorker = new BackgroundWorker();
                try {
                    String s = backgroundWorker.postRequestHandler(url, requestedParams);
//                    Log.d("HashMap--------", requestedParams.get("salary"));
//                    Log.d("Results------", s.toString());
//                    Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG).show();
                    return s.toString();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(GONE);
                Toast.makeText(getApplicationContext(), "Result : " + s, Toast.LENGTH_LONG).show();

            }
        }

}
