import java.util.*;

/**
 * Main
 * @author  Arel Sharon
 * @since  11/05/2022
 */
public class Execute {
    static Scanner in = new Scanner(System.in);
    /*
        Hey! If you would like to test the code for yourself here is a useful template

        long[][] arr = Test.CompareExtractsTimes(<n>, <k>, <amount of times>);
        long[] res = Test.averageTesting(arr);
        System.out.println("Heap: " + res[0] + "\n" + "Select: " + res[1]);

        This will
        1) Create a n size array of random numbers in range specified in test.java
        2) Use both algorithms to extract k elements from this array
        3) Save the amount of comparisons that took each algorithm in a long[heapCount,selectCount] array
        4) Do this <amount of times> more times and store all the results into arr
        5) Take all the results from arr and average them by algorithm to give a more accurate _average_
            result
        6)Print it!
        Note: If you would like exactly 1 test to be made of course just set <amount of times> to be 1


     */
    public static void main(String[] args) throws Exception {

        int n,k;
        boolean state;
        n = Test.receiveInteger("Please submit array size (n): ");
        k = Test.receiveInteger("Please submit number of cells to extract (k): ");
        if(k>n){
            System.out.println("k is greater than n, all cells will be extracted");
            k=n;
        }
        System.out.println("Would you like to select values yourself or randomize them? \n" +
                            "Myself: Press 0\n"+
                            "Randomize: Any other key"
        );
        state = in.nextLine().equals("0");
        if(state){
            //User generated
            int[] array= new int[n];
            Test.CompareExtract(array,k);
        }else{
            //Computer Generated
            Test.CompareExtract(n,k,true);
        }
    }
}


