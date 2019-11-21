Since: PMD 6.6.0

Configurable naming conventions for local variable declarations and other locally-scoped
            variables. This rule reports variable declarations which do not match the regex that applies to their
            specific kind (e.g. final variable, or catch-clause parameter). Each regex can be configured through
            properties.

            By default this rule uses the standard Java naming convention (Camel case).

Example(s):
```
class Foo {
                void bar() {
                    int localVariable = 1; // This is in camel case, so it's ok
                    int local_variable = 1; // This will be reported unless you change the regex

                    final int i_var = 1; // final local variables can be configured separately

                    try {
                        foo();
                    } catch (IllegalArgumentException e_illegal) {
                        // exception block parameters can be configured separately
                    }

                }
            }
```