package io.github.anderscheow.validator;

import android.content.Context;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.rules.BaseRule;
import io.github.anderscheow.validator.util.ErrorMessage;

public class Validator {

    public interface OnValidateListener {
        void onValidateSuccess(List<String> values) throws IndexOutOfBoundsException;

        void onValidateFailed();
    }

    private Context context;

    public static Validator getInstance(Context context) {
        return new Validator(context);
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

                boolean isCurrentValueValid = validateBaseRules(value, validation);
                if (isCurrentValueValid) {
                    isCurrentValueValid = validateConditions(value, validation);
                }

                if (isCurrentValueValid) {
                    values.add(value);
                    validation.getTextInputLayout().setError(null);
                } else {
                    isValid = false;
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

    private boolean validateBaseRules(String value, Validation validation) {
        for (BaseRule baseRule : validation.getBaseRules()) {
            if (!baseRule.validate(value)) {
                showErrorMessage(validation, baseRule);

                return false;
            }
        }

        return true;
    }

    private boolean validateConditions(String value, Validation validation) {
        for (Condition condition : validation.getConditions()) {
            if (!condition.validate(value)) {
                showErrorMessage(validation, condition);

                return false;
            }
        }

        return true;
    }

    private void showErrorMessage(Validation validation, ErrorMessage errorMessage) {
        if (errorMessage.isErrorAvailable()) {
            if (errorMessage.isErrorResAvailable() ) {
                validation.getTextInputLayout().setError(context.getString(errorMessage.errorRes()));
            } else if (errorMessage.isErrorMessageAvailable()) {
                validation.getTextInputLayout().setError(errorMessage.errorMessage());
            }
        } else {
            throw new RuntimeException("Please either use errorRes or errorMessage as your error output");
        }
    }
}
