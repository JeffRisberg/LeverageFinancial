package com.example.android.sunshine.app;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.sunshine.app.model.AccountDataRecord;

import java.util.ArrayList;
import java.util.List;


public class SavingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_savings, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        SavingsDisplayAdapter mOpSavingsAdapter;
        SavingsDisplayAdapter mLtSavingsAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            List<AccountDataRecord> opSavingsList = new ArrayList<AccountDataRecord>();
            opSavingsList.add(new AccountDataRecord("Entertainment", 200, "$150"));
            opSavingsList.add(new AccountDataRecord("Dining Out", 350, "$350 / funded"));
            opSavingsList.add(new AccountDataRecord("Fashion", 1200, "$800 / funded"));
            opSavingsList.add(new AccountDataRecord("Gifts and Toys", 800, "$400 / funded"));

            List<AccountDataRecord> ltSavingsList = new ArrayList<AccountDataRecord>();
            ltSavingsList.add(new AccountDataRecord("Los Angeles Trip", 3000, "$2600 / funded"));
            ltSavingsList.add(new AccountDataRecord("New Car", 15000, "$300 down payment"));
            ltSavingsList.add(new AccountDataRecord("Kitchen Remodel", 10000, "$6000 prepay"));
            ltSavingsList.add(new AccountDataRecord("Emergency Expenses", 2000, "$2000 / funded"));
            ltSavingsList.add(new AccountDataRecord("Japan Trip", 6000, "$4000 / funded"));
            ltSavingsList.add(new AccountDataRecord("Buy House in Colorado", 760000, "$500,000 / pending"));

            mOpSavingsAdapter =
                    new SavingsDisplayAdapter(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_savings,
                            opSavingsList);

            mLtSavingsAdapter =
                    new SavingsDisplayAdapter(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_savings,
                            ltSavingsList);

            View rootView = inflater.inflate(R.layout.fragment_savings, container, false);

            ListView opListView = (ListView) rootView.findViewById(R.id.list_view_savings_op);
            opListView.setAdapter(mOpSavingsAdapter);

            ListView ltListView = (ListView) rootView.findViewById(R.id.list_view_savings_lt);
            ltListView.setAdapter(mLtSavingsAdapter);

            return rootView;
        }

        public class SavingsDisplayAdapter extends ArrayAdapter<AccountDataRecord> {
            private final Activity activity;
            private final int layoutId;

            public SavingsDisplayAdapter(Activity activity, int layoutId, List<AccountDataRecord> dataList) {
                super(activity, layoutId, dataList);

                this.activity = activity;
                this.layoutId = layoutId;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View rowView = convertView;
                SavingsViewHolder viewHolder;

                if (rowView == null) {
                    // Create a new instance of the row layout view
                    LayoutInflater inflater = activity.getLayoutInflater();
                    rowView = inflater.inflate(this.layoutId, null);

                    // Hold the view objects in an object, that way the don't need to be "re-  finded"
                    viewHolder = new SavingsViewHolder();
                    viewHolder.savings_record_name = (TextView) rowView.findViewById(R.id.list_item_savings_text_view);
                    viewHolder.savings_record_detail = (TextView) rowView.findViewById(R.id.list_item_savings_detail_text_view);

                    rowView.setTag(viewHolder);
                } else {
                    viewHolder = (SavingsViewHolder) rowView.getTag();
                }

                /* Set data to your view */
                AccountDataRecord record = this.getItem(position);

                viewHolder.savings_record_name.setText(record.getName());
                //view.savings_record_value.setText(record.getValue());
                viewHolder.savings_record_detail.setText(record.getDetail());

                return rowView;
            }

            protected class SavingsViewHolder {
                protected TextView savings_record_name;
                protected TextView savings_record_value;
                protected TextView savings_record_detail;
            }
        }
    }
}
