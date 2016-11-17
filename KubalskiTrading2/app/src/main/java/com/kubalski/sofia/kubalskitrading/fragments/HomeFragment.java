package com.kubalski.sofia.kubalskitrading.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kubalski.sofia.kubalskitrading.R;

/**
 * Created by sofia on 2016-05-15.
 */
public class HomeFragment extends Fragment {

    private Button sendEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, container, false);
        sendEmail = (Button) v.findViewById(R.id.button);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "edward.kubalski@swipnet.se" });
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Shipping inquiries");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    emailIntent.setType("message/rfc822");
                    startActivity(emailIntent);

                } catch (ActivityNotFoundException anfe) {
                    Toast toast = Toast.makeText(getContext(), "Sorry no email client found", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
        return v;
    }

    }


