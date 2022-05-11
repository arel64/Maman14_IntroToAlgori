/**
 * This class implements Minimum Heap
 * @author  Arel Sharon
 * @since  11/05/2022
 */
public class Heap extends ComparisonCount{

    public final static String UNDERFLOW = "Heap UnderFlow";

    /*
        Heap size and underlying array
     */
    protected int [] _arr;
    protected int heapSize;

    /**
     * Create Empty Heap of all zeros
     * @param n Max and current size of Heap
     */
    public Heap(int n){
        super();
        _arr = new int[n+1];// init to 0
        heapSize = n;

    }

    /**
     * Create a minimum heap from an array
     * @param h array to create heap from
     */
    public Heap(int [] h){
        super();// Init Comparison Count Parent class

        _arr = new int[h.length];// init to 0
        heapSize = h.length - 1;
        _arr = h.clone();


        /*
            Transform our array to a minHeap.
        */
        this.buildMinHeap();
    }

    /**
     * Find Parent of item in heap
     * @param i index in array
     * @return Parent index
     */
    public int parent(int i){
        return i/2;
    }

    /**
     * Find element to the right of item in heap
     * @param i index in array
     * @return Right index
     */
    public int right(int i){
        return (i*2 + 1);
    }

    /**
     * Find element to the left of item in heap
     * @param i index in array
     * @return Left index
     */
    public int left(int i){
        return i*2;
    }

    /**
     * The analogous algorithm to max-heapify
     * @apiNote See page 109
     * @param i index to slide
     */
    public void minHeapify(int i){
        //Index is out of bounds, terminate
        if(i > heapSize){
            return;
        }

        /*
            Find children(If exists) of Node
         */
        int left = left(i);
        int right= right(i);
        int smallest=i;

        /*
            Next 2 if's check if the children of the node are smaller than the parent
            if so, saved the index into "smallest" variable.
         */
        if (left<=heapSize){
            count++;
            if (_arr[left]< _arr[i])
                smallest=left;
        }
        if (right<=heapSize) {
            count++;
            if (_arr[right] < _arr[smallest])
                smallest = right;
        }

        /*
            If a child was found to be smaller than its parents exchange them.
         */
        if(smallest!=i){
            //Exchange
            int temp = _arr[i];
            _arr[i] = _arr[smallest];
            _arr[smallest] = temp;

            /*
                Call function on the new index of what was arr[i] before the swap,
                as it might be bigger than the children
             */

            minHeapify(smallest);
        }
    }

    /**
     * The analogous algorithm to buildMaxHeap
     * @apiNote See page 111
     */
    public void buildMinHeap(){
        for(int i = ((heapSize)/2); i>=1  ; i--){
            minHeapify(i);
        }
    }

    /**
     * The analogous algorithm to heapExtractMax
     * @apiNote  see page 116
     * @return minimum key in heap
     */
    public int heapExtractMin() throws Exception {
        /*
            If heap is empty, return Exception
         */
        if (heapSize <= 0){
            throw new Exception(UNDERFLOW);
        }

        /*
            First item is always the smallest in min heap
            Remove it, decrease the size of the heap
            Re-Min Heapify it
         */
        int min = _arr[1];
        _arr[1] = _arr[heapSize];
        heapSize--;
        minHeapify(1);


        return min;
    }

    /**
     * Extract k smallest elements in heap.
     * @param k how many keys to extract
     * @return k smallest keys, sorted ASC order
     * @throws Exception Underflow if try to extract more elements then are present
     */
    public int[] heapExtractMin(int k) throws Exception{
         /*
            If heap is too small, throw Exception
         */
        if(k>heapSize) throw new Exception(UNDERFLOW);

        /*
            Class the single element function k times
         */
        int[] nums= new int[k];

        for(int i=0 ; i<k ; i++){
            nums[i] = heapExtractMin();
        }

        /*
            Return the array
         */
        return nums;
    }

    /**
     * Stringify the Heap
     * @return String representing the heap
     */
    public String toString() {
        /*
            [a,b,c,d..,z] representation
         */
        StringBuilder sbArray= new StringBuilder();
        for(int i = 1; i <=heapSize ; i++){
            sbArray.append(_arr[i]).append(",");
        }
        sbArray.append(" \n");


        /*
            same heap in tier representation
         */
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i <= heapSize / 2; i++) {

            // Printing the parent and both children
            sb.append("Parent: ").append(_arr[i]);
            if(left(i)<=heapSize){
                sb.append(" Left Child: ").append(_arr[left(i)]);
            }
            if(right(i)<=heapSize){
                sb.append(" Right Child: ").append(_arr[right(i)]);
            }
            sb.append("\n");
        }
        return sbArray.append(sb).toString();
    }



}
