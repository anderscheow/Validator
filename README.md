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
  compile 'io.github.anderscheow:validator:1.3.1'
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
 
    override fun validate(value: Any?): Boolean {
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
            .setMode(Mode.CONTINUOUS));
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
                        Log.d("MainActivity", Arrays.toString(values.toTypedArray()))
                        Toast.makeText(applicationContext, "Validate successfully", Toast.LENGTH_LONG).show()
                    }

                    override fun onValidateFailed() {
                        Toast.makeText(applicationContext, "Validate failed", Toast.LENGTH_LONG).show()
                    }
                })
            .validate(usernameValidation, passwordValidation)
```

### **New Feature:** Validation does support for `Object (Java)` and `Any (Kotlin)` parameter
```kotlin
val usernameWithConditionValidation = Validation("test@email.com")
                .add(And().add(EmailRule()))
```

## Changelog

**1.3.1**

* Addressed some algorithm issues
* Added more test cases

**1.3.0**

* Removed Validation.and() and Validation.or() by encouraging user to use Condition
* Removed listener parameter from Validator.validate() and required to assigned manually using Validator.setListener()
* Added some predefined rules in Validation and Condition to simplify procedure on adding rules

**1.2.2**

* Added Dokka to show Kotlin sources

**1.2.1**

* Removed generic type from BaseRule as there's a limitation

* validate() only accept "Any?" as parameter

**1.2.0**

* **For version lower than 1.2.0, upgrading to latest version may broke your code. Use it at your risk**

* Updated to Kotlin

* Validation does support for `Object` parameter instead of just TextInputLayout (Refer to example below)

* Set TextInputLayout.setErrorEnabled(false) on every Validate method called

**1.1.5**

* Removed unwanted log

**1.1.4**

* Fixed an issue where validate will success if last validation pass the rules in Mode.CONTINUOUS

* RegexRule now is open class rather than abstract class

**1.1.3**

* Input can be any object, previously restrict to String (Along with proper validation with different object)

* Added more test cases to validate input

**1.1.2**

* Updated ALPHA_NUMERIC_SYMBOLS regex on PasswordRule

**1.1.1**

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

## Testing
I have added unit testing for Rules and Conditions, soon will provide test code on Validation and Validator, please check it out under [Test code](https://github.com/anderscheow/Validator/tree/master/library/src/test/java/io/github/anderscheow/validator)

## Contributions
Any contribution is more than welcome! You can contribute through pull requests and issues on GitHub.

## License
Validator is released under the [MIT License](https://github.com/anderscheow/Validator/blob/master/LICENSE)