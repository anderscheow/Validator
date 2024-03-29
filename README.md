[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Validator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6478) [![](https://jitpack.io/v/anderscheow/Validator.svg)](https://jitpack.io/#anderscheow/Validator) 
[![codecov](https://codecov.io/gh/anderscheow/Validator/branch/master/graph/badge.svg)](https://codecov.io/gh/anderscheow/Validator/branch/develop)


## Download
```groovy
dependencies {
  implementation 'com.github.anderscheow:validator:3.0.3'
}
```

Usage
-----
### Available rules

* LengthRule
* MaxRule
* MinRule
* NotEmptyRule
* NotNullRule
* RegexRule
* AlphabetRule
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
* NotBlankRule
* AllUpperCaseRule
* AllLowerCaseRule
* EndsWithRule
* StartsWithRule
* SymbolRule

### Additional predefined rules to use in Validation or Condition

* contain
* notContain
* notEqualTo
* withinRange
* minimumLength
* maximumLength
* email
* alphanumericOnly
* alphabetOnly
* digitsOnly
* symbolsOnly
* allUppercase
* allLowercase
* startsWith
* endsWith
* withCreditCard
* withPassword
* notNull
* notEmpty
* notBlank
* regex
* future
* past
* matchAtLeastOneRule (Only for Validation)
* matchAllRules (Only for Validation)

### Beside from using the provided Rules, you can create your own Rule by extending Rule (Create as many as you want)

```kotlin
class CustomRule : Rule("Value doesn't match 'ABC'") {
 
    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        }
        return value == "ABC"
    }
}
```

### Add it to your Activity class

```kotlin
// Username
// Input: hello@test.com
val usernameInput = findViewById(R.id.layout_username)
val usernameValidation = validation(usernameInput) {
    conditions {
        +and(errorMessage = "Does not match 'And' condition") {
            +email(errorMessage = "")
            +endsWith(keyword = ".com", errorMessage = "")
        }
        +or(errorMessage = "Does not match 'Or' condition") {
            +minimumLength(minLength = 8, errorMessage = "")
            +contain(keyword = "hello", errorMessage = "")
        }
    }
}
 
// Password
// Input: 12345abc
val passwordInput = findViewById(R.id.layout_password)
val passwordValidation = validation(passwordInput) {
    rules {
        +notEmpty(errorMessage = "Input is empty")
        +minimumLength(minLength = 8, errorMessage = "Must have at least 8 characters")
    }
}
```

### Mode

```kotlin
validator(applicationContext) {
    /* Set your mode here, by default is CONTINUOUS */
    mode = Mode.SINGLE
}
```

| Single                                                          | Continuous                                                      |
| ---                                                             | ---                                                             |
| ![](https://media.giphy.com/media/3ohs7YJIZfbrC7txyU/giphy.gif) | ![](https://media.giphy.com/media/3ohs84PogwMOkUg0Jq/giphy.gif) |


### Validate the input field

```kotlin
// Order of the values on the success callback follow the sequence of your Validation object
validator(applicationContext) {
    mode = Mode.SINGLE
    listener = object : Validator.OnValidateListener {
        override fun onValidateSuccess(values: List<String>) {
            Log.d("MainActivity", values.toTypedArray().contentToString())
            Toast.makeText(
                applicationContext,
                "Validate successfully",
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onValidateFailed(errors: List<String>) {
            Log.e("MainActivity", errors.toTypedArray().contentToString())
        }
    }
    validate(usernameValidation, passwordValidation)
}
```

* Introduce Validator library

## Testing
I have added unit testing for Rules and Conditions, soon will provide test code on Validation and Validator, please check it out under [Test code](https://github.com/anderscheow/Validator/tree/master/library/src/test/java/io/github/anderscheow/validator)

## Contributions
Any contribution is more than welcome! You can contribute through pull requests and issues on GitHub.

## License
Validator is released under the [MIT License](https://github.com/anderscheow/Validator/blob/master/LICENSE)