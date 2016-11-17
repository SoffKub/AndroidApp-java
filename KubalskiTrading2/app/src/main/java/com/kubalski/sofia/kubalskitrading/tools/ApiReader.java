package com.kubalski.sofia.kubalskitrading.tools;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by sofia on 2016-05-18.
 */
public class ApiReader extends AsyncTask<URL, Integer, String> {

    private URL url1;
    private URL url2;
    private BufferedReader br;
    private String str;
    private String str2;

    public ApiReader(){
        }
    @Override
    protected String doInBackground(URL... urls) {
       try{

            br = new BufferedReader(new InputStreamReader(urls[0].openStream()));
           StringBuffer buffer = new StringBuffer();
           int read;
           char[] chars = new char[1024];
           while ((read = br.read(chars)) != -1) {
               buffer.append(chars, 0, read);
           }
           str = buffer.toString();
           buffer.setLength(0);
           br = new BufferedReader(new InputStreamReader(urls[1].openStream()));
           while ((read = br.read(chars)) != -1) {
               buffer.append(chars, 0, read);
           }
           str2 = buffer.toString();

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return str + ";" + str2;
    }

}
