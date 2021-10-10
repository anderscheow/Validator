package io.github.anderscheow.validator.sample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.Validator
import io.github.anderscheow.validator.conditions.common.and
import io.github.anderscheow.validator.conditions.common.or
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.rules.common.contain
import io.github.anderscheow.validator.rules.common.endsWith
import io.github.anderscheow.validator.rules.common.minimumLength
import io.github.anderscheow.validator.rules.common.notEmpty
import io.github.anderscheow.validator.rules.regex.email
import io.github.anderscheow.validator.validation
import io.github.anderscheow.validator.validator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<TextInputLayout>(R.id.layout_username)
        val passwordInput = findViewById<TextInputLayout>(R.id.layout_password)
        val submitButton = findViewById<Button>(R.id.button_submit)

        val usernameValidation = validation(usernameInput) {
            conditions {
                +and(errorMessage = "Does not match 'And' condition") {
                    +email(errorMessage = "")
                    +endsWith(keyword = ".com", errorMessage = "")
                }
                +or(errorMessage = "Does not match 'Or' condition") {
                    +minimumLength(minLength = 8, errorMessage = "")
                    +contain(keyword = "hello", errorMessage = "")
                }
            }
        }

        val passwordValidation = validation(passwordInput) {
            rules {
                +notEmpty(errorMessage = "Input is empty")
                +minimumLength(minLength = 8, errorMessage = "Must have at least 8 characters")
            }
        }

        submitButton.setOnClickListener {
            validator(applicationContext) {
                mode = Mode.SINGLE
                listener = object : Validator.OnValidateListener {
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
                }
                validate(usernameValidation, passwordValidation)
            }
        }
    }
}
