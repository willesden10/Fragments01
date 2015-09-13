package gab.fragments01;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Articles extends Fragment {
    private TextView articlesTextView;
    private String msg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);

        articlesTextView = (TextView) rootView.findViewById(R.id.articles_textview);

        return rootView;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null)
            articlesTextView.setText(savedInstanceState.getString("MSG"));
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if(args != null){
            articlesTextView.setText(args.getString("MSG"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        msg = articlesTextView.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(!msg.equals("")){
            outState.putString("MSG",msg);
        }
    }
}
