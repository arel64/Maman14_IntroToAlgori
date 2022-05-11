import java.util.Random;

/**
 * An Implementation of Q-S Algorithm
 * @author  Arel Sharon
 * @since  11/05/2022
 */
public class QuickSort extends ComparisonCount{
    static Random rand = new Random();

    /**
     * Init the counter.
     */
    public QuickSort(){
        super();
    }

    /**
     * Partition the array into 3 parts using r as pivot
     * @param arr array to partition
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @apiNote see page 122
     * @return partitioned array
     */
    private int _partition(int arr[],int p,int r){
        /*
            This function is documented in the book
         */
        int x = arr[r];
        int i = p-1;
        for(int j=p; j<r ; j++){
            count++;
            if(arr[j]<=x){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,r);
        return i+1;
    }
    /**
     * Partition the array into 3 parts using r as pivot
     * @param arr array to partition
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @apiNote see page 122
     * @return partitioned array
     */
    public int partition(int arr[],int p,int r) {
        return _partition(arr,p,r);
    }
    /**
     * Partition the array into 3 parts using random number in range as pivot.
     * @param arr array to partition
     * @param p start of range inclusive
     * @param r end of range inclusive
     * @apiNote see page 122
     * @return partitioned array
     */
    public int randPartition(int arr[],int p, int r){
        int pivot = rand.nextInt(r-p)+p;
        swap(arr,pivot,r);
        return _partition(arr,p,r);
    }
    /**
     * Quicksort the array
     * @param arr array to quicksort
     * @param p start of range inclusive
     * @param r end of range inclusive
     */
    public void quickSort(int arr[],int p,int r){
        int q;
        if(p<r){
            q = partition(arr,p,r);
            quickSort(arr,p,q-1);
            quickSort(arr,q+1,r);
        }
    }

    /**
     * swap 2 cells in array
     * @param arr array to swap in
     * @param i cell a
     * @param j cell b
     */
    private void swap(int arr[],int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]= temp;
    }
}
