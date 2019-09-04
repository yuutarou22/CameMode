package com.example.yutaroapp.camemode;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        ImageView categoryImageView = (ImageView) inflatedView.findViewById(R.id.category_image);

        if (categoryRole.equals("カメラマン")) {
            categoryImageView.setImageResource(R.drawable.cameraman);
            categoryImageView.setBackgroundColor(Color.argb(120, 0, 153, 204));
        } else if (categoryRole.equals("モデル")) {
            categoryImageView.setImageResource(R.drawable.model);
            categoryImageView.setBackgroundColor(Color.argb(120, 255, 0, 0));
        } else {
            categoryImageView.setImageResource(R.drawable.camera_and_model);
            categoryImageView.setBackgroundColor(Color.argb(120, 255, 241, 0));
        }

        TextView displayNameTextView = (TextView) inflatedView.findViewById(R.id.display_name);
        displayNameTextView.setText(displayName);

        TextView categoryRoleTextView = (TextView) inflatedView.findViewById(R.id.category_role);
        categoryRoleTextView.setText(categoryRole);

        TextView categorySNSTextView = (TextView) inflatedView.findViewById(R.id.category_sns);
        categorySNSTextView.setText(categorySNS);

        // freeDay

        TextView imaginationHopeTextView = (TextView) inflatedView.findViewById(R.id.imagination_hope);
        imaginationHopeTextView.setText(imaginationHope);

        TextView snsUserNameTextView = (TextView) inflatedView.findViewById(R.id.sns_user_name);
        snsUserNameTextView.setText(snsUserName);

        TextView ageTextView = (TextView) inflatedView.findViewById(R.id.age);
        String[] ageArray = {"10代", "20代", "30代", "40代", "50代", "60代以上"};
        ageTextView.setText(ageArray[age]);

        TextView regionTextView = (TextView) inflatedView.findViewById(R.id.region);
        String[] regionArray = {"北海道", "東北", "関東", "北陸", "中部", "近畿", "四国", "中国", "九州", "沖縄"};
        regionTextView.setText(regionArray[region]);

        TextView sexTextView = (TextView) inflatedView.findViewById(R.id.sex);
        String[] sexArray = {"未選択", "男性", "女性"};
        sexTextView.setText(sexArray[sex]);

        TextView whichChargeTextView = (TextView) inflatedView.findViewById(R.id.which_charge);
        whichChargeTextView.setText(whichCharge);

        Button snsTransitionButton = (Button) inflatedView.findViewById(R.id.sns_transition);
        snsTransitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ListItemFragment", "onClick snsUserName: " + snsUserName);
                Uri uri = Uri.parse("https://twitter.com/" + snsUserName + "/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return inflatedView;
    }
}
