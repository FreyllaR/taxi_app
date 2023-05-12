package com.example.taxi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taxi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    EditText number, name, surname;
    Button reg;
    SharedPreferences prefs;
    public static final String SP_NAME = "spName";
    public static final String SP_KEY_FIRST_START = "spKeyFirstStart";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        number = binding.editTextTextTelephoneNumber;
        name = binding.editTextName;
        surname = binding.editTextSurname;
        reg = binding.buttonr;
        reg.setOnClickListener(this);
        prefs = this.getSharedPreferences("com.example.taxi", Context.MODE_PRIVATE);
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        boolean firstStart = sp.getBoolean(SP_KEY_FIRST_START, true);
        if(firstStart) {
            sp.edit().putBoolean(SP_KEY_FIRST_START, false).apply();
            reg.setText("registration");
        } else {
            reg.setText("log in");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        prefs.edit().putString("num", number.getText().toString()).apply();
        prefs.edit().putString("nam", name.getText().toString()).apply();
        prefs.edit().putString("sur", surname.getText().toString()).apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        number.setText(prefs.getString("num", ""));
        name.setText(prefs.getString("nam", ""));
        surname.setText(prefs.getString("sur", ""));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("Nsurname", name.getText().toString() + " " + surname.getText().toString());
        intent.putExtra("Number", number.getText().toString());
        startActivity(intent);
    }
}