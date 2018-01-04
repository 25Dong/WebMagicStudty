package test;

public class DebugTest {
    public  static void main(String[] args){
        int x = 1;
        change(x);
        int x1 = 1;
        change(x);
        int x2 = 1;
        change(x);
        int x3 = 1;
        change(x);
    }

    private static void change(int x) {
        x = 5;
        System.out.println(x);
    }
}
