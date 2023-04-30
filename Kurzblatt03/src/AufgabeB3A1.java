import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB3A1 {
    private int subsetSize;

    public static int[] readStandardIn(){
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while(in.hasNextLine()){
            int current = Integer.parseInt(in.nextLine());
            list.add(current);
        }
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
    public AufgabeB3A1(int subsetSize){
        this.subsetSize = subsetSize;
    }
    //TODO: createSubsets(int[] data)
    public int[][] createSubsets(int[] data){
        if(subsetSize > data.length){
            return null;
        }
        if(subsetSize == 0){
            return new int[1][0];
        }
        int[][] out = new int[binom(data.length, subsetSize)][];
        int out_pointer = 0;
        for(int i = 0; i < data.length; i++){
            int[] rest = new int[data.length - 1];
            for(int j = 0; j < rest.length; j++){
                if(i != j){
                    rest[j] = data[j];
                }
            }
            //We have the Array rest with all elements except a_i.

            //Get all subsets of rest with subsetSize-1.
            subsetSize--;
            int[][] _new = createSubsets(rest);
            subsetSize++;
            for (int j = 0; j < _new.length; j++){
                //Add data[i] to every one
                int[] finish = new int[_new[j].length + 1];
                for(int x = 0; x < _new[j].length; x++){
                    finish[x] = _new[j][x];
                }
                finish[finish.length - 1] = data[i];
                //Add every array to out
                out[out_pointer] = finish;
                out_pointer++;
            }
        }
        return out;
    }
    public int[] removeDuplicates(int[] data){
        int[] temp = data.clone();
        Arrays.sort(temp);
        int last = temp[0];
        int duplicateCounter = 0;
        for(int i = 1; i < temp.length; i++){
            if(temp[i] == last){//Case: We encounter a duplicate
                temp[i] = Integer.MIN_VALUE; //Set Duplicates to a certain
                duplicateCounter++;
            }else{
                last = temp[i];
            }
        }
        Arrays.sort(temp); //Sorts array: All duplicates are now at lowest indices
        int[] out = new int[data.length - duplicateCounter];
        for(int i = 0; i < out.length; i++){
            out[i] = temp[i + duplicateCounter];
        }
        return out;
    }
    public static void main(String[] args){
        //TODO: main
        int[] array = readStandardIn();
        AufgabeB3A1 task1 = new AufgabeB3A1(Integer.parseInt(args[2]));
        int[][] subsets = task1.createSubsets(task1.removeDuplicates(array));
        System.out.println("All Subsets of the Array: " + Arrays.toString(array));
        for(int i = 0; i < subsets.length; i++){
            System.out.println(Arrays.toString(subsets[i]));
        }
    }
    private int binom(int n, int k){
        if(k > n || k < 0){
            return 0;
        }
        if(k == 0 || k == n){
            return 1;
        }
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
