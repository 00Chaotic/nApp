package com.napp.napp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.napp.napp.Homepage;
import com.napp.napp.MyApplication;
import com.napp.napp.R;
import com.napp.napp.databinding.ActivityLoginBinding;
import com.napp.napp.ui.profile.ProfileActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private MyApplication myApplication;
    Button SignIn;
//    private LoginViewModel loginViewModel;
//    //private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication) this.getApplication();
        SignIn =  (Button) findViewById(R.id.login);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Homepage.class));
            }
        });

    }
}