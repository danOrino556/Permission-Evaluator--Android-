package com.threeAmigos.whistleblowerpro;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Daniel Robertson
 * 879118
 *
 * Not currently used in the application. Was replaced by a progress bar
 * widget. Might still be used in the future
 */

public class LoadingActivity extends Activity
{

    private static int SPLASH_TIME_OUT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading);
        Bundle appWanted = getIntent().getExtras();
        String appSearched = appWanted.getString("AppSearched");
        TextView appName = (TextView) findViewById(R.id.textLoadingSearchedAppName);
        appName.setText(appSearched);

        final String detailsJsonString = new String();
        ProgressBar loadingProgressBar = (ProgressBar) findViewById(R.id.progressBarLoading);

        //String appJSON = new String();
        //new DownloadApplicationDetails().execute("details url from jacob");


        new Handler().postDelayed(new Runnable()
        {

            //Splash screen timer

            @Override
            public void run()
            {
                //The following will happen after the timer is complete

                Bundle appWanted = getIntent().getExtras();
                String JSONreceived = appWanted.getString("JSONreceived");




                Intent i = new Intent(getApplicationContext(), DefaultSearchDetails.class);
                Bundle extras = new Bundle();
                extras.putString("JSONreceived", JSONreceived);
                i.putExtras(extras);
                startActivity(i);

                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private class DownloadApplicationDetails extends AsyncTask<String, Void, String>
    {
        JSONParser jsonParser = new JSONParser();
        protected String doInBackground(String... urls)
        {
            return jsonParser.readJSONFeed(urls[0]);
        }

        @Override
        protected void onPostExecute(String result)
        {
            try
            {
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.getLocalizedMessage();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loading, menu);
        return true;
    }
    
}
