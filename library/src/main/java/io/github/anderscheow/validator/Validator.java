package io.github.anderscheow.validator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.constant.Mode;
import io.github.anderscheow.validator.rules.BaseRule;
import io.github.anderscheow.validator.util.ErrorMessage;

public class Validator {

    public interface OnValidateListener {
        void onValidateSuccess(List<String> values) throws IndexOutOfBoundsException;

        void onValidateFailed();
    }

    private Context context;

    private Mode mode = Mode.CONTINUOUS;

    /**
     * @deprecated Use {@link #with(Context)} )} instead.
     */
    @Deprecated
    public static Validator getInstance(@NonNull Context context) {
        return new Validator(context);
    }

    public static Validator with(@NonNull Context context) {
        return new Validator(context);
    }

    private Validator(@NonNull Context context) {
        this.context = context;
    }

    public Validator setMode(@NonNull Mode mode) {
        this.mode = mode;
        return this;
    }

    public void validate(OnValidateListener listener, Validation... validations) {
        boolean isValid = false;
        List<String> values = new ArrayList<>();

        clearAllErrors(validations);

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
            } else {
                isValid = false;

                if (mode.equals(Mode.SINGLE)) {
                    return;
                }
            }
        }

        if (isValid) {
            listener.onValidateSuccess(values);
        } else {
            listener.onValidateFailed();
        }
    }

    private void clearAllErrors(Validation... validations) {
        for (Validation validation : validations) {
            validation.getTextInputLayout().setError(null);
        }
    }

    // Iterate each type of rules
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
        if (validation.getOrRules().size() > 0) {
            BaseRule baseRule = validation.getOrRules().get(0);

            if (baseRule.validate(value)) {
                return true;
            } else {
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
