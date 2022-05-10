import java.util.Random;

public class QuickSort extends ComparisonCount{

    static Random rand = new Random();
    public QuickSort(){
        super();
    }
    public int partition(int arr[],int p,int r){
        int x = arr[r];
        int i = p-1;
        for(int j=p; j<r ; j++){
            count++;
            if(arr[j]<=x){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,r);
        return i+1;
    }
    public int partition(int arr[],int p,int r,int pivot) {
        swap(arr,pivot,r);
        return partition(arr,p,r);
    }

    public int randPartition(int arr[],int p, int r){
        int pivot = rand.nextInt(r-p)+p;
        swap(arr,pivot,r);
        return partition(arr,p,r);
    }

    public void randQuickSort(int arr[],int p, int r){
        _quickSort(arr,p,r,true);
    }

    public void quickSort(int arr[],int p,int r){
        _quickSort(arr,p,r,false);
    }

    private void _quickSort(int arr[],int p,int r,boolean rand){
        int q;
        if(p<r){
            if(rand){
                q = randPartition(arr,p,r);
            }else{
                q = partition(arr,p,r);
            }
            quickSort(arr,p,q-1);
            quickSort(arr,q+1,r);
        }
    }


    private void swap(int arr[],int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]= temp;
    }
}
