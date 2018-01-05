package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import io.github.anderscheow.validator.rules.BaseRule;

public class FutureRule extends BaseRule {

    private DateFormat dateFormat;

    public FutureRule(@Nullable DateFormat dateFormat) {
        super("Does not match future rule");
        this.dateFormat = dateFormat;
    }

    public FutureRule(@Nullable DateFormat dateFormat, @StringRes int errorRes) {
        super(errorRes);
        this.dateFormat = dateFormat;
    }

    public FutureRule(@Nullable DateFormat dateFormat, @NonNull String errorMessage) {
        super(errorMessage);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Date parsedDate = null;

        if (value instanceof String) {
            if (dateFormat == null) {
                throw new NullPointerException();
            }

            try {
                parsedDate = dateFormat.parse((String) value);
            } catch (ParseException ignored) {
            }
        } else if (value instanceof Date) {
            parsedDate = (Date) value;
        } else {
            throw new ClassCastException("Required String or Date value");
        }

        return parsedDate != null && parsedDate.after(new Date());
    }
}
