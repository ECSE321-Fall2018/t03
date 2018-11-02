package ca.mcgill.ecse321.driver_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import ca.mcgill.ecse321.driver_android.MainActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Second_Activity extends AppCompatActivity {

    String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
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

        if(getIntent().hasExtra("ca.mcgill.ecse321.driver_android.USERNAME")){
            TextView tv = (TextView) findViewById(R.id.driverUsernameTextView);
            String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
            tv.setText(driverUsername);
        }
        final Spinner startCitySpinner = (Spinner) findViewById(R.id.startCitySpinner);
        final Spinner endCitySpinner = (Spinner) findViewById(R.id.endCitySpinner);
        final Spinner seatsSpinner = (Spinner) findViewById(R.id.seatsSpinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        startCitySpinner.setAdapter(adapter);
        endCitySpinner.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> seatsAdapter = ArrayAdapter.createFromResource(this,
                R.array.seats_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        seatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        seatsSpinner.setAdapter(seatsAdapter);

        final ArrayList<String> routeArrayList = new ArrayList<String>();
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, routeArrayList);
//
//        String startCity = startCitySpinner.getSelectedItem().toString();
//        String endCity = endCitySpinner.getSelectedItem().toString();
//
//        EditText priceText = (EditText) findViewById(R.id.priceText);
//        EditText vehicleText = (EditText) findViewById(R.id.vehicleText);
//        EditText seatsAvailableText = (EditText) findViewById(R.id.seatsAvailableText);
//
//        TextView dateText = (TextView) findViewById(R.id.routeDateText);
//
//        String price = priceText.getText().toString();
//        String vehicle = vehicleText.getText().toString();
//        String seatsAvailable = seatsAvailableText.getText().toString();
//
//        String date = dateText.getText().toString();
//
//        String route = startCity + " " + endCity + " " + price + " " + vehicle + " " + seatsAvailable + " " + date;
//
//        routeArrayList.add(route);

//        Button createRouteBtn = (Button) findViewById(R.id.createRouteBtn);
//        createRouteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String startCity = startCitySpinner.getSelectedItem().toString();
//                String endCity = endCitySpinner.getSelectedItem().toString();
//
//                EditText priceText = (EditText) findViewById(R.id.priceText);
//                EditText vehicleText = (EditText) findViewById(R.id.vehicleText);
//                EditText seatsAvailableText = (EditText) findViewById(R.id.seatsAvailableText);
//
//                TextView dateText = (TextView) findViewById(R.id.routeDateText);
//
//                String price = priceText.getText().toString();
//                String vehicle = vehicleText.getText().toString();
//                String seatsAvailable = seatsAvailableText.getText().toString();
//
//                String date = dateText.getText().toString();
//
//                String route = startCity + " " + endCity + " " + price + " " + vehicle + " " + seatsAvailable + " " + date;
//
//                routeArrayList.add(route);
//
////                String routeString = "";
//
//                Intent startIntent = new Intent(getApplicationContext(), ThirdActivity.class);
//                startIntent.putStringArrayListExtra("ca.mcgill.ecse321.driver_android.ROUTELIST", routeArrayList);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.STARTCITY",startCity);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.ENDCITY",endCity);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.PRICE",price);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.VEHICLE",vehicle);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.SEATSAVAILABLE",seatsAvailable);
////                startIntent.putExtra("ca.mcgill.ecse321.driver_android.DATE",date);
//
//                startActivity(startIntent);
//
////               for(int i =0; i<routeArrayList.size();i++){
////                   routeString=routeString + routeArrayList.get(i);
////               }
////               TextView testingRouteView = (TextView) findViewById(R.id.testingRouteView);
////               testingRouteView.setText(routeString);
//
//            }
//        });

    }

    public void createRoute(View v) {
        error = "";
        final Spinner startCitySpinner = (Spinner) findViewById(R.id.startCitySpinner);
        final Spinner endCitySpinner = (Spinner) findViewById(R.id.endCitySpinner);
        final Spinner seatsSpinner = (Spinner) findViewById(R.id.seatsSpinner);

        String startCity = startCitySpinner.getSelectedItem().toString();
        String endCity = endCitySpinner.getSelectedItem().toString();
        String seatsAvailable = seatsSpinner.getSelectedItem().toString();

        EditText priceText = (EditText) findViewById(R.id.priceText);
        EditText vehicleText = (EditText) findViewById(R.id.vehicleText);
   //     EditText seatsAvailableText = (EditText) findViewById(R.id.seatsAvailableText);

        TextView dateText = (TextView) findViewById(R.id.routeDateText);

        String price = priceText.getText().toString();
        String vehicle = vehicleText.getText().toString();
        int seatsInt = Integer.parseInt(seatsAvailable);

        String date = dateText.getText().toString();

        String route = startCity + " " + endCity + " " + price + " " + vehicle + " " + seatsAvailable + " " + date;

        ArrayList<String> routeArrayList = new ArrayList<String>();
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, routeArrayList);

            TextView tv = (TextView) findViewById(R.id.driverUsernameTextView);
            String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
            tv.setText(driverUsername);


        routeArrayList.add(route);

        for(int i = 0; i<routeArrayList.size();i++){
            String currentRoute = routeArrayList.get(i);
        }

     //   String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");



        //create a route
        HttpUtils.post("createRoute/" + seatsInt + "/" + startCity + "/"+ endCity + "/" + date + "/" + vehicle + "/" +
                        driverUsername + "/" + price, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                // driverUsernameText.setText("");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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
        Intent startIntent = new Intent(getApplicationContext(), ThirdActivity.class);
       // startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME",driverUsername);
        startIntent.putStringArrayListExtra("ca.mcgill.ecse321.driver_android.ROUTELIST", routeArrayList);

        startActivity(startIntent);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }



    private Bundle getDateFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }

}
