Since: PMD 6.0.0

Empty Catch Block finds instances where an exception is caught, but nothing is done.
In most circumstances, this swallows an exception which should either be acted on
or reported.

Example(s):
```
public void doSomething() {
    ...
    try {
        insert accounts;
    } catch (DmlException dmle) {
        // not good
    }
}
```
