package com.raider.addressthroughpincode;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.raider.addressthroughpincode.com.raider.addressthroughpincode.dto.PostOffices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ListView ll;
    Button submitButton;
    EditText inputPincode;
    String result = "";
    ProgressDialog progressDialog;
    List<PostOffices> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (ListView) findViewById(R.id.ll);
        submitButton = (Button) findViewById(R.id.submit);
        inputPincode = (EditText) findViewById(R.id.pincdoe);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("fetching data plz wait");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPincode = MainActivity.this.inputPincode.getText().toString();
                String url = "";
                int pincode = checkNumberic(inputPincode);
                if (pincode == -1) {
                    url = "https://api.postalpincode.in/postoffice/" + inputPincode;
                } else {
                    url = "https://api.postalpincode.in/pincode/" + pincode;
                }
                progressDialog.show();
                Task t = new Task();
                AsyncTask<String, String, String> execute = t.execute(url);

            }

            private int checkNumberic(String inputPincode) {
                try {
                    return Integer.parseInt(inputPincode);
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        });
    }


    public class Task extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                dataList = new ArrayList<>();
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    result = result + temp;
                }


                JSONArray responseArray = new JSONArray(result);
                for (int i = 0; i < responseArray.length(); i++) {
                    JSONObject responseObject = responseArray.getJSONObject(i);
                    JSONArray postOffices = responseObject.getJSONArray("PostOffice");
                    for (int j = 0; j < postOffices.length(); j++) {
                        JSONObject postOffice = postOffices.getJSONObject(j);
                        PostOffices ps = new PostOffices();
                        ps.setName(postOffice.getString("Name"));
                        ps.setBranchType(postOffice.getString("BranchType"));
                        ps.setDescription(postOffice.getString("Description"));
                        ps.setDistrict(postOffice.getString("District"));
                        ps.setDivison(postOffice.getString("Division"));
                        ps.setRegion(postOffice.getString("Region"));
                        ps.setState(postOffice.getString("State"));
                        ps.setCountry(postOffice.getString("Country"));
                        dataList.add(ps);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //
            // Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, dataList);
            ll.setAdapter(arrayAdapter);
        }
    }
}