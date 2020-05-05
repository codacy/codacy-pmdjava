//#Patterns: category_java_bestpractices_UnusedLocalVariable

public class Foo {
    public void doSomething() {
        //#Warn: category_java_bestpractices_UnusedLocalVariable
        int i = 5; // Unused

        int foo = 5;

        System.out.println(foo);
    }
}

