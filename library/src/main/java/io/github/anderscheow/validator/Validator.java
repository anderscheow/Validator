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

    /**
     * @deprecated Use {@link #with(Context)} )} instead.
     */
    @Deprecated
    public static Validator getInstance(Context context) {
        return new Validator(context);
    }

    public static Validator with(Context context) {
        return new Validator(context);
    }

    private Validator(Context context) {
        this.context = context;
    }

    public void validate(OnValidateListener listener, Validation... validations) {
        boolean isValid = false;
        List<String> values = new ArrayList<>();

        // Iterate each validation
        for (Validation validation : validations) {
            EditText editText = validation.getTextInputLayout().getEditText();

            if (editText == null) {
                throw new NullPointerException("TextInputLayout must include one EditText");
            }

            String value = editText.getText().toString();
            boolean isCurrentValueValid = validate(value, validation);

            if (isCurrentValueValid) {
                isValid = true;
                values.add(value);
                validation.getTextInputLayout().setError(null);
            }
        }

        if (isValid) {
            listener.onValidateSuccess(values);
        } else {
            listener.onValidateFailed();
        }
    }

    private boolean validate(String value, Validation validation) {
        boolean isCurrentValueValid = validateBaseRules(value, validation);
        if (isCurrentValueValid) {
            isCurrentValueValid = validateAndRules(value, validation);
        }
        if (isCurrentValueValid) {
            isCurrentValueValid = validateOrRules(value, validation);
        }
        if (isCurrentValueValid) {
            isCurrentValueValid = validateConditions(value, validation);
        }

        return isCurrentValueValid;
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

    private boolean validateAndRules(String value, Validation validation) {
        for (BaseRule baseRule : validation.getAndRules()) {
            if (!baseRule.validate(value)) {
                showErrorMessage(validation, baseRule);

                return false;
            }
        }

        return true;
    }

    private boolean validateOrRules(String value, Validation validation) {
        BaseRule firstFalseBaseRule = null;

        for (BaseRule baseRule : validation.getOrRules()) {
            if (baseRule.validate(value)) {
                return true;
            }

            if (firstFalseBaseRule == null) {
                firstFalseBaseRule = baseRule;
            }
        }

        showErrorMessage(validation, firstFalseBaseRule);

        return false;
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
