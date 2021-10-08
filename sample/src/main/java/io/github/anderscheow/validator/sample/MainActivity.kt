package io.github.anderscheow.validator.sample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.Validator
import io.github.anderscheow.validator.conditions.common.And
import io.github.anderscheow.validator.conditions.common.Or
import io.github.anderscheow.validator.conditions.common.matchAtLeastOneRule
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.rules.common.MaxRule
import io.github.anderscheow.validator.rules.common.MinRule
import io.github.anderscheow.validator.rules.regex.EmailRule
import io.github.anderscheow.validator.rules.regex.email

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<TextInputLayout>(R.id.layout_username)
        val passwordInput = findViewById<TextInputLayout>(R.id.layout_password)
        val submitButton = findViewById<Button>(R.id.button_submit)

        val usernameValidation = Validation(usernameInput)
                .email()
                .matchAtLeastOneRule(arrayOf(MinRule(5), MaxRule(10)))

        val usernameWithConditionValidation = Validation(usernameInput)
                .add(And().add(EmailRule()))
                .add(Or().add(MinRule(5)).add(MaxRule(10)))

        val passwordValidation = Validation(passwordInput)
                .add(object : Rule() {
                    override fun validate(value: String?): Boolean {
                        return (value as String).isNotEmpty()
                    }
                })
                .add(object : Rule() {
                    override fun validate(value: String?): Boolean {
                        return (value as String).length >= 8
                    }

                    override fun getErrorRes(): Int {
                        return R.string.error_password_too_short
                    }

                    override fun getErrorMessage(): String {
                        return "Invalid input, please try again"
                    }
                })

        submitButton.setOnClickListener {
            Validator.with(applicationContext)
                    .setMode(Mode.SINGLE)
                    .setListener(object : Validator.OnValidateListener {
                        override fun onValidateSuccess(values: List<String>) {
                            Log.d("MainActivity", values.toTypedArray().contentToString())
                            Toast.makeText(applicationContext, "Validate successfully", Toast.LENGTH_LONG).show()
                        }

                        override fun onValidateFailed(errors: List<String>) {
                            Log.e("MainActivity", errors.toTypedArray().contentToString())
                        }
                    })
                    .validate(usernameValidation, passwordValidation)
        }
    }
}
