package io.github.anderscheow.validator.util;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public abstract class ErrorMessage {

    private int errorRes = -1;

    private String errorMessage = "Invalid input";

    @StringRes
    public int errorRes() {
        return errorRes;
    }

    @NonNull
    public String errorMessage() {
        return errorMessage;
    }

    public void setErrorRes(@StringRes int errorRes) {
        this.errorRes = errorRes;
    }

    public void setErrorMessage(@NonNull String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isErrorAvailable() {
        return isErrorResAvailable() || isErrorMessageAvailable();
    }

    public boolean isErrorResAvailable() {
        return errorRes() != -1;
    }

    public boolean isErrorMessageAvailable() {
        return !errorMessage().isEmpty();
    }
}
