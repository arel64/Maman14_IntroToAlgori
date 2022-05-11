/**
 * Basic Counting class, will be overridden by Extraction algorithms as necessary
 * @author  Arel Sharon
 * @since  11/05/2022
 */
public class ComparisonCount implements Countable{
    /**
     * reset Count
     */
    public void resetCount() {
        count = 0;
    }

    /**
     * get comparisons counted
     * @return comparisons counted
     */
    public long getCount() {
        return count;
    }
    protected long count;
}
