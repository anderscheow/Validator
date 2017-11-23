package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import io.github.anderscheow.validator.rules.BaseRule;

public class FutureRule extends BaseRule {

    private DateFormat dateFormat;

    public FutureRule(DateFormat dateFormat) {
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

        return parsedDate != null && parsedDate.after(new Date());
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Does not match future rule";
    }
}
