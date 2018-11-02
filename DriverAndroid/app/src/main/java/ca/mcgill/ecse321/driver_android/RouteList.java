package ca.mcgill.ecse321.driver_android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

public class RouteList extends AppCompatActivity {

    //when app is opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (getIntent().hasExtra("ca.mcgill.ecse321.driver_android.USERNAME")) {
            TextView tv = (TextView) findViewById(R.id.routeUsername);
            String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
            tv.setText(driverUsername);
        }
    }

    //view the users routes
    public void myRoutes(View v) {
        //error = "";

        TextView tv = (TextView) findViewById(R.id.routeUsername);
        String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");



        HttpUtils.get("showDriversRoutes/" + driverUsername, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("omg android", response.toString());

            }

            //if JSON object is found
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                //initialize parameters
                String startCity = "";
                String endCity = "";
                String date = "";
                String vehicle = "";
                String price = "";
                String availableSeats = "";
                String id = "";
                String info = "";

                //looop through found routes
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {

                        //get parameters form individual objects
                        startCity = jsonobject.getString("startCity");
                        endCity = jsonobject.getString("endCity");
                        date = jsonobject.getString("date");
                        vehicle = jsonobject.getString("vehicle");
                        price = jsonobject.getString("price");
                        availableSeats = jsonobject.getString("availableSeats");
                        id = String.valueOf(jsonobject.getString("id"));

                    } catch (JSONException e) {


                    }

                    //the values to the info list
                    info += "Start City: " + startCity + " End City: " + endCity + " Date: " + date + " Vehicle: " + vehicle + " Price: " + price + " Available Seats: " + availableSeats + " ID: " + id + "\n";

                }

                //display the info on the screen
                TextView tv = (TextView) findViewById(R.id.routeListView);
                tv.setText(info);
//                String helper = "";
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        String responseString = response.getString(i);
//                        for (int j = 0; j < responseString.length(); i++) {
//                            char c = responseString.charAt(j);
//                            if ((int) c != 34 || (int) c != 94 || (int) c != 92) {
//                                helper += c;
//                            }
//                        }
//                        helper += " ";
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                Log.d("fail", "fail aahhhhh");

                /*
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
                */
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    //to delete the route
    public void deleteRoute(View v){
        final TextView idText = (TextView)findViewById(R.id.IdText);
        long id = Long.parseLong(idText.getText().toString());
        HttpUtils.get("endRoute/" + id , new RequestParams(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                refreshErrorMessage();
//               // driverUsernameText.setText("");
//            }
//
////            @Override
////            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
////                super.onSuccess(statusCode, headers, response);
////            }
////
////            @Override
////            public void onSuccess(int statusCode, Header[] headers, String responseString) {
////                super.onSuccess(statusCode, headers, responseString);
////            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
////
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
        });
    }
}