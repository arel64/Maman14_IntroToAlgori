/**
 * Basic Counting class, will be overridden by Extraction algorithms as necessary
 */
public class ComparisonCount implements Countable{
    public void resetCount() {
        count = 0;
    }

    public long getCount() {
        return count;
    }
    protected long count;
}
