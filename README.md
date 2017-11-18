## Download

```groovy
//dependencies
compile 'io.github.anderscheow:validator:1.0.0'
```

Usage
-----
### Create your own Rule class (as many as you can)

```java
public class NotEmptyRule implements BaseRule {

    @Override
    public boolean validate(String s) {
        return !s.isEmpty();
    }

    @StringRes
    @Override
    public int errorMessage() {
        return R.string.input_error_field_required;
    }
}

public class PasswordLengthRule implements BaseRule {

    @Override
    public boolean validate(String s) {
        return s.length() >= 8;
    }

    @StringRes
    @Override
    public int errorMessage() {
        return R.string.error_invalid_field_login;
    }
}
```

### Add it to your Activity class

```java
// Username
TextInputLayout usernameInput = findViewById(R.id.layout_username);
final Validation usernameValidation = new Validation(usernameInput)
                .addRule(new NotEmptyRule());

// Password
TextInputLayout passwordInput = findViewById(R.id.layout_password);
final Validation passwordValidation = new Validation(passwordInput)
                .addRule(new NotEmptyRule())
                .addRule(new PasswordLengthRule());
```

### Validate the input field

```java
Validator.getInstance(getApplicationContext())
                        .validate(new Validator.OnValidateListener() {
                            @Override
                            public void onValidate() {
                                Toast.makeText(getApplicationContext(), "Validate successfully", Toast.LENGTH_LONG).show();
                            }
                        },
                        usernameValidation, passwordValidation);
```
