package gab.fragments01;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Articles extends Fragment {
    private static final String TAG = Articles.class.toString();
    private TextView articlesTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);

        Bundle msgBundle = getArguments();
        articlesTextView = (TextView) rootView.findViewById(R.id.articles_textview);
        //Sets the text with the message in the TextView if MainActivity has sent any message.
        if(msgBundle != null)
            articlesTextView.setText(msgBundle.getString("MSG"));

        if(savedInstanceState != null)
            articlesTextView.setText(savedInstanceState.getString("MSG"));
        return rootView;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        articlesTextView.setText(savedInstanceState.getString("MSG"));
        Log.i(TAG,"onViewStateRestored(Bundle)");
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the text that articlesTextView is showing.
        String msg = articlesTextView.getText().toString();
        outState.putString("MSG",msg);
    }
}
