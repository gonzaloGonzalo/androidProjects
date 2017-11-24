package com.example.augustopinto.firstconnection;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.augustopinto.firstconnection.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = LandingPageActivity.class.getSimpleName();

    private TextView mDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mDataTextView = findViewById(R.id.data_response);
        loadWeatherData();
    }

    private void loadWeatherData() {
        FetchWeatherDataAsyncTask asyncTask = new FetchWeatherDataAsyncTask();
        asyncTask.execute();
    }

    public class FetchWeatherDataAsyncTask extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {

            URL url = NetworkUtils.buildUrl();

            String response = NetworkUtils.getResponseFromHttpUrl(url);
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.d(TAG, jsonObject.toString());
            JSONObject city;
            try {
                city = jsonObject.getJSONObject("city");
                String data = "Ciudad: " + city.getString("name");
                JSONObject coordenates = city.getJSONObject("coord");
                data += "\n Latitude: " + coordenates.getString("lat");
                data += "\n Longitude: " + coordenates.getString("lon");

                JSONArray list =jsonObject.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject day = (JSONObject) list.get(i);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                    String date = df.format(new Date(day.getLong("dt") * 1000));
                    data += "\n" + date;
                }


                mDataTextView.setText(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
