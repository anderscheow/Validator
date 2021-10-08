## Changelog

**2.2.1**

* Added `Validation.notNull`, `Validation.notEmpty`, `Validation.notBlank`, `Validation.regex`, `Validation.past` and `Validation.future`
* Can use `TextInputLayout.validate` or `TextInputLayout.add` instead of `Validation(TextInputLayout)` in Kotlin

**2.2.0**

* Added `AllUpperCaseRule`, `AllLowerCaseRule`, `StartsWithRule`, `EndsWithRule` and `SymbolRule`
* Changed `validate(Any?)` to `validate(String?)`

**2.1.2**

* Added `NotEmptyRule`
* Added error messages into `Validator.OnValidateListener.onValidateFailed()`

**2.1.0**

* Updated Gradle and Kotlin version
* Changed Android Support artifacts to AndroidX
* Removed some install dependencies from README

**2.1.0**

* Updated Gradle and Kotlin version
* Changed Android Support artifacts to AndroidX
* Removed some install dependencies from README

**2.0.1**

* Updated to support Android SDK 28
* Converted Android Support to AndroidX

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