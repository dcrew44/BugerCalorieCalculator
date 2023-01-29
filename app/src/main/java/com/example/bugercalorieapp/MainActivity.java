package com.example.bugercalorieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pattyRG;
    private CheckBox prosciuttoCB;
    private RadioGroup cheeseRG;
    private SeekBar sauceBar;
    private TextView caloriesTV;

    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        burger = new Burger();
        initialize();

        registerChangeListner();
    }

    private void initialize() {
        pattyRG = (RadioGroup) findViewById(R.id.radioGroup1);
        prosciuttoCB = (CheckBox) findViewById(R.id.checkBox3);
        cheeseRG = (RadioGroup) findViewById(R.id.radioGroup2);
        sauceBar = (SeekBar) findViewById(R.id.seekBar);
        caloriesTV = (TextView) findViewById(R.id.textView2);

        displayCalories();
    }

    private void registerChangeListner() {
        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCB.setOnClickListener(prosciuttoListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceBar.setOnSeekBarChangeListener(sauceListener);

    }

    private RadioGroup.OnCheckedChangeListener foodListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.radioButton:
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case R.id.radioButton2:
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case R.id.radioButton3:
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case R.id.radioButton6:
                    burger.setCheeseCalories(Burger.ASIAGO);
                    break;
                case R.id.radioButton7:
                    burger.setCheeseCalories(Burger.CREME_FRAICHE);
                    break;
            }
            displayCalories();
        }
    };

    private CheckBox.OnClickListener prosciuttoListener = new CheckBox.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(prosciuttoCB.isChecked()) {
                burger.setProsciuttoCalories(Burger.PROSCIUTTO);
            } else {
                burger.setProsciuttoCalories(0);
            }
            displayCalories();
        }
    };

    private SeekBar.OnSeekBarChangeListener sauceListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            burger.setSauceCalories(seekBar.getProgress());
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void displayCalories() {
        caloriesTV.setText("Calories: "+ burger.getTotalCalories());

    }
}