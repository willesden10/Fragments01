package gab.fragments01;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Headers extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_headers, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);

        Articles articles = (Articles) getFragmentManager().findFragmentById(R.id.fragment_articles);
        if(articles == null){
            TextView headersTextView = (TextView) getActivity().findViewById(R.id.headers_textview);
            headersTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //Communicate with MainActivity to change fragments
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(menu.findItem(R.id.action_headers) == null) {
            inflater.inflate(R.menu.menu_headers, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_headers){
            Toast.makeText(getActivity(),"Headers settings selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
