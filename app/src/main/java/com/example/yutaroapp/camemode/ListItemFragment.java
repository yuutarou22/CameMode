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
    String displayName;
    String categoryRole;
    String categorySNS;
    boolean[] freeDay;
    String imaginationHope;
    String snsUserName;
    int age;
    int region;
    int sex;
    String whichCharge;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        displayName = bundle.getString("DisplayName");
        categoryRole = bundle.getString("CategoryRole");
        categorySNS = bundle.getString("CategorySNS");
        boolean[] freeDay = bundle.getBooleanArray("FreeDay");
        imaginationHope = bundle.getString("ImaginationHope");
        snsUserName = bundle.getString("SNSUserName");
        age = bundle.getInt("SpinnerAgeInt");
        region = bundle.getInt("SpinnerRegionInt");
        sex = bundle.getInt("SpinnerSexInt");
        whichCharge = bundle.getString("WhichCharge");

        View mInflatedView = inflater.inflate(R.layout.fragment_list_item, container, false);

        return setInflateFragmentLayout(mInflatedView);
    }

    private View setInflateFragmentLayout(View inflatedView) {
        TextView displayNameTextView = (TextView) inflatedView.findViewById(R.id.display_name);
        displayNameTextView.setText(displayName);

        TextView categoryRoleTextView = (TextView) inflatedView.findViewById(R.id.category_role);
        categoryRoleTextView.setText(categoryRole);

        TextView categorySNSTextView = (TextView) inflatedView.findViewById(R.id.category_sns);
        categorySNSTextView.setText(categorySNS);

        return inflatedView;
    }
}
