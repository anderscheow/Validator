[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Validator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6478) [![](https://jitpack.io/v/anderscheow/Validator.svg)](https://jitpack.io/#anderscheow/Validator)
[![Build Status](https://travis-ci.org/anderscheow/Validator.svg?branch=master)](https://travis-ci.org/anderscheow/Validator) [![codecov](https://codecov.io/gh/anderscheow/Validator/branch/master/graph/badge.svg)](https://codecov.io/gh/anderscheow/Validator/branch/develop)


## Download

Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    ... 
    
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency
```groovy
dependencies {
  compile 'io.github.anderscheow:validator:1.1.1'
}
```

## Version

**1.1.1 (Newest)**

* Fixed bug where overloading the constructor with errorMessage or errorRes does not override the default value

**1.1.0**

* Added ability to add conditions (And or Or)

* Added mode of validation (Single or Continuous)

* Added ability to override errorRes or errorMessage in constructor without overriding the methods

**1.0.4**

* Added more rules, please check at 'Available Rules'

**1.0.3**

* Fixed LengthRule wrong validation on maxValue

**1.0.2**

* Added success and failed callback instead of just success callback

* Success callback return list of EditText values (Order by sequence of Validation object(s))

**1.0.1** 

* Added some common rules (LengthRule, MinRule, MaxRule, RegexRule etc.)

* Able to use String as error message

**1.0.0**

* Introduce Validator library

Usage
-----
### Available Rules

* LengthRule
* MaxRule
* MinRule
* NotEmptyRule
* NotNullRule
* RegexRule
* AlphanumericRule
* DigitsRule
* EmailRule
* PasswordRule
* FutureRule
* PastRule
* CreditCardRule
* ContainRule
* NotContainRule
* EqualRule
* NotEqualRule

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
// Input: hello@test.com
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

// You can also use the constructor to override errorRes or errorMessage
final Validation usernameValidation = new Validation(usernameInput)
                .addRule(new NotEmptyRule(R.string.error_not_empty));
                
final Validation usernameValidation = new Validation(usernameInput)
                .addRule(new NotEmptyRule("Value is empty"));

// Password
// Input: 12345abc
TextInputLayout passwordInput = findViewById(R.id.layout_password); 
final Validation passwordValidation = new Validation(passwordInput)
                .addRule(new NotEmptyRule())
                .addRule(new PasswordRule(PasswordRegex.ALPHA_NUMERIC))
                .addRule(new MinRule(8));
```

### Condition

```java
// And Condition
final Validation usernameWithConditionValidation = new Validation(usernameInput)
                .add(new And().add(new EmailRule()));

// Or Condition
final Validation usernameWithConditionValidation = new Validation(usernameInput)
                .add(new Or().add(new MinRule(5)).add(new MaxRule(10)));

// Both
final Validation usernameWithConditionValidation = new Validation(usernameInput)
                .add(new And().add(new EmailRule()))
                .add(new Or().add(new MinRule(5)).add(new MaxRule(10)));
```

### Mode

```java
Validator.getInstance(getApplicationContext())
                        /* Set your mode here, by default is CONTINUOUS */
                        .setMode(Mode.CONTINUOUS));
```

| Single                                                          | Continuous                                                      |
| ---                                                             | ---                                                             |
| ![](https://media.giphy.com/media/3ohs7YJIZfbrC7txyU/giphy.gif) | ![](https://media.giphy.com/media/3ohs84PogwMOkUg0Jq/giphy.gif) |


### Validate the input field

```java
// Order of the values on the success callback follow the sequence of your Validation object
Validator.getInstance(getApplicationContext())
                        .setMode(Mode.CONTINUOUS))
                        .validate(new Validator.OnValidateListener() {

                                      @Override
                                      public void onValidateSuccess(List<String> values) {
                                          Log.d("MainActivity", Arrays.toString(values.toArray())); // Output: [hello@test.com, 12345abc]
                                          Toast.makeText(getApplicationContext(), "Validate successfully", Toast.LENGTH_LONG).show();
                                      }

                                      @Override
                                      public void onValidateFailed() {

                                      }
                        },
                        usernameValidation, passwordValidation);
```

## Contributions
Any contribution is more than welcome! You can contribute through pull requests and issues on GitHub.

## License
Validator is released under the [Apache License](https://github.com/anderscheow/Validator/blob/master/LICENSE)