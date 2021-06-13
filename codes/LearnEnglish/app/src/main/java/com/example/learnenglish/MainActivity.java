package com.example.learnenglish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String language;
    EditText editText ;
    Button firstLanguageButton,secondLanguageButton;
    String firstLanguage,secondLanguage,resultText;
    TextView  translatedText,previousText;
    int a;
    RadioButton radioButton;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amplifyConfig();
        initBottomNavView();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
    }

    private void amplifyConfig() {
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.nav_learn:
                    selectedFragment =new LearnFragment();
                    break;
                case R.id.nav_words:
                    selectedFragment =new WordsFragment();
                    break;
                case R.id.nav_saved:
                    selectedFragment=new SaveFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();
            return true;
        }
    };

    private void initBottomNavView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
    }

    public void alert(int b){
        a=b;
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.languages,null);
        radioGroup = view.findViewById(R.id.languages);
        Button buttonApply = view.findViewById(R.id.button_apply);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog alertDialog = alert.create();
        buttonApply.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                translatedText= ((TextView) view.findViewById(R.id.translatedText));
                previousText= ((TextView) view.findViewById(R.id.previousText));