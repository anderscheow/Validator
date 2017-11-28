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
import io.github.anderscheow.validator.conditions.common.And;
import io.github.anderscheow.validator.conditions.common.Or;
import io.github.anderscheow.validator.constant.Mode;
import io.github.anderscheow.validator.rules.BaseRule;
import io.github.anderscheow.validator.rules.common.MaxRule;
import io.github.anderscheow.validator.rules.common.MinRule;
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
                .and(new EmailRule())
                .or(new MinRule(5))
                .or(new MaxRule(10));

        final Validation usernameWithConditionValidation = new Validation(usernameInput)
                .add(new And().add(new EmailRule()))
                .add(new Or().add(new MinRule(5)).add(new MaxRule(10)));

        final Validation passwordValidation = new Validation(passwordInput)
                .and(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return !s.isEmpty();
                    }
                })
                .and(new BaseRule() {
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
                Validator.with(getApplicationContext())
                        .setMode(Mode.CONTINUOUS)
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
