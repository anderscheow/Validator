package io.github.anderscheow.validator;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.github.anderscheow.validator.rules.BaseRule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout usernameInput = findViewById(R.id.layout_username);
        TextInputLayout passwordInput = findViewById(R.id.layout_password);
        Button submitButton = findViewById(R.id.button_submit);

        final Validation usernameValidation = new Validation(usernameInput)
                .addRule(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return !s.isEmpty();
                    }

                    @Override
                    public int errorMessage() {
                        return R.string.error_field_required;
                    }
                });

        final Validation passwordValidation = new Validation(passwordInput)
                .addRule(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return !s.isEmpty();
                    }

                    @Override
                    public int errorMessage() {
                        return R.string.error_field_required;
                    }
                })
                .addRule(new BaseRule() {
                    @Override
                    public boolean validate(String s) {
                        return s.length() >= 8;
                    }

                    @Override
                    public int errorMessage() {
                        return R.string.error_password_too_short;
                    }
                });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validator.getInstance(getApplicationContext())
                        .validate(new Validator.OnValidateListener() {
                            @Override
                            public void onValidate() {
                                Toast.makeText(getApplicationContext(), "Validate successfully", Toast.LENGTH_LONG).show();
                            }
                        },
                        usernameValidation, passwordValidation);
            }
        });
    }
}
