## Download

```groovy
//dependencies
compile 'io.github.anderscheow:validator:1.0.1'
```

## Version

**1.0.1** 

* Added some common rules (LengthRule, MinRule, MaxRule, RegexRule etc.)

* Able to use String as error message

**1.0.0**

* Introduce Validator library

Usage
-----
### Beside from using the provided Rules, you can create your own Rule by extending BaseRule (Create as many as you want)

```java
public class CustomRule extends BaseRule {

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value.equals("ABC");
    }

    // You can use this to return error message
    @NonNull
    @Override
    public String errorMessage() {
        return "Value doesn't match 'ABC'";
    }

    // or use this to return error message as StringRes

    @Override
    public int errorRes() {
        return R.string.error_not_match;
    }
}
```

### Add it to your Activity class

```java
// Username
TextInputLayout usernameInput = findViewById(R.id.layout_username);
final Validation usernameValidation = new Validation(usernameInput)
                .addRule(new NotEmptyRule() {   
                    // You can also override the default error message
                    @Override
                    public int errorRes() {
                        return R.string.error_not_empty;
                    }

                    // Use either errorRes() or errorMessage()
                    // Note: errorRes() has higher priority

                    @NonNull
                    @Override
                    public String errorMessage() {
                        return "Value is empty";
                    }
                })
                .addRule(new CustomRule());

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
