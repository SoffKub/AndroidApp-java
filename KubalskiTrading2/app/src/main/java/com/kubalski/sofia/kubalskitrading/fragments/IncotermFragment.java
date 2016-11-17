package com.kubalski.sofia.kubalskitrading.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kubalski.sofia.kubalskitrading.R;

import java.util.Locale;

/**
 * Created by sofia on 2016-05-15.
 */
public class IncotermFragment extends Fragment {

    TextView dynamicTextView;
    ImageView imageView;
    ImageView fob_button;
    ImageView cif_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.incoterm_layout, container, false);

        imageView = (ImageView)v.findViewById(R.id.incoterm_image);
        incotermSelector(v);

        initiateOnClick();
        return v;
    }

    public void incotermSelector(View v){

        dynamicTextView = (TextView)v.findViewById(R.id.dynamic_text);
        fob_button = (ImageView)v.findViewById(R.id.fob_button);
        cif_button = (ImageView)v.findViewById(R.id.cif_button);


        Log.i("locale", Locale.getDefault().getDisplayLanguage());

        fob_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fob_button.setImageResource(R.drawable.fob_selected);
                cif_button.setImageResource(R.drawable.cif_unselected);
                dynamicTextView.setText(R.string.fob_string);
            }
        });
        cif_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicTextView.setText(R.string.cif_string);
                cif_button.setImageResource(R.drawable.cif_selected);
                fob_button.setImageResource(R.drawable.fob_unselected);
            }
        });
    }


    public void initiateOnClick(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.ndffreight.co.uk/inco-terms/"));
                startActivity(intent);
            }
        });
    }

}
