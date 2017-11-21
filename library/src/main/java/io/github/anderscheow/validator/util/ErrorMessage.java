package io.github.anderscheow.validator.util;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public abstract class ErrorMessage {

    @StringRes
    public int errorRes() {
        return -1;
    }

    @NonNull
    public  String errorMessage() {
        return "Invalid input";
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
