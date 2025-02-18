import java.util.*;
public class IteratorOfIterators implements Iterator<Integer> {
    List<Iterator<Integer>> list;
    Iterator p1;
    int cnt;
    public IteratorOfIterators(List<Iterator<Integer>> a){
        list = a;
        p1 = list.getFirst();
        cnt = 0;
    }
    @Override
    public boolean hasNext() {
        while(cnt+1 < list.size()&&!p1.hasNext()){
            cnt += 1;
            p1 = list.get(cnt);
            if(cnt+1 >=list.size()&&!p1.hasNext()){
                return false;
            }
        }
        if(cnt+1>=list.size()) {//列表到末尾
            if(!p1.hasNext()) {
                return false;
            }
        }
        return true;
    }
//广度优先搜索
    @Override
    public Integer next() {
        Integer answer;
        if (cnt+1 == list.size()){
            if (p1.hasNext()){
                answer = (Integer)p1.next();
                cnt = 0;
                p1 = list.get(cnt);
                return answer;
            }else {
                throw new NoSuchElementException();
            }
        }else {
            while(!p1.hasNext())
            {
                cnt +=1;
                p1 = list.get(cnt);
                if(cnt+1 == list.size() && !p1.hasNext()){
                    throw new NoSuchElementException();
                }
            }

            if(cnt+1 < list.size()){
                answer = (Integer)p1.next();
                cnt += 1;
                p1 = list.get(cnt);
                return answer;
            } else {
                answer = (Integer)p1.next();
                cnt = 0;
                p1 = list.get(cnt);
                return answer;
            }
        }
    }
}
