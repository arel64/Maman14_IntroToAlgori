public class Heap extends ComparisonCount{

    public final static String UNDERFLOW = "Heap UnderFlow";

    protected int [] _arr;
    protected int heapSize;

    public Heap(int n){
        super();
        _arr = new int[n+1];// init to 0
        heapSize = n;

    }
    public Heap(int [] h){
        super();
        _arr = new int[h.length];// init to 0
        heapSize = h.length - 1;
        _arr = h.clone();
        this.buildMinHeap();
    }

    public int parent(int i){
        return i/2;
    }

    public int right(int i){
        return (i*2 + 1);
    }

    public int left(int i){
        return i*2;
    }

    public void minHeapify(int i){
        if(!(i<= heapSize)){
            return;
        }

        int left = left(i);
        int right= right(i);
        int smallest=i;

        if (left<=heapSize){
            count++;
            if (_arr[left]< _arr[i])
                smallest=left; // One comparison
        }
        if (right<=heapSize) {
            count++;
            if (_arr[right] < _arr[smallest])
                smallest = right;// One comparison
        }
        if(smallest!=i){
            //Exchange
            int temp = _arr[i];
            _arr[i] = _arr[smallest];
            _arr[smallest] = temp;

            minHeapify(smallest);
        }
    }

    public void buildMinHeap(){
        for(int i = ((heapSize)/2); i>=1  ; i--){
            minHeapify(i);
        }
    }
    public int heapExtractMin(){
        if (heapSize == 0){
            return Integer.MAX_VALUE;
        }
        int min = _arr[1];
        _arr[1] = _arr[heapSize];
        heapSize--;
        minHeapify(1);
        return min;
    }
    public int[] heapExtractMin(int k) throws Exception{

        if(k>heapSize) throw new Exception(UNDERFLOW);

        int[] nums= new int[k];
        for(int i=0 ; i<k ; i++){
            nums[i] = heapExtractMin();
        }
        return nums;
    }
    public String toString() {

        StringBuilder sbArray= new StringBuilder();
        for(int i = 1; i <=heapSize ; i++){
            sbArray.append(_arr[i]).append(",");
        }
        sbArray.append(" \n");

        StringBuilder sb= new StringBuilder();
        for (int i = 1; i <= heapSize / 2; i++) {

            // Printing the parent and both childrens
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
