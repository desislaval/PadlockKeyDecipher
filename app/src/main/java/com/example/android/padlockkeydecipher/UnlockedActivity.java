package com.example.android.padlockkeydecipher;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.padlockkeydecipher.databinding.ActivityUnlockedBinding;

public class UnlockedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUnlockedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_unlocked);

        //Get the extra from the MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String password = bundle.getString("decipheredPass");
            //and then set it to the TextView in this Activity
            binding.showPassword.setText(password);
        }

    }

}
