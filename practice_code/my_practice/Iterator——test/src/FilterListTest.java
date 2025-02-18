import java.util.ArrayList;
import java.util.List;

public class FilterListTest {
    public static void main(String[] args) {
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i<1000;i++){
            keys.addLast(i);
        }
        Predicate flag = new primeNumFilter();

        FilterList<Integer> filterList = new FilterList<>(keys,flag);
    for(int i:filterList){
        System.out.print(i + "  ");
    }
}
}
