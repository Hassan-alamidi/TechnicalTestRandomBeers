package com.example.hassan.technicaltestrandombeers;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Beer randomBeer;
    ServerRequests connection;
    ImageButton beerLabel;
    TextView beerName, beerDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beerLabel = (ImageButton)findViewById(R.id.beerLabelBtn);
        beerName = (TextView) findViewById(R.id.beerTitleLbl);
        beerDescription = (TextView)findViewById(R.id.beerDescriptionLbl);
    }

    @Override
    protected void onStart(){
        super.onStart();
        connection = ServerRequests.getInstance();

        beerLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRandomBeer();
            }
        });
    }

    void requestRandomBeer(){
        connection.RequestBeer(getApplicationContext(),new ActivityCallback() {

            @Override
            public void onResponse(Beer randomBeer) {

                String name = randomBeer.getBeerName();
                String description = randomBeer.getDescription();
                String labelUrl = randomBeer.getLabelFromKey("large");

                if(checkString(name) && checkString(description) && checkString(labelUrl)){
                    beerLabel.setBackgroundColor(Color.TRANSPARENT);
                    Picasso.with(getApplicationContext()).load(labelUrl).placeholder(R.drawable.animated_loading_icon).error(R.drawable.error_icon).into(beerLabel);
                    beerName.setText(name);
                    description = "Description: \n" + description;
                    beerDescription.setText(description);
                }else{
                    Log.e("MainActivity, Line 60", "Some of the values requested from the server are missing");
                    displaySnackbar("Something has gone wrong please try again", 3000);
                }

            }

            @Override
            public void OnFailure(String e) {
                //user does not need to know the exact error, so just let the user know something has gone wrong
                //and tell them to try again later
                if(e.equals("No Internet Connection")){
                    displaySnackbar(e, 5000);
                }else if(e.equals("Request Failure")){
                    displaySnackbar("Something has gone wrong please try again later", 8000);
                }else{
                    Log.e("MainActivity, Line 75", "Unexpected value returned, please ensure values sent to onFailure has been implemented correctly in onFailure too");
                    displaySnackbar("Something has gone seriously wrong please try again later", 10000);
                }

            }
        });
    }

    void displaySnackbar(String message, int duration){
        final Snackbar popup = Snackbar.make(findViewById(R.id.mainContent), message, duration);
        popup.setAction(
                "Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                }
        );
        popup.show();
    }

    boolean checkString(String val){
        return val != null && val.trim().isEmpty() == false;
    }
}
