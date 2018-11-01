package ca.mcgill.ecse321.passengerandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Rating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
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

        final Spinner passengerRatingSpinner = (Spinner) findViewById(R.id.driverRatingSpinner);
        ArrayAdapter<CharSequence> ratingAdapter = ArrayAdapter.createFromResource(this,
                R.array.rating_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        passengerRatingSpinner.setAdapter(ratingAdapter);
    }

}
