import java.util.Arrays;
import java.util.stream.IntStream;

public class SelectExtract extends ComparisonCount{
    public final static String UNDERFLOW = "SELECT UnderFlow";
    final private QuickSort qs;
    public SelectExtract(){
        super();
        qs= new QuickSort();
    }
    public int[] extractMin(int []arr,int k) throws Exception{
        if(k>arr.length) throw new Exception(UNDERFLOW);
        int index = select(arr,0,arr.length-1,k);
      //  qs.partition(arr,0,arr.length-1,index);
        qs.quickSort(arr,0,index);
        return Arrays.stream(arr,0,k).toArray();

    }




    public int select(int [] arr,int p,int r, int i) {
        return _select(arr,p,r,i,false);
    }
    public int randSelect(int [] arr,int p,int r, int i) {
        return _select(arr,p,r,i,true);
    }

    private int _select(int [] arr,int p,int r, int i,boolean isRand){
        if(p==r){
            return p;
        }
        int q;
        if(isRand){
            q = qs.randPartition(arr,p,r);
        }else{
            q = qs.partition(arr,p,r);
        }
        int k = q - p + 1;
        if(i==k){
            return q;
        }
        else if(i<k){
            return _select(arr,p,q-1,i,isRand);
        }
        return _select(arr,q+1,r,i-k,isRand);
    }
    public long getCount() {
        return super.getCount() + qs.getCount();
    }
    public void resetCount(){
        count = 0;
        qs.resetCount();
    }
}
