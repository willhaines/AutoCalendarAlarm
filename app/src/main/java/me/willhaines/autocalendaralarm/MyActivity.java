package me.willhaines.autocalendaralarm;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MyActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public String[] Calendar;
    public HashMap<String, String[]> Calendars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Calendar = new String[] {};
        Calendars = new HashMap<String, String[]>();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayList<String> listItems=new ArrayList<String>();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, listItems);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Run query
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        cur = cr.query(uri, new String[]{"calendar_displayName", "ownerAccount", "account_type"}, null, null, null);
        Log.d("foo", "cursor = " + cur);
        while (cur.moveToNext()) {
            final String displayName = cur.getString(0);
            final String ownerAccount = cur.getString(1);
            final String account_type = cur.getString(2);
            Calendars.put(displayName + " " + ownerAccount, new String[] {displayName, ownerAccount, account_type});
            adapter.add(displayName + " " + ownerAccount);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Calendar = (String[])Calendars.get(parent.getItemAtPosition(pos).toString());
        Log.d("foo", Calendar[0]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
