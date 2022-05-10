public class HeapCounter extends Heap{
    private int compCount;
    public HeapCounter(int n) {
        super(n);
        compCount = 0;
    }
    public HeapCounter(int []n) {
        super(n);
        compCount = 0;

    }
    public void minHeapify(int i){
        if(this.left(i)<_arr.length){
            compCount++;
        }
        if(this.right(i)<_arr.length){
            compCount++;
        }
        super.minHeapify(i);
    }
    public int getCompCount() {
        return compCount;
    }
    public void resetCompCount() {
        compCount = 0;
    }
}
