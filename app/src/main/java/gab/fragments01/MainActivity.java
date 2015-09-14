package gab.fragments01;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Headers.onHeaderClickListener{
    private static final String TAG =MainActivity.class.toString();
    private static boolean isHeadersLoadedSinglePanel = false;


    //Interface's method that allow fragments to communicate with MainActivity.
    @Override
    public void onHeaderClick(String msg) {
        Articles articles = (Articles) getFragmentManager().findFragmentById(R.id.fragment_articles);
        TextView articlesTextView = (TextView) findViewById(R.id.articles_textview);
        if(articles == null || articlesTextView == null){
            Bundle bundle = new Bundle();
            bundle.putString("MSG",msg);

            articles = new Articles();
            articles.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_container, articles);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        else{
            articlesTextView.setText(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");
        setContentView(R.layout.activity_main);

        //the fragment Headers is only loaded is the phone is in portrait mode -> fragment_headers == null
        //and if the app is created for the first time -> saveInstanceState == null
        if (findViewById(R.id.fragment_headers) == null && savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //The fragment headers is given a tag "headers" so hat it can be found later using fragmentManager.findFragmentByTag(String);
            fragmentTransaction.add(R.id.activity_container, new Headers(),"headers");
            fragmentTransaction.commit();
            isHeadersLoadedSinglePanel = true;
            Log.i(TAG, "Headers loaded");
        }
    }


    //When the activity is recreated after changing orientation for instance the fragment Headers is loaded
    //if the orientation is portrait -> fragment_headers -> null
    //and if the fragment Headers has not been loaded previously -> headers == null
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "On Restore Instace State");
        FragmentManager fragmentManager = getFragmentManager();
        Headers headers = (Headers) fragmentManager.findFragmentByTag("headers");
        if (findViewById(R.id.fragment_headers) == null && headers == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_container, new Headers(),"headers");
            fragmentTransaction.commit();
            Log.i(TAG, "Headers loaded");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"On Saved Instance State");
    }

}
