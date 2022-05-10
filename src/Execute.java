import java.awt.geom.QuadCurve2D;
import java.sql.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Execute {
    static Random rand = new Random();
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n,k;
        boolean state;
        n = recieveInteger("Please submit array size (n): ");
        k = recieveInteger("Please submit number of cells to extract (k): ");
        if(k>n){
            System.out.println("k is greater than n, all cells will be extracted");
            k=n;
        }
        System.out.println("Would you like to select values yourself or randomize them? \n" +
                            "Myself: Press 0\n"+
                            "Randomize: Any other key"
        );
        state = in.nextLine().equals("0");
        int[] heapArray,heapEx;
        int[] selectArray,selectEx;
        if(state){
            //User generated
            int count = 0;
            int temp;
            selectArray = new int[n];
            heapArray   = new int[n+1];
            heapArray[0] = Integer.MIN_VALUE;

            while(count<n){
                temp = recieveInteger("Please Select Next Integer " +"("+(count+1)+"\\"+(n)+")");
                selectArray[count] = temp;
                heapArray[count+1] =temp;
                count++;
            }
        }else{
            //Computer Generated
            System.out.println("Creating DataSet...");
            selectArray = rand.ints(n,0,1000).toArray();

            heapArray   = new int[n+1];
            heapArray[0] = Integer.MIN_VALUE;
            for(int i=0; i<selectArray.length;i++){heapArray[i+1] = selectArray[i];}

        }
        System.out.println("Here is the n size array on we will run the test");

        System.out.print("[");
        for (int i:selectArray){
            System.out.print(i+",");
        }
        System.out.println("]");

        Heap heap = new Heap(heapArray);
        heapEx = heap.heapExtractMin(k);

        SelectExtract sx = new SelectExtract();
        selectEx = sx.extractMin(selectArray,k);



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
    public static int recieveInteger(String msg){
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
}

