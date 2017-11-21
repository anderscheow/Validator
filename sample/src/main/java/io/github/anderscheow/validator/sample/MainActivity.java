package io.github.anderscheow.validator.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import io.github.anderscheow.validator.Validation;
import io.github.anderscheow.validator.Validator;
import io.github.anderscheow.validator.rules.BaseRule;
import io.github.anderscheow.validator.rules.regex.EmailRule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout usernameInput = findViewById(R.id.layout_username);
        TextInputLayout passwordInput = findViewById(R.id.layout_password);
        Button submitButton = findViewById(R.id.button_submit);

        final Validation usernameValidation = new Validation(usernameInput)
                .add(new EmailRule());

        final Validation passwordValidation = new Validation(passwordInput)
                .add(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return !s.isEmpty();
                    }
                })
                .add(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return s.length() >= 8;
                    }

                    @Override
                    public int errorRes() {
                        return R.string.error_password_too_short;
                    }

                    @NonNull
                    @Override
                    public String errorMessage() {
                        return "Invalid input, please try again";
                    }
                });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validator.getInstance(getApplicationContext())
                        .validate(new Validator.OnValidateListener() {

                                      @Override
                                      public void onValidateSuccess(List<String> values) {
                                          Log.d("MainActivity", Arrays.toString(values.toArray()));
                                          Toast.makeText(getApplicationContext(), "Validate successfully", Toast.LENGTH_LONG).show();
                                      }

                                      @Override
                                      public void onValidateFailed() {

                                      }
                        },
                        usernameValidation, passwordValidation);
            }
        });
    }
}
