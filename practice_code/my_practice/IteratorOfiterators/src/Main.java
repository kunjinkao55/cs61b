import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        test t = new test();
        int cnt = 0;
        for(Object i : t){
            cnt+=1;
            System.out.print(i + "  ");
        }
        System.out.println("");
        System.out.print(cnt);
    }
}