package com.example.yutaroapp.camemode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ListItemFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mInflatedView = inflater.inflate(R.layout.fragment_list_item, container, false);
        TextView t = (TextView)mInflatedView.findViewById(R.id.display_name);
        t.setText("testtest");
        return mInflatedView;
    }

}
