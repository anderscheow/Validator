package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import io.github.anderscheow.validator.rules.BaseRule;

public class PastRule extends BaseRule {

    private DateFormat dateFormat;

    public PastRule(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public PastRule(DateFormat dateFormat, @StringRes int errorRes) {
        super(errorRes);
        this.dateFormat = dateFormat;
    }

    public PastRule(DateFormat dateFormat, @NonNull String errorMessage) {
        super(errorMessage);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(value);
        } catch (ParseException ignored) {}

        return parsedDate != null && parsedDate.before(new Date());
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Does not match past rule";
    }
}
