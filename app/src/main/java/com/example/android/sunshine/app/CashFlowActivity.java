package com.example.android.sunshine.app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.app.model.AccountDataRecord;
import com.example.android.sunshine.app.view.AccountView;

import java.util.ArrayList;
import java.util.List;


public class CashFlowActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_flow);

        PlaceholderFragment placeholderFragment = null;
        if (savedInstanceState == null) {
            placeholderFragment = new PlaceholderFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_cash_flow, placeholderFragment)
                    .commit();
        }

        try {
            Intent intent = getIntent();
            //String extraText = intent.getStringExtra(Intent.EXTRA_TEXT);
            float newPayment = intent.getFloatExtra("NEW_CAR_PAYMENT", 0.0f);

            if (newPayment != 0.0f) {
                placeholderFragment.changeCarPayment(newPayment);
            }
        }
        catch (Exception e) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cash_flow, menu);
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

    public void makePayment(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        List<AccountDataRecord> dataRecords;

        public PlaceholderFragment() {
            dataRecords = new ArrayList<AccountDataRecord>();
            dataRecords.add(new AccountDataRecord("Paycheck", 160, "(bi-weekly)"));
            dataRecords.add(new AccountDataRecord("Car Payment", -75, "(monthly)"));
            dataRecords.add(new AccountDataRecord("Rental Income", 115, "(monthly)"));
            dataRecords.add(new AccountDataRecord("Golf Club Dues", -20, "(monthly)"));
            dataRecords.add(new AccountDataRecord("Day Care", -70, "(weekly)"));
            dataRecords.add(new AccountDataRecord("Pet Supplies", -20, "when Fido is hungry"));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cash_flow, container, false);


            AccountView view1 = (AccountView) rootView.findViewById(R.id.view1);
            view1.setDataRecord(dataRecords.get(0));

            AccountView view2 = (AccountView) rootView.findViewById(R.id.view2);
            view2.setDataRecord(dataRecords.get(1));

            AccountView view3 = (AccountView) rootView.findViewById(R.id.view3);
            view3.setDataRecord(dataRecords.get(2));

            AccountView view4 = (AccountView) rootView.findViewById(R.id.view4);
            view4.setDataRecord(dataRecords.get(3));

            AccountView view5 = (AccountView) rootView.findViewById(R.id.view5);
            view5.setDataRecord(dataRecords.get(4));

            AccountView view6 = (AccountView) rootView.findViewById(R.id.view6);
            view6.setDataRecord(dataRecords.get(5));

            TextView netCashFlowView = (TextView) rootView.findViewById(R.id.textView_net_cash_flow);
            int netCashFlow = calcNetCashFlow(dataRecords);
            if (netCashFlow < 1000) {
                netCashFlowView.setText("$" + netCashFlow);
                netCashFlowView.setTextColor(Color.parseColor("#ff0000"));
            } else {
                netCashFlowView.setText("$" + netCashFlow);
            }

            return rootView;
        }

        public void changeCarPayment(float payment) {
            AccountDataRecord dataRecord = dataRecords.get(1);
            dataRecord.setValue(-(int) (payment / 10.0));
        }

        public int calcNetCashFlow(List<AccountDataRecord> dataRecords) {
            int total = 0;

            for (AccountDataRecord record : dataRecords) {
                total += record.getValue();
            }

            return 10*total;
        }
    }
}
