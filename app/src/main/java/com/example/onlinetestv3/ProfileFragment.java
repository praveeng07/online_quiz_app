package com.example.onlinetestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedPrefHandler sharedPrefHandler;
    Button updateUserData;
    EditText txtNewUserName,txtNewPass;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPrefHandler = new SharedPrefHandler(requireContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        updateUserData = view.findViewById(R.id.updateUserData);
        txtNewPass = view.findViewById(R.id.txtNewPass);
        txtNewUserName = view.findViewById(R.id.txtNewUserName);

        updateUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPrefHandler.setNewUserName(txtNewUserName.getText().toString());
                sharedPrefHandler.setNewUserPass(txtNewPass.getText().toString());


                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setTitle("Verify yourself");

                final View customLayout = getLayoutInflater().inflate(R.layout.custom_verify_pass, null);
                builder.setView(customLayout);


                builder.setPositiveButton("OK", (dialog, which)->{
                    EditText oldPassWord = customLayout.findViewById(R.id.txtVerifyPass);
                    String oldPass = oldPassWord.getText().toString();
                    if(oldPass.equals(sharedPrefHandler.getLoginPassword().toString())){
                        sharedPrefHandler.saveLoginDetails(txtNewUserName.getText().toString(), txtNewPass.getText().toString());
                        Toast.makeText(getContext(), "User details updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                });


                AlertDialog mDailog = builder.create();
                mDailog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        mDailog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    }
                });
                mDailog.show();
            }
        });

        return view;
    }

}