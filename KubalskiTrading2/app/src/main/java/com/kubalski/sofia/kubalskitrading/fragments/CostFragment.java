package com.kubalski.sofia.kubalskitrading.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kubalski.sofia.kubalskitrading.R;
import com.kubalski.sofia.kubalskitrading.tools.ApiReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;

/**
 * Created by sofia on 2016-05-15.
 */
public class CostFragment extends Fragment {

    private String converter;
    private EditText editText;
    private TextView costUnit;
    private TextView textView;
    private ImageView kiloImage;
    private ImageView volumeImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.cost_layout, container, false);
        readUrl();
        try {
            convertInput(v);
            initiateImages(v);
            initiateOnClick(v);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        textView = (TextView) v.findViewById(R.id.textView);


        return v;
    }

    public void convertInput(View v){
        editText = (EditText) v.findViewById(R.id.editText);
        editText.getBackground().setColorFilter(Color.parseColor("#6466ed"), PorterDuff.Mode.SRC_IN);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    try {

                        calculateCost(new BigDecimal(s.toString()));
                        textView.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    textView.setText("");
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
    public void readUrl(){
        try {
            converter = new ApiReader().execute(new URL("https://blockchain.info/tobtc?currency=SEK&value=1"), new URL("https://blockchain.info/tobtc?currency=USD&value=1")).get();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void initiateOnClick(View v){
        costUnit = (TextView)v.findViewById(R.id.cost_unit);
        kiloImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiloImage.setImageResource(R.drawable.kilo_selected);
                volumeImage.setImageResource(R.drawable.volume_unselected);
                costUnit.setText(getResources().getText(R.string.cost_unit_kg));
            }
        });
        volumeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiloImage.setImageResource(R.drawable.kilo_unselected);
                volumeImage.setImageResource(R.drawable.volume_selected);
                costUnit.setText(getResources().getText(R.string.cost_unit_m));
            }
        });

    }

    public void initiateImages(View v){
        kiloImage = (ImageView)v.findViewById(R.id.kilo_button);
        volumeImage = (ImageView)v.findViewById(R.id.volume_button);
    }

    public void calculateCost(BigDecimal input) throws IOException {

        String[] values = converter.split(";");
        BigDecimal USD = new BigDecimal(values[1]);
        BigDecimal SEK = new BigDecimal(values[0]);
        SEK = SEK.divide(USD, 20, BigDecimal.ROUND_HALF_EVEN);
        SEK = BigDecimal.ONE.divide(SEK, 20, BigDecimal.ROUND_HALF_EVEN);

        if(input.compareTo(BigDecimal.ZERO) == 1) {

            int initialCost = 39;
            if (input.compareTo(new BigDecimal(1000)) == -1) {
                input = new BigDecimal(initialCost);
            } else {
                input = input.divide(new BigDecimal(1000)).multiply(new BigDecimal(initialCost));
            }
            input = input.multiply(new BigDecimal(1.1));

            textView.setText("SEK: "+ input.multiply(SEK).setScale(0, RoundingMode.HALF_UP) + " USD: " + input.setScale(0, RoundingMode.HALF_UP));
        }
    }
}
