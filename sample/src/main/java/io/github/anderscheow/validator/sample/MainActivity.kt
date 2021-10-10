package io.github.anderscheow.validator.sample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.Validator
import io.github.anderscheow.validator.conditions.common.or
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.rules.common.ContainRule
import io.github.anderscheow.validator.rules.common.MinRule
import io.github.anderscheow.validator.rules.regex.email
import io.github.anderscheow.validator.validation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<TextInputLayout>(R.id.layout_username)
        val passwordInput = findViewById<TextInputLayout>(R.id.layout_password)
        val submitButton = findViewById<Button>(R.id.button_submit)

        val usernameValidation = validation(usernameInput) {
            rules {
                +email(errorMessage = "Invalid email")
            }
            conditions {
                +or(errorMessage = "Does not match 'Or' condition") {
                    +MinRule(8, errorMessage = "Minimum 8 characters")
                    +ContainRule(".com", errorMessage = "Must contain '.com'")
                }
            }
        }

        val passwordValidation = Validation(passwordInput)
            .add(object : Rule("Input is empty") {
                override fun validate(value: String?): Boolean {
                    return (value as String).isNotEmpty()
                }
            })
            .add(object : Rule("Invalid input, please try again") {
                override fun validate(value: String?): Boolean {
                    return (value as String).length >= 8
                }
            })

        submitButton.setOnClickListener {
            Validator.with(applicationContext)
                .setMode(Mode.SINGLE)
                .setListener(object : Validator.OnValidateListener {
                    override fun onValidateSuccess(values: List<String>) {
                        Log.d("MainActivity", values.toTypedArray().contentToString())
                        Toast.makeText(
                            applicationContext,
                            "Validate successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onValidateFailed(errors: List<String>) {
                        Log.e("MainActivity", errors.toTypedArray().contentToString())
                    }
                })
                .validate(usernameValidation, passwordValidation)
        }
    }
}
