package com.example.taxi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxi.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ActivityMain2Binding binding;

    TextView t1, t2, t3;
    Button sp, call1, back;

    SharedPreferences prefs;


    ActivityResultLauncher<Intent> startSecondActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        if(intent != null){
                            String street1 = intent.getStringExtra("street1");
                            String street2 = intent.getStringExtra("street2");
                            String home1 = intent.getStringExtra("home1");
                            String home2 = intent.getStringExtra("home2");
                            String flat1 = intent.getStringExtra("flat1");
                            String flat2 = intent.getStringExtra("flat2");
                            t3.setText("Taxi will arrive at " + street1 + ", " + home1 + ", " + flat1 + " in 5 minutes and take you in " + street2 + ", " + home2 + ", " + flat2 + ". " + "If you are agree click Call Taxi");
                            call1.setEnabled(true);
                        }
                    }
                    else{
                        String textError = "Error!";
                        t3.setText(textError);
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        t1 = binding.textVtn1;
        t2 = binding.textVtn2;
        t3 = binding.textVtn3;
        sp = binding.btnsp;
        call1 = binding.btncall;
        back = binding.buttonback;
        sp.setOnClickListener(this);
        call1.setOnClickListener(this);
        prefs = this.getSharedPreferences("com.example.taxi", Context.MODE_PRIVATE);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("Nsurname");
        t1.setText(name);
        String number = extras.getString("Number");
        t2.setText(number);
        String in = extras.getString("info");
        t3.setText(in);
        back.setOnClickListener(this);
        call1.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsp:
                Intent intent = new Intent(this, MainActivity3.class);
                startSecondActivityForResult.launch(intent);
                break;
            case R.id.btncall:
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Wait for Taxi. Good Luck!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.buttonback:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }

    }
}