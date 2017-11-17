package io.github.anderscheow.validator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.EditText;

import io.github.anderscheow.validator.rules.BaseRule;

public class Validator {

    public interface OnValidateListener {
        void onValidate();
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

        for (Validation validation : validations) {
            EditText editText = validation.getTextInputLayout().getEditText();

            if (editText != null) {
                String s = editText.getText().toString();

                for (BaseRule baseRule : validation.getBaseRules()) {
                    if (!baseRule.validate(s)) {
                        validation.getTextInputLayout().setError(context.getString(baseRule.errorMessage()));
                        isValid = false;
                        break;
                    }
                }
            } else {
                isValid = false;
            }
        }

        if (isValid) {
            listener.onValidate();
        }
    }
}
