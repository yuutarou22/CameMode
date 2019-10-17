package com.example.yutaroapp.camemode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yutaroapp.camemode.Activity.EditActivity;
import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.List;


public class ListItemFragment extends Fragment implements UserInfoEditCheckTask.UserInfoEditCheckListener {
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

    // 表示用文字列配列
    // ToDo: Stringsに定義しておくことはできないか
    String[] ageArray = {"10代", "20代", "30代", "40代", "50代", "60代以上"};
    String[] sexArray = {"未選択", "男性", "女性"};
    String[] regionArray = {"北海道", "東北", "関東", "北陸", "中部", "近畿", "四国", "中国", "九州", "沖縄"};

    EditText editText = null;

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
        sex = bundle.getInt("SpinnerSex");
        whichCharge = bundle.getString("WhichCharge");

        View mInflatedView = inflater.inflate(R.layout.fragment_list_item, container, false);

        return setInflateFragmentLayout(mInflatedView);
    }

    private View setInflateFragmentLayout(final View inflatedView) {
        ImageView backButton = (ImageView) inflatedView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        editText = new EditText(getContext());

        final ImageView editButton = (ImageView) inflatedView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("編集・削除パスワードを入力してください")
                        .setView(editText)
                        .setNeutralButton("編集する", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CheckPassword(categoryRole, snsUserName, sex, editText.getText().toString());
                            }
                        }).setPositiveButton("戻る", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ViewGroup viewGroup = (ViewGroup) editText.getParent();
                                viewGroup.removeView(editText);
                            }
                        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Log.i("MainActivity", "setOnDismissListenerが呼ばれました。");
                                ViewGroup viewGroup = (ViewGroup) editText.getParent();
                                if (viewGroup != null) {
                                    viewGroup.removeView(editText);
                                }
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        ImageView categoryImageView = (ImageView) inflatedView.findViewById(R.id.category_image);
        LinearLayout fragmentLayout = (LinearLayout) inflatedView.findViewById(R.id.fragment_list_item);
        if (categoryRole.equals("カメラマン")) {
            categoryImageView.setImageResource(R.drawable.cameraman);
            fragmentLayout.setBackground(getResources().getDrawable(R.drawable.fragment_background_cameraman));
        } else if (categoryRole.equals("モデル")) {
            categoryImageView.setImageResource(R.drawable.model);
            fragmentLayout.setBackground(getResources().getDrawable(R.drawable.fragment_background_model));
        } else {
            categoryImageView.setImageResource(R.drawable.camera_and_model);
            fragmentLayout.setBackground(getResources().getDrawable(R.drawable.fragment_background_camera_and_model));
        }

        TextView displayNameTextView = (TextView) inflatedView.findViewById(R.id.display_name);
        displayNameTextView.setText(displayName);

        TextView categorySNSTextView = (TextView) inflatedView.findViewById(R.id.category_sns);
        categorySNSTextView.setText(categorySNS);

        TextView snsUserNameTextView = (TextView) inflatedView.findViewById(R.id.sns_user_name);
        snsUserNameTextView.setText(snsUserName);

        // freeDay

        TextView ageTextView = (TextView) inflatedView.findViewById(R.id.age);

        TextView regionTextView = (TextView) inflatedView.findViewById(R.id.region);
        regionTextView.setText(regionArray[region]);

        ageTextView.setText(ageArray[age]);

        TextView sexTextView = (TextView) inflatedView.findViewById(R.id.sex);
        sexTextView.setText(sexArray[sex]);

        TextView whichChargeTextView = (TextView) inflatedView.findViewById(R.id.which_charge);
        whichChargeTextView.setText(whichCharge);

        TextView imaginationHopeTextView = (TextView) inflatedView.findViewById(R.id.imagination_hope);
        imaginationHopeTextView.setText(imaginationHope);

        Button snsTransitionButton = (Button) inflatedView.findViewById(R.id.sns_transition);
        snsTransitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ListItemFragment", "onClick snsUserName: " + snsUserName);
                Utility.snsTranslationActivity(snsUserName, categorySNS, getContext());
            }
        });

        return inflatedView;
    }

    /**
     * 編集・削除パスワードがサーバにある情報と一致するか確認
     *
     * @return パスワードが一致:true　パスワードが不一致:false
     */
    private void CheckPassword(String categoryRole, String snsUserName, int sex, final String inputText) {
        if (inputText.equals("") || inputText.length() == 0) {
            return;
        }

        // 種別、SNSユーザ名、性別で検索をかける
        NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");
        query.whereEqualTo("CategoryRole", categoryRole);
        query.whereEqualTo("SNSUserName", snsUserName);
        query.whereEqualTo("SpinnerSex", sex);
        query.setLimit(1);

        // 検索処理タスクを生成
        final UserInfoEditCheckTask editCheckTask = new UserInfoEditCheckTask();
        editCheckTask.setListener(this);

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> list, NCMBException e) {
                if (e != null) {
                    // エラー時
                    Utility.isValidPassword = false;
                } else {
                    // 成功時
                    editCheckTask.taskStart(list, inputText);
                }
            }
        });
    }

    @Override
    public void onSuccess() {
        if(Utility.isValidPassword){
            Utility.isValidPassword = false;
            Intent intent = new Intent(getContext(), EditActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "パスワード不一致", Toast.LENGTH_SHORT).show();
        }
    }
}
