package com.example.onlinetestv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ContactUs extends Fragment {

    public ContactUs() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ImageView emailIcon, instagramIcon, facebookIcon;
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);
        emailIcon = v.findViewById(R.id.emailIcon);
        instagramIcon = v.findViewById(R.id.instagramIcon);
        facebookIcon = v.findViewById(R.id.facebookIcon);

        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:academic.section@manipal.edu"));
                startActivity(emailIntent);
            }
        });

        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Instagram app or website
                Uri instagramUri = Uri.parse("https://www.instagram.com/mahe_manipal");
                Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instagramUri);
                startActivity(instagramIntent);
            }
        });

        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Facebook app or website
                Uri facebookUri = Uri.parse("https://www.facebook.com/mit.manipal");
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, facebookUri);
                startActivity(facebookIntent);
            }
        });

        return v;
    }
}