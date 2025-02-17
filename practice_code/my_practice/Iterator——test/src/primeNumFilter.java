
public class primeNumFilter<T> implements Predicate<T>{

    public boolean LastNine(T x) {
        return (int)x % 10 == 9;
    }
    @Override
    public boolean test(T x){
        for(int i = 2;i * i <= (Integer)x;i++) {
            if (((Integer)x % i) == 0){
                return false;
            }
        }
        if(!LastNine(x)){
            return false;
        }
        return true;
    }
}
