package com.example.taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxi.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button Ok;

    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ed1 = binding.editTextstreet1;
        ed2 = binding.editTexthouse1;
        ed3 = binding.editTextflat1;
        ed4 = binding.editTextstreet2;
        ed5 = binding.editTexthouse2;
        ed6 = binding.editTextflat2;
        Ok = binding.btnok;
        Ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (ed1.getText().toString().equals("") | ed2.getText().toString().equals("") | ed3.getText().toString().equals("") | ed4.getText().toString().equals("") | ed5.getText().toString().equals("") | ed6.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Not all data is filled in", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent intent3 = new Intent(this, MainActivity2.class);
            intent3.putExtra("street1", ed1.getText().toString());
            intent3.putExtra("street2", ed4.getText().toString());
            intent3.putExtra("home1", ed2.getText().toString());
            intent3.putExtra("home2", ed5.getText().toString());
            intent3.putExtra("flat1", ed3.getText().toString());
            intent3.putExtra("flat2", ed6.getText().toString());
            setResult(RESULT_OK, intent3);
            finish();
        }
    }
}