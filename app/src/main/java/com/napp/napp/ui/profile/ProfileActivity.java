package com.napp.napp.ui.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.napp.napp.MyApplication;
import com.napp.napp.R;
import com.napp.napp.data.model.User;
import com.napp.napp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private MyApplication myApplication;

    private ActivityProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication) this.getApplication();
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final TextView nameTextView = binding.nameTextView;
        final TextView phoneNumberTextView = binding.phoneNumberTextView;
        final TextView locationTextView = binding.locationTextView;

//        // Retrieve and de-serialise user info
//        String userJson = myApplication.getSharedPreferences().getString(myApplication.getString(R.string.preference_user), null);
//        User user = new Gson().fromJson(userJson, User.class);
//
//        nameTextView.setText(user.getDisplayName());
//        phoneNumberTextView.setText(user.getPhoneNumber());
//        locationTextView.setText(user.getUserClient().getLocation());
    }
}
