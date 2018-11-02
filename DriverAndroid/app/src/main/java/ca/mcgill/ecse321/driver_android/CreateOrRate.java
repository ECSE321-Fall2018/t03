package ca.mcgill.ecse321.driver_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateOrRate extends AppCompatActivity {

    //when the page is first opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_rate);
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
            TextView tv = (TextView) findViewById(R.id.createOrRateUserTextView);
            String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
            tv.setText(driverUsername);
        }


        //to go to the create page
        Button goCreateRouteBtn = (Button) findViewById(R.id.goCreateRouteBtn);
        goCreateRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView driverTextView = (TextView) findViewById(R.id.createOrRateUserTextView);
                String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
                Intent startIntent = new Intent(getApplicationContext(), Second_Activity.class);
                startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME", driverUsername);
                startActivity(startIntent);
            }
        });

        //to go to the rate page
        Button ratePassengerActivityBtn = (Button) findViewById(R.id.ratePassengerActivityBtn);
        ratePassengerActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView driverTextView = (TextView) findViewById(R.id.createOrRateUserTextView);
                String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
                Intent startIntent = new Intent(getApplicationContext(), RatePassenger.class);
                startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME", driverUsername);
                startActivity(startIntent);
            }
        });

        //to see the users routes
        Button goToRouteListBtn = (Button) findViewById(R.id.goToRouteListBtn);
        goToRouteListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView driverTextView = (TextView) findViewById(R.id.createOrRateUserTextView);
                String driverUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.driver_android.USERNAME");
                Intent startIntent = new Intent(getApplicationContext(), RouteList.class);
                startIntent.putExtra("ca.mcgill.ecse321.driver_android.USERNAME", driverUsername);
                startActivity(startIntent);
            }
        });
    }

}
