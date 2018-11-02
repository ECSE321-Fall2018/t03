package ca.mcgill.ecse321.driver_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String error = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //this button takes you to the home page
        Button continueAppBtn = (Button) findViewById(R.id.continueAppBtn);
       continueAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText driverUsernameText = (EditText) findViewById(R.id.driverUsernameText);
                EditText driverPasswordText = (EditText) findViewById(R.id.driverPasswordText);


                String driverUsername = driverUsernameText.getText().toString();
                String driverPassword = driverPasswordText.getText().toString();

                //HttpUtils.post("create/" + driverUsername + "/" + driverPassword, new RequestParams(), new JsonHttpResponseHandler());

                Intent startIntent = new Intent(getApplicationContext(), CreateOrRate.class);
                startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME",driverUsername);

                startActivity(startIntent);



            }
        });
    }

    //create a user
    public void addUser(View v) {
        error = "";

        //input username and password
        EditText driverUsernameText = (EditText) findViewById(R.id.driverUsernameText);
        EditText driverPasswordText = (EditText) findViewById(R.id.driverPasswordText);

        String driverUsername = driverUsernameText.getText().toString();
        String driverPassword = driverPasswordText.getText().toString();

        HttpUtils.post("signIn/" + driverUsername + "/" + driverPassword, new RequestParams(), new JsonHttpResponseHandler() {
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

        Intent startIntent = new Intent(getApplicationContext(), CreateOrRate.class);
        startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME", driverUsername);
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



}
