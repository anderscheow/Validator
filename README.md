[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Validator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6478) [![](https://jitpack.io/v/anderscheow/Validator.svg)](https://jitpack.io/#anderscheow/Validator) 
[![codecov](https://codecov.io/gh/anderscheow/Validator/branch/master/graph/badge.svg)](https://codecov.io/gh/anderscheow/Validator/branch/develop)


## Download
```groovy
dependencies {
  implementation 'com.github.anderscheow:validator:2.2.1'
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

#### You can create your own Predefined Rules

```kotlin
// For Validation
fun Validation.customPredefinedRule(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(ContainRule(keyword, ignoreCase))
    return this
}
 
// For Condition
fun Condition.customPredefinedRule(keyword: String, ignoreCase: Boolean = false): Condition {
    baseRules.add(ContainRule(keyword, ignoreCase))
    return this
}
```

### Beside from using the provided Rules, you can create your own Rule by extending BaseRule (Create as many as you want)

```kotlin
class CustomRule : BaseRule {
 
    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        }
        return value == "ABC"
    }
 
    // You can use this to return error message
    override fun getErrorMessage(): String {
        return "Value doesn't match 'ABC'"
    }
 
    // or use this to return error message as StringRes
    override fun getErrorRes(): Int {
        return R.string.error_not_match
    }
}
```

### Add it to your Activity class

```kotlin
// Username
// Input: hello@test.com
val usernameInput = findViewById(R.id.layout_username)
val usernameValidation = Validation(usernameInput)
                .addRule(
                    // You can also override the default error message
                    NotEmptyRule(R.string.error_not_empty)
                     
                    // Use either errorRes() or errorMessage()
                    // Note: errorRes() has higher priority
                    NotEmptyRule("Value is empty")
                )
                .addRule(CustomRule())
 
// Password
// Input: 12345abc
val passwordInput = findViewById(R.id.layout_password)
val passwordValidation = Validation(passwordInput)
                .addRule(NotEmptyRule())
                .withPassword(PasswordRegex.ALPHA_NUMERIC)
                .minimumLength(8)
```

### Condition

```kotlin
// And Condition
val usernameWithConditionValidation = Validation(usernameInput)
                .add(And().add(EmailRule()))
 
// Or Condition
val usernameWithConditionValidation = Validation(usernameInput)
                .add(Or().add(MinRule(5)).add(MaxRule(10)))
 
// Both by using Predefined Rules
val usernameWithConditionValidation = new Validation(usernameInput)
                .matchAllRules(listOf(EmailRule()))
                .matchAtLeastOneRule(listOf(MinRule(5), MaxRule(10)))
```

### Mode

```kotlin
Validator.with(applicationContext)
            /* Set your mode here, by default is CONTINUOUS */
            .setMode(Mode.CONTINUOUS)
```

| Single                                                          | Continuous                                                      |
| ---                                                             | ---                                                             |
| ![](https://media.giphy.com/media/3ohs7YJIZfbrC7txyU/giphy.gif) | ![](https://media.giphy.com/media/3ohs84PogwMOkUg0Jq/giphy.gif) |


### Validate the input field

```kotlin
// Order of the values on the success callback follow the sequence of your Validation object
Validator.with(applicationContext)
            .setMode(Mode.CONTINUOUS)
            .setListener(object : Validator.OnValidateListener {
                    override fun onValidateSuccess(values: List<String>) {
                        Log.d("MainActivity", values.toTypedArray().contentToString())
                        Toast.makeText(applicationContext, "Validate successfully", Toast.LENGTH_LONG).show()
                    }

                    override fun onValidateFailed(errors: List<String>) {
                        Log.e("MainActivity", errors.toTypedArray().contentToString())
                    }
                })
            .validate(usernameValidation, passwordValidation)
```

* Introduce Validator library

## Testing
I have added unit testing for Rules and Conditions, soon will provide test code on Validation and Validator, please check it out under [Test code](https://github.com/anderscheow/Validator/tree/master/library/src/test/java/io/github/anderscheow/validator)

## Contributions
Any contribution is more than welcome! You can contribute through pull requests and issues on GitHub.

## License
Validator is released under the [MIT License](https://github.com/anderscheow/Validator/blob/master/LICENSE)