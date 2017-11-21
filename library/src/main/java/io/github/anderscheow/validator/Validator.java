package io.github.anderscheow.validator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.rules.BaseRule;

public class Validator {

    public interface OnValidateListener {
        void onValidateSuccess(List<String> values);

        void onValidateFailed();
    }

    @SuppressLint("StaticFieldLeak")
    private static Validator instance;

    private Context context;

    public static Validator getInstance(Context context) {
        if (instance == null) {
            instance = new Validator(context);
        }

        return instance;
    }

    private Validator(Context context) {
        this.context = context;
    }

    public void validate(OnValidateListener listener, Validation... validations) {
        boolean isValid = true;
        List<String> values = new ArrayList<>();

        // Iterate each validation
        for (Validation validation : validations) {
            EditText editText = validation.getTextInputLayout().getEditText();

            if (editText != null) {
                String value = editText.getText().toString();
                boolean isCurrentValueValid = true;

                // Iterate each rule in validation
                for (BaseRule baseRule : validation.getBaseRules()) {
                    if (!baseRule.validate(value)) {
                        if (baseRule.errorRes() != -1) {
                            validation.getTextInputLayout().setError(context.getString(baseRule.errorRes()));
                        } else if (!baseRule.errorMessage().isEmpty()) {
                            validation.getTextInputLayout().setError(baseRule.errorMessage());
                        } else {
                            throw new RuntimeException("Please either use errorRes or errorMessage as your error output");
                        }
                        isValid = false;
                        isCurrentValueValid = false;
                        break;
                    }
                }

                if (isCurrentValueValid) {
                    values.add(value);
                    validation.getTextInputLayout().setError(null);
                }
            } else {
                isValid = false;
            }
        }

        if (isValid) {
            listener.onValidateSuccess(values);
        } else {
            listener.onValidateFailed();
        }
    }
}
