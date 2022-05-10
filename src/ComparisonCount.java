public class ComparisonCount implements Countable{
    public ComparisonCount(){
        count=0;
    }
    public void resetCount() {
        count = 0;
    }

    public long getCount() {
        return count;
    }
    protected long count;
}
