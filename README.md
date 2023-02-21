Simple codetest
-----------------

Package codetest.services contains a service `FindService` to implement. This service works with the datastucture
defined in package `codetest.orgtree`.

The tasks are to implement and test (in increasing order of difficulty) -

* `FindService.findPersonByName()` - tree search, returning on first result.
* `FindService.findOrganisationsEngagingPerson()` - tree search, accumulating results
* `FindService.findPeopleWithMultipleEngagements()` - tree search, accumulating working set and results.

### Guidelines

Implementing the tasks without duplicating code is to be encouraged.

Methods may be added to the classes in the `codetest.orgtree` package if desired - but the general class hierarchy
should remain unchanged.

The signatures of the methods in the interfaces of `codetest.services` should remain unchanged.

The first task has an example test to get you started. The test cases should illustrate the desired behaviour for this
method. Add further test cases and refactor if required.

The implementation will be evaluated for correctness, clarity, simplicity and structure.

Please don't spend more than an hour or so on the test.

This project has a gradle build. To run the tests - 

``` ./gradlew clean test```

or on windows

``` gradlew.bat clean test```
