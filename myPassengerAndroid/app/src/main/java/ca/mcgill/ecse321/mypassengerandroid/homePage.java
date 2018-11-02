package ca.mcgill.ecse321.mypassengerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



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

        TextView txtView = (TextView) findViewById(R.id.textView1);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getName = (String) bd.get("username");
            txtView.setText(getName);
        }




        Button searchBtn = (Button) findViewById(R.id.findRouteButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView usernameText = (TextView)findViewById(R.id.textView1);
                 String username = usernameText.getText().toString();

                Intent startIntent = new Intent(getApplicationContext(), FindRoute.class);
                startIntent.putExtra( "username" ,username);
                startActivity(startIntent);
            }

        });
        Button myRoutesButton = (Button) findViewById(R.id.button3);
        myRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView usernameText = (TextView)findViewById(R.id.textView1);
                String username = usernameText.getText().toString();

                Intent startIntent = new Intent(getApplicationContext(), MyPassengerRoutes.class);
                startIntent.putExtra( "username" ,username);
                startActivity(startIntent);
            }

        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView usernameText = (TextView)findViewById(R.id.textView1);
                String username = usernameText.getText().toString();

                Intent startIntent = new Intent(getApplicationContext(), RateDriver.class);
                startIntent.putExtra( "username" ,username);
                startActivity(startIntent);
            }

        });
    }

}
