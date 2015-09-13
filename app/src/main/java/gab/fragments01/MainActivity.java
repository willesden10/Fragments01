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
    public final static String TAG = "------";

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

        //if fragment_headers == null phone is in portrait mode.
        if (findViewById(R.id.fragment_headers) == null && savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_container, new Headers(),"headers");
            fragmentTransaction.commit();
            Log.i(TAG, "Headers loaded");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"On Resume");
    }

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

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Activity destroyed");
    }
}
