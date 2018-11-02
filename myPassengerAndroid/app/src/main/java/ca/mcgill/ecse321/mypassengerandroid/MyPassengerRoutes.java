package ca.mcgill.ecse321.mypassengerandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyPassengerRoutes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_passenger_routes);
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
        TextView tv = (TextView) findViewById(R.id.UserNameMyRoutes);
        String passenger = getIntent().getExtras().getString("username");
        tv.setText(passenger);
    }
    public void passengerRoutes(View v) {
        //error = "";

        TextView tv = (TextView) findViewById(R.id.UserNameMyRoutes);
        String username = tv.getText().toString();



        HttpUtils.get("showPassengersRoutes/" + username, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("omg android", response.toString());

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                String startCity = "";
                String endCity = "";
                String date = "";
                String vehicle = "";
                String price = "";
                String availableSeats = "";
                String id = "";
                String info = "";

                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {

                        startCity = jsonobject.getString("startCity");
                        endCity = jsonobject.getString("endCity");
                        date = jsonobject.getString("date");
                        vehicle = jsonobject.getString("vehicle");
                        price = jsonobject.getString("price");
                        availableSeats = jsonobject.getString("availableSeats");
                        id = String.valueOf(jsonobject.getString("id"));

                    } catch (JSONException e) {


                    }

                    info += "Start City: " + startCity + " End City: " + endCity + " Date: " + date + " Vehicle: " + vehicle + " Price: " + price + " Available Seats: " + availableSeats + " ID: " + id + "\n";

                }
                TextView tv = (TextView) findViewById(R.id.textView4);
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
    public void leaveRoute(View v){
        final TextView idText = (TextView)findViewById(R.id.leaveRouteText);
        long id = Long.parseLong(idText.getText().toString());

        TextView tv = (TextView) findViewById(R.id.UserNameMyRoutes);
        String username = tv.getText().toString();

        HttpUtils.get("leaveRoute/" + id + "/" + username, new RequestParams(), new JsonHttpResponseHandler() {
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
