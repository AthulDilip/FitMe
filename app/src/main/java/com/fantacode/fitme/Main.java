package com.fantacode.fitme;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import  android.support.v7.app.ActionBar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main extends Activity{

    public static XmlParser x;
    static List<String> quotes = new ArrayList<String>();
    int n = 1;
    static TextView emsg;
    static TextView qstn;
    static Typeface face;
    static AdView mAdView;
    static AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String questions[] = {"Still don't want workout?","Still not motivated?","Still want to eat junk?","Still don't believe in it?"};
        int bg[] = {R.drawable.quoteback1,R.drawable.quoteback2,R.drawable.quoteback3,R.drawable.quoteback4,R.drawable.quoteback5};

        LinearLayout  linearLayout = (LinearLayout) findViewById(R.id.linearLayoutid);
        linearLayout.setBackgroundResource(bg[(int)Math.round(Math.random()*4)]);

        x = new XmlParser(this);

        quotes = x.getQuotes(getIntent().getExtras().getString("type"));
        Collections.shuffle(quotes);

        emsg = (TextView)findViewById(R.id.msgtv);
        face = Typeface.createFromAsset(getAssets(), "fonts/ardarling.ttf");
        emsg.setTypeface(face);
        emsg.setText(Html.fromHtml(quotes.get(0)));

        qstn = (TextView)findViewById(R.id.alertQoute);
        face = Typeface.createFromAsset(getAssets(), "fonts/ardarling.ttf");
        qstn.setTypeface(face);
        qstn.setText(questions[getIntent().getExtras().getInt("typenum")-1]);

        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void yesClicked(View view){
        onBackPressed();
    }

    public void noClicked(View view){
        if(n>=quotes.size()){
            n=0;
            Collections.shuffle(quotes);
        }
        String qoute = quotes.get(n);
        emsg.setText(Html.fromHtml(qoute));
        ++n;
    }

    public void aboutClicked(View view){
        Intent intent = new Intent(Main.this,About.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}