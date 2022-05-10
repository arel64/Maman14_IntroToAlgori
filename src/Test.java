
import java.util.*;

public class Test {
    static Random rand = new Random();
    static Scanner in = new Scanner(System.in);

    public static long[] CompareExtract(int n,int k, boolean verbose) throws Exception{

        int[] heapArray   = new int[n+1];
        int[] selectArray = new int[n];
        prepArrays(heapArray,selectArray);

        return _testArrays(heapArray,selectArray,k,verbose);

    }
    public static long[] CompareExtract(int[] arr,int k) throws Exception {
        //User generated
        int count = 0;
        int temp;
        int[] heapArray   = new int[arr.length+1];
        int[] selectArray = new int[arr.length];


        heapArray[0] = Integer.MIN_VALUE;

        while(count<arr.length){
            temp = receiveInteger("Please Select Next Integer " +"("+(count+1)+"\\"+(arr.length)+")");
            selectArray[count] = temp;
            heapArray[count+1] =temp;
            count++;
        }
        return _testArrays(heapArray,selectArray,k,true);

    }
    public static void prepArrays(int[] heapArray,int[] selectArray){
        int[] temp = rand.ints(selectArray.length,0,1000).toArray();

        heapArray[0] = Integer.MIN_VALUE;
        for(int i=0; i<selectArray.length;i++){
            heapArray[i+1] = temp[i];
            selectArray[i] = temp[i];
        }
    }
    public static int receiveInteger(String msg){
        String temp;
        boolean cast=true;
        int num=0;
        do{
            System.out.println(msg);
            temp = in.nextLine();
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
                System.out.println("Please submit a whole, positive number");
            }
        }while(!cast);
        return num;
    }
    private static long[]_testArrays(int[] heapArray,int[]selectArray,int k, boolean verbose)throws Exception{

        int[] selectEx,heapEx;
        Heap heap = new Heap(heapArray);
        heapEx = heap.heapExtractMin(k);

        SelectExtract sx = new SelectExtract();
        selectEx = sx.extractMin(selectArray,k);
        if(!isEquivalent(heapEx,selectEx))
            throw new Exception("Algorithm error detected, execution will halt");

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
        return new long[]{heap.getCount(),sx.getCount()};
    }
    private static boolean isEquivalent(int [] arr1, int []arr2){
        if(arr1.length!=arr2.length) return false;
        for(int i =0; i<arr1.length;i++){
            if(arr1[i]!=arr2[i])
                return false;
        }
        return true;
    }



}
