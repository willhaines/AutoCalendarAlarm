package me.willhaines.autocalendaralarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Aaron on 8/31/2014.
 */

public class AlarmReceiver extends BroadcastReceiver
{

    private static final String ALARM_ACTION_NAME = "me.willhaines.autocalendaralarm.ALARM";

    @Override
    public void onReceive(Context context, Intent intent)
    {

        if(ALARM_ACTION_NAME.equals(intent.getAction()))
        {
            String[] calendar = intent.getStringArrayExtra("calendar");
            Toast.makeText(context, calendar[0] + ", " + calendar[1], Toast.LENGTH_SHORT).show();
        }


    }
}
