package com.example.android.padlockkeydecipher;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.padlockkeydecipher.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Setting an onClickListener to the decipher button. It then deciphers the password, shows it in a TextView
        // and with transition animation opens next Activity
        binding.decipherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.passwordEditText.getText().toString();
                String decipheredPass = decipher(password);
                binding.decipheredPassword.setText(decipheredPass);

                Intent unlockedIntent = new Intent(MainActivity.this, UnlockedActivity.class);
                unlockedIntent.putExtra("decipheredPass", decipheredPass);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                        binding.decipheredPassword, "decipheredPassTransition");

                startActivity(unlockedIntent, options.toBundle());
            }
        });
    }

    /**
     * Method to decipher the password given by the user in the EdiText field
     * @param s storing the input from the EditText field
     * @return deciphered password in a String
     */
    private static String decipher(String s) {
        //Counter that is as big as the Extended ASCII table
        int[] count = new int[256];

        //Creating a new ArrayList that will hold all the different characters found in the string
        List<Character> lettersChar = new ArrayList<>();

        //For the string length we add every new letter that we encounter into the lettersChar ArrayList
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i) returns the char in that position of the string. Putting it in the [] of tha char array
            //char['a'] is the same as char[97]. So we store how many times we encountered the letter a (position 97 of our char array
            //by incrementing the counter for every encounter of the letter
            count[s.charAt(i)]++;
            //If the letter is not in the lettersChar ArrayList we add it in it
            if (!lettersChar.contains(s.charAt(i))) {
                lettersChar.add(s.charAt(i));
            }
        }

        //Transfer all the letters from the ArrayList to char array, so that we can add them in pur custom class
        char[] chars = new char[lettersChar.size()];
        for (int i = 0; i < lettersChar.size(); i++) {
            chars[i] = lettersChar.get(i);
        }

        //make a ArrayList witch holds objects of our custom class CharPair(char, int) - how many times each character is in the string
        List<CharPair> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            list.add(new CharPair(chars[i], count[chars[i]]));
        }

        //Sorting the array with our custom CountComparator class.
        //We sort them by the number of occurrences of each char in our String
        Collections.sort(list);

        //Create a new char array to store the first 4 letters of the sorted ArrayList
        char[] cut = new char[4];
        for (int i = 0; i < 4; i++) {
            cut[i] = list.get(i).getChar();
        }

        //Then convert the char[] to string and return it
        return new String(cut);
    }

}
