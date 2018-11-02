package ca.mcgill.ecse321.mypassengerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

import com.loopj.android.http.AsyncHttpClient;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class FindRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_route);
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
        final Spinner startCitySpinner = (Spinner) findViewById(R.id.startCitySpinner);
        final Spinner endCitySpinner = (Spinner) findViewById(R.id.endCitySpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        startCitySpinner.setAdapter(adapter);
        endCitySpinner.setAdapter(adapter);

        TextView dateView = (TextView) findViewById(R.id.newevent_date);
        String date = dateView.getText().toString();



        TextView txtView = (TextView) findViewById(R.id.textView1);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getName = (String) bd.get("username");
            txtView.setText(getName);
        }

    }


    public void findRoute (View v) {
        //error = "";

//        final TextView passengerUsernameText = (TextView) findViewById(R.id.username_editText);
//        final TextView passengerPasswordText = (TextView) findViewById(R.id.password_editText);
//
//        String passengerUsername = passengerUsernameText.getText().toString();
//        String passengerPassword = passengerPasswordText.getText().toString();

        final Spinner startCitySpinner = (Spinner) findViewById(R.id.startCitySpinner);
        final Spinner endCitySpinner = (Spinner) findViewById(R.id.endCitySpinner);

        String startCity = startCitySpinner.getSelectedItem().toString();
        String endCity = endCitySpinner.getSelectedItem().toString();

        TextView dateView = (TextView) findViewById(R.id.newevent_date);
        String date = dateView.getText().toString();

        Log.d("hello", "hello");

        HttpUtils.get("findRoute/" + date + "/" + startCity + "/" + endCity, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("omg android", response.toString());

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                String vehicle = "";
                String price = "";
                String id = "";
                String list = "";
                for (int i = 0; i < response.length(); i++) {

                    JSONObject jsonobject = null;
                    try {
                        jsonobject = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        vehicle = jsonobject.getString("vehicle");
                        Log.d("fail", vehicle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        price = jsonobject.getString("price");
                        Log.d("fail", price);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        id = String.valueOf(jsonobject.getLong("id"));
                        Log.d("id", String.valueOf(id));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    list += "Vehicle: " + vehicle + " Price: " + price + " ID: " + id + "\n";
                }

                TextView listText = (TextView) findViewById(R.id.listText);
                listText.setText(list);
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

    public void joinRoute (View v){

        final EditText idText = (EditText) findViewById(R.id.idText);
        long id = Long.parseLong(idText.getText().toString());

        TextView txtView = (TextView) findViewById(R.id.textView1);
        String username = txtView.getText().toString();





        HttpUtils.get("joinRoute/" + id + "/" + username, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("omg android", response.toString());

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



    private Bundle getDateFromLabel (String text){
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
        rtn.putInt("month", month - 1);
        rtn.putInt("year", year);

        return rtn;
    }


    public void showDatePickerDialog (View v){
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void setDate ( int id, int d, int m, int y){
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }

}
