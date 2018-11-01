package ca.mcgill.ecse321.passengerandroid;

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

public class Second_Activity extends AppCompatActivity {

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

        if(getIntent().hasExtra("ca.mcgill.ecse321.passengerandroid.USERNAME")){
            TextView tv = (TextView) findViewById(R.id.passengerUsernameTextView2);
            String passengerUsername = getIntent().getExtras().getString("ca.mcgill.ecse321.passengerandroid.USERNAME");
            tv.setText(passengerUsername);
        }

        Button searchBtn = (Button) findViewById(R.id.searchRouteButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), SearchRoute.class);
                startActivity(startIntent);

            }

        });

        Button ratingBtn = (Button) findViewById(R.id.ratingButton);
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), Rating.class);
                startActivity(startIntent);

            }

        });


        Button currentPastBtn = (Button) findViewById(R.id.currentRouteButton);
        currentPastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), CurrentPastRoutes.class);
                startActivity(startIntent);

            }

        });
    };


}
