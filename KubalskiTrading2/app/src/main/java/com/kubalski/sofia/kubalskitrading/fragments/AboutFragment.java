package com.kubalski.sofia.kubalskitrading.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubalski.sofia.kubalskitrading.R;

import org.w3c.dom.Text;

/**
 * Created by sofia on 2016-05-15.
 */
public class AboutFragment extends Fragment {

    TextView textView;
    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.foryou_layout, container, false);

        colorTextView(v);

        return v;
    }

    public void colorTextView(View v){


    }
}
