import java.util.*;

public class Execute {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Test.CompareExtractsTimes(500,10,2000);

        /*
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
        }*/
    }

}

