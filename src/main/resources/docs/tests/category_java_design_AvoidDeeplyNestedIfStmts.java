//#Patterns: category_java_design_AvoidDeeplyNestedIfStmts


public class Foo {
  public void bar(int x, int y, int z) {
    if (x>y) {
      if (y>z) {
      //#Warn: category_java_design_AvoidDeeplyNestedIfStmts
        if (z==x) {
         // !! too deep
        }
      }
    }
  }
}
