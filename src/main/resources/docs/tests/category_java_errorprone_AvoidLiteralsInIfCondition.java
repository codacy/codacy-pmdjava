//#Patterns: category_java_errorprone_AvoidLiteralsInIfCondition

public class Foo {
    private static final int MAX_NUMBER = 10;

    public void bar() {
        int x = 0;

        //#Warn: category_java_errorprone_AvoidLiteralsInIfCondition
        if (x > 10) {  }

        if (x > MAX_NUMBER) {  } //preferred approach
    }
}
