package io.github.anderscheow.validator.sample

import android.os.Bundle
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.Validator
import io.github.anderscheow.validator.conditions.common.And
import io.github.anderscheow.validator.conditions.common.Or
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.rules.common.MaxRule
import io.github.anderscheow.validator.rules.common.MinRule
import io.github.anderscheow.validator.rules.regex.EmailRule
import io.github.anderscheow.validator.util.email
import io.github.anderscheow.validator.util.matchAtLeastOneRule
import java.util.*

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
                .add(object : BaseRule() {
                    override fun validate(value: Any?): Boolean {
                        return !(value as String).isEmpty()
                    }
                })
                .add(object : BaseRule() {
                    override fun validate(value: Any?): Boolean {
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
                    .setMode(Mode.CONTINUOUS)
                    .setListener(object : Validator.OnValidateListener {
                        override fun onValidateSuccess(values: List<String>) {
                            Log.d("MainActivity", Arrays.toString(values.toTypedArray()))
                            Toast.makeText(applicationContext, "Validate successfully", Toast.LENGTH_LONG).show()
                        }

                        override fun onValidateFailed() {
                        }
                    })
                    .validate(usernameValidation, passwordValidation)
        }
    }
}
