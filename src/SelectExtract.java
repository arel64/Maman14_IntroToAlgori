import java.util.Arrays;

/**
 * Algorithm 2
 * Use select to split the elements to k smallest and quicksort them
 * @author  Arel Sharon
 * @since  11/05/2022
 */
public class SelectExtract extends ComparisonCount{

    public final static String UNDERFLOW = "SELECT UnderFlow";
    final private QuickSort qs;

    /**
     * Create Empty Obj, mainly for counting
     */
    public SelectExtract(){
        super();
        qs= new QuickSort();
    }

    /**
     * Extract the K smallest elements from arr
     * @param arr array to Exctract from
     * @param k amount of elements to extract
     * @return sorted array of k smallest elements in arr ASC
     * @throws Exception Underflow if try to extract more elements then are present
     * @implNote Using select and quicksort
     */
    public int[] extractMin(int []arr,int k) throws Exception{
        /*
            If array is too small, throw Exception
         */
        if(k>arr.length) throw new Exception(UNDERFLOW);

        /*
            Find the k'th smallest element in arr.
            select will also use partition to place smaller than k'th smallest element
            to the "left" of its index in the array
         */
        int index = randSelect(arr,0,arr.length-1,k);

        /*
            Sort all elements to the left of the k'th smallest array.
         */
        qs.quickSort(arr,0,index-1);

        //return a subarray of sorted smallest k elements from arr.
        return Arrays.stream(arr,0,k).toArray();

    }

    /**
     * Select the k'th smallest element
     * @param arr array to search
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @param i i'th smallest element to find
     * @apiNote See page 154
     * @return k'th smallest element in array
     */
    public int select(int [] arr,int p,int r, int i) {
        return _select(arr,p,r,i,false);
    }

    /**
     * Select the k'th smallest element with random pivot
     * @param arr array to search
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @param i i'th smallest element to find
     * @apiNote See page 154
     * @return k'th smallest element in array
     */
    public int randSelect(int [] arr,int p,int r, int i) {
        return _select(arr,p,r,i,true);
    }
    /**
     * Select the k'th smallest element with last cell as pivot
     * @param arr array to search
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @param i i'th smallest element to find
     * @apiNote See page 154
     * @return k'th smallest element in array
     */
    private int _select(int [] arr,int p,int r, int i,boolean rand){
        /*
            If the range if of 1 element return it.
         */
        if(p==r){
            return p;
        }


        int q;
        /*
            Partition the array
         */
        if(rand){
            q = qs.randPartition(arr,p,r);
        }else{
            q = qs.partition(arr,p,r);
        }


        int k = q - p + 1;
        //If first element in range is the desired element return it.
        if(i==k){
            return q;
        }
        //If element if too big, search for it in left side of array
        else if(i<k){
            return select(arr,p,q-1,i);
        }
        //If element if too small, search for it in right side of array
        return select(arr,q+1,r,i-k);
    }

    /**
     * Count Comparison made by the algorithm
     * @return amount of comparison made
     */
    public long getCount() {
        return super.getCount() + qs.getCount();
    }

    /**
     * Reset counter
     */
    public void resetCount(){
        count = 0;
        qs.resetCount();
    }
}
