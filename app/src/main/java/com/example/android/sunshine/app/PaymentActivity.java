package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class PaymentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
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

    public void acceptNewValue(View view) {
        Intent intent = new Intent(this, CashFlowActivity.class);

        final TextView mealPriceField = (TextView) findViewById(R.id.mealprice);
        String mealprice = mealPriceField.getText().toString();

        // Check to see if the meal price includes a "$"
        if (mealprice.indexOf("$") == -1) {
            mealprice = "$" + mealprice;
        }
        try {
            // Get currency formatter
            NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();
            float newPayment = nf.parse(mealprice).floatValue();
            intent.putExtra("NEW_CAR_PAYMENT", newPayment);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_payment, container, false);

            final Button button2 = (Button) rootView.findViewById(R.id.calculate);
            final TextView mealPriceField = (TextView) rootView.findViewById(R.id.mealprice);

            mealPriceField.setText("$750.00");

            final TextView answerField = (TextView) rootView.findViewById(R.id.answer);
            button2.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v) {
                    try {
                        // Perform action on click
                        //Log.i(MainActivity.tag, "onClick invoked.");

                        // Grab the meal price from the UI
                        String mealprice = mealPriceField.getText().toString();

                        //Log.i(MainActivity.tag, "mealprice is [" + mealprice + "]");
                        String answer = "";

                        // Check to see if the meal price includes a "$"
                        if (mealprice.indexOf("$") == -1) {
                            mealprice = "$" + mealprice;
                        }

                        float fmp = 0.0F;

                        // Get currency formatter
                        NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();

                        if (nf == null) {
                            //Log.i(MainActivity.tag, "punt - NumberFormat is null");
                        }

                        // Grab the input meal price
                        fmp = nf.parse(mealprice).floatValue();

                        // calculate number of payments and how much you will pay in interest
                        int numberPayments = (int) Math.floor(1.15 * 55000.0 / fmp);
                        double interestCosts = 35000/2.0 * 0.01 * numberPayments;

                        //Log.i(MainActivity.tag, "Total Meal price is [" + fmp + "]");
                        // Format our result
                        answer = "Car price: $55,000, # payments " + numberPayments + ", total Interest " + nf.format(interestCosts);

                        // Display the answer
                        answerField.setText(answer);

                        //Log.i(MainActivity.tag, "onClick complete.");
                    } catch (java.text.ParseException pe) {
                        //Log.i(MainActivity.tag, "Parse exception caught");
                        answerField.setText("Failed to parse amount");
                    } catch (Exception e) {
                        //Log.e(MainActivity.tag, "Failed to Calculate Tip:" + e.getMessage());
                        e.printStackTrace();
                        answerField.setText(e.getMessage());
                    }
                }
            });

            return rootView;
        }

    }
}
