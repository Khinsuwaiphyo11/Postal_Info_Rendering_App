package org.techtown.finalprojectv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SScreen = 201;

    Button btn, btn2;
    EditText textview;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control on the layout
        btn = findViewById(R.id.button);
        tvResult = findViewById(R.id.textViewResult);

        //click listener for each button

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://data.opendatasoft.com/api/explore/v2.1/catalog/datasets/geonames-postal-code@public/records?limit=5&refine=country_code%3A%22KR%22";


// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    // Parse the JSON response
                                    JSONObject jsonResponse = new JSONObject(response);

                                    // Get the "results" array
                                    JSONArray resultsArray = jsonResponse.getJSONArray("results");

                                    // Check if the array is not empty
                                    if (resultsArray.length() > 0) {
                                        StringBuilder resultTextBuilder = new StringBuilder();

                                        // Loop through each object in the array
                                        for (int i = 0; i < resultsArray.length(); i++) {
                                            // Get the current object
                                            JSONObject resultObject = resultsArray.getJSONObject(i);

                                            // Extract information from the object
                                            String country_code = resultObject.getString("country_code");
                                            String postal_code = resultObject.getString("postal_code");
                                            String place_name = resultObject.getString("place_name");
                                            double latitude = resultObject.getDouble("latitude");
                                            double longitude = resultObject.getDouble("longitude");
                                            // Add more fields as needed...

                                            // Append the information to the result text
                                            resultTextBuilder.append("Country Code: ").append(country_code).append("\n")
                                                    .append("Postal Code: ").append(postal_code).append("\n")
                                                    .append("Place Name: ").append(place_name).append("\n")
                                                    .append("Latitude: ").append(latitude).append("\n")
                                                    .append("Longitude: ").append(longitude).append("\n")
                                                    .append("\n"); // Add a line break between each record
                                        }

                                        // Display the extracted information in your TextView or do whatever you need with it
                                        tvResult.setText(resultTextBuilder.toString());
                                    } else {
                                        // Handle the case when the "results" array is empty
                                        tvResult.setText("No results found");
                                    }
                                } catch (JSONException e) {
                                    // Handle JSON parsing error
                                    e.printStackTrace();
                                    tvResult.setText("Error parsing JSON");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvResult.setText("Something Wrong");
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }


        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondScreen.class);
                startActivityForResult(intent, REQUEST_CODE_SScreen);
                intent.putExtra("titleMsg", "Search by Typing ");

            }
        });



    }
}