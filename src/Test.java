
import java.util.*;

/**
 * This class will handle the bulk of Execution of Tests and Comparisons
 */
public class Test {
    static Random rand = new Random();
    static Scanner in = new Scanner(System.in);
    static final int END_RAND_RANGE_EXC = 1000;
    static final int START_RAND_RANGE_INC = 0;

    /**
     * This function returns the amount of comparisons made by both algorithms of extracting
     * k smallest elements from a number of different random arrays of size n.
     * @param n size of random array
     * @param k amount of smallest elements to extract
     * @param times amount of random arrays to test
     * @return [times][2] array with the results of each iteration [heapExtract,selectExtract]
     * @throws Exception In case of logical or runtime error
     */
    public static long[][] CompareExtractsTimes(int n,int k,int times)throws Exception{
        long[][] counts = new long[times][2];
        for(int i=0;i<times;i++){
            counts[i]=CompareExtract(n,k,false);
        }
        return counts;
    }

    /**
     * This function returns the amount of comparisons made by both algorithms of extracting
     * k smallest elements from a random array n.
     * @param n size of random array
     * @param k amount of smallest elements to extract
     * @param verbose print results
     * @return 2 cell array with both counts  [heapExtract,selectExtract]
     * @throws Exception thrown when k>n or the algorithm detected an error.
     */
    public static long[] CompareExtract(int n,int k, boolean verbose) throws Exception{
        /*
            Create and initialize both arrays
            heap is 1 cell larger as heap[0] is not used under current implementation
         */
        int[] heapArray   = new int[n+1];
        int[] selectArray = new int[n];

        /*
            Fill both array with the same random numbers(offset by 1 for heap).
         */
        prepArrays(heapArray,selectArray);
        /*
            Return the count of comparison for identical arrays using either method.
         */
        return _testArrays(heapArray,selectArray,k,verbose);

    }

    /**
     * This function returns the amount of comparisons made by both algorithms of extracting
     * k smallest elements from a given array.
     * @param arr array to test
     * @param k amount of smallest elements to extract
     * @return 2 cell array with both counts  [heapExtract,selectExtract]
     * @throws Exception thrown when k>n or the algorithm detected an error.
     * @apiNote This will be used when the user creates the array
     */
    public static long[] CompareExtract(int[] arr,int k) throws Exception {
        //User generated

        /*
        Initialize arrays
         */
        int count = 0;
        int temp;
        int[] heapArray   = new int[arr.length+1];
        int[] selectArray = new int[arr.length];

        //Anchor unused cell of heap array
        heapArray[0] = Integer.MIN_VALUE;

        /*
            Initialize both arrays with valid, whole numbers given by the user.
            count is only increased upon a valid input.
         */
        while(count<arr.length){
            //Recieve valid input from user
            temp = receiveInteger("Please Select Next Integer " +"("+(count+1)+"\\"+(arr.length)+")");

            //Add to both arrays, offset by 1 to heap array.
            selectArray[count] = temp;
            heapArray[count+1] =temp;

            count++;
        }
        /*
            Return the count of comparison for identical arrays using either method.
         */
        return _testArrays(heapArray,selectArray,k,true);

    }

    /**
     * This method fills heap and select array with random numbers, assuming both are of equal size
     * except for anchor cell.
     * @param heapArray array that will be used for heap
     * @param selectArray array that will be used for selectQuickSort
     */
    public static void prepArrays(int[] heapArray,int[] selectArray){
        //Fill array with random numbers in range.
        int[] temp = rand.ints(selectArray.length,START_RAND_RANGE_INC,END_RAND_RANGE_EXC).toArray();
        //Apply anchor to heap array
        heapArray[0] = Integer.MIN_VALUE;

        /*
            Initialize both arrays with values generated.
         */
        for(int i=0; i<selectArray.length;i++){
            heapArray[i+1] = temp[i];
            selectArray[i] = temp[i];
        }
    }

    /**
     * Gets a valid whole integer from user
     * @param msg message to prompt user for input
     * @return valid value given by user
     */
    public static int receiveInteger(String msg){

        String temp;
        boolean cast=true;// while this is false no valid value was given.
        int num=0;


        do{
            /*
            Prompt user and recieve a line
             */
            System.out.println(msg);
            temp = in.nextLine();


            /*
                If line is invalid, explain why and request another input
             */
            try{
                num = Integer.parseInt(temp);
                if(num<0){
                    throw new InputMismatchException();
                }
            }catch (NumberFormatException e){
                cast=false;
                System.out.println("Please submit a number");
            }
            catch (InputMismatchException e){
                cast=false;
                System.out.println("Please submit a whole number");
            }

        }while(!cast);
        //Valid input given, return
        return num;
    }

    /**
     * Applies algorithms to arrays and extracts using both the k smallest elements
     * @param heapArray array to check using heap
     * @param selectArray array to check using select
     * @param k number of smallest elements to extract
     * @param verbose print results
     * @return 2 cell array with both counts  [heapExtract,selectExtract]
     * @throws Exception Upon invalid k OR internal error(Runtime or algorithmically)
     * @apiNote This method has a verification that both methods return the same results.
     */
    private static long[]_testArrays(int[] heapArray,int[]selectArray,int k, boolean verbose)throws Exception{

        int[] selectEx,heapEx;

        /*
        Create heap and extract elements
         */
        Heap heap = new Heap(heapArray);
        heapEx = heap.heapExtractMin(k);
        /*
        Using the Select-QuickSort algorithm extract elements
         */
        SelectExtract sx = new SelectExtract();
        selectEx = sx.extractMin(selectArray,k);

        /*
            Both method should return:
            1)The same amount of items
            2)In the same order
            If either is untrue there is a logical problem, halt.
         */
        if(!isEquivalent(heapEx,selectEx))
            throw new Exception("Algorithm error detected, execution will halt");

        /*
            Print the results if required.
         */
        if(verbose){
            StringBuilder heapSb = new StringBuilder();
            StringBuilder selectSb = new StringBuilder();

            heapSb.append('[');
            selectSb.append('[');

            for (int i = 0; i< selectEx.length;i++){
                heapSb.append(',').append(heapEx[i]);
                selectSb.append(',').append(selectEx[i]);
            }

            heapSb.append(']');
            selectSb.append(']');


            System.out.println("The "+k+" smallest nums in the array are \n"+
                    "Heap Ex:   "+ heapSb+"\n"+
                    "Select Ex: "+selectSb
            );

            System.out.println("Amount of array elements comparisons: ");
            System.out.println("Heap: "+ heap.getCount());
            System.out.println("Select: "+ sx.getCount());
        }
        //Return counts.
        return new long[]{heap.getCount(),sx.getCount()};
    }

    /**
     * check if array are value equivalent
     * @param arr1 array a
     * @param arr2 array b
     * @return true if equivalent by length and value
     */
    private static boolean isEquivalent(int [] arr1, int []arr2){
        if(arr1.length!=arr2.length) return false;
        for(int i =0; i<arr1.length;i++){
            if(arr1[i]!=arr2[i])
                return false;
        }
        return true;
    }



}
