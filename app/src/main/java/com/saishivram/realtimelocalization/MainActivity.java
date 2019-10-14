package com.saishivram.realtimelocalization;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button changeLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeLanguage=findViewById(R.id.chlng);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("RealTimeLocalization");
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangelanguage();
            }
        });
    }

    private void showchangelanguage() {
        final String[] list={"Telugu","Hindi","English"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change Language");
        builder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    setLocale("TE");
                    recreate();
                }
                else if(which==1)
                {
                    setLocale("HI");
                    recreate();
                }
                else
                {
                    setLocale("EN");
                    recreate();
                }

                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void setLocale(String te) {
        Locale locale=new Locale(te);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("MY_TE",te);
        editor.apply();

    }
    public void loadlocale()
    {
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("MY_Te","");
        setLocale(language);
    }
}
