package com.example.yutaroapp.camemode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ListItemFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String displayName = bundle.getString("DisplayName");
        String categoryRole = bundle.getString("CategoryRole");
        String categorySNS = bundle.getString("CategorySNS");
        boolean[] freeDay = bundle.getBooleanArray("FreeDay");
        String imaginationHope = bundle.getString("ImaginationHope");
        String snsUserName = bundle.getString("SNSUserName");
        int age = bundle.getInt("SpinnerAgeInt");
        int region = bundle.getInt("SpinnerRegionInt");
        int sex = bundle.getInt("SpinnerSexInt");
        String whichCharge = bundle.getString("WhichCharge");

        View mInflatedView = inflater.inflate(R.layout.fragment_list_item, container, false);
        TextView t = (TextView) mInflatedView.findViewById(R.id.display_name);
        t.setText(displayName);

        Log.d("ListItemFragment", "categoryRole: " + categoryRole + ", categorySNS: " + categorySNS +
                ", freeDay: " + freeDay.length + ", imaginationHope: " + imaginationHope + ", snsUserName: " + snsUserName +
                ", age: " + age + ", region: " + region + ", sex: " + sex + ", whichCharge: " + whichCharge);
        return mInflatedView;
    }

}
