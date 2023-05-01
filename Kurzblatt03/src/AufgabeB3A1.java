import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB3A1 {
    public int subsetSize;

    public static void main(String[] args) {
        try {
            int[] array = readStandardIn();
            int k = Integer.parseInt(args[0]);
            AufgabeB3A1 task1 = new AufgabeB3A1(k);
            int[][] subsets = task1.createSubsets(task1.removeDuplicates(array));
            for (int i = 0; i < subsets.length; i++) {
                System.out.println(Arrays.toString(subsets[i]));
            }
            System.out.println("There are " + subsets.length + "subsets.");

        }catch(Exception e){

        }
    }
    public AufgabeB3A1(int subsetSize){
        this.subsetSize = subsetSize;
    }
    public static int[] readStandardIn() throws NumberFormatException{
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        try {
            while (in.hasNextLine()) {
                int current = Integer.parseInt(in.nextLine());
                list.add(current);
            }
        }catch(Exception e){
            throw e;
        }
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int[][] createSubsets(int[] data){
        //TODO: createSubsets(int[] data) --Done--
        if(subsetSize > data.length || subsetSize < 0){
            return new int[0][0]; //Subset of length bigger than set does not exist
        }
        if(subsetSize == 0){
            return new int[1][0]; //Subset of length 0: Empty set
        }
        if(subsetSize == data.length){ //Subset of length of n: Only set itself
            int[][] out = new int[1][];
            out[0] = data;
            return out;
        }
        int[] rest = Arrays.copyOfRange(data, 1, data.length); //Rest has n - 1 elements (not 1st element)
        subsetSize--;
        int[][] subsets1 = createSubsets(rest); //Get all subsets with (n - 1 choose k - 1)
        //Add kth element to all subsets with k - 1 elements (add data[0] to every subset)
        for(int i = 0; i < subsets1.length; i++){
            int[] _new = new int[subsets1[i].length + 1];
            for(int j = 0; j < subsets1[i].length; j++){
                _new[j] = subsets1[i][j];
            }
            _new[_new.length - 1] = data[0];
            subsets1[i] = _new;
        }
        subsetSize++;
        int[][] subsets2 = createSubsets(rest); //Get all subsets with (n - 1 choose k)
        int[][] out = merge(subsets1, subsets2);
        return out;
    }
    public int[] removeDuplicates(int[] data){
        int[] temp = data.clone();
        if(data.length <= 1){
            return temp;
        }
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
    /**
     * [For test purposes]: Generates an array with values between 0 and 100
     * @param size The size of the generated array
     * @return A randomly generated array
     */
    public static int[] randomArray(int size){
        int[] out = new int[size];
        for(int i = 0; i < size; i++){
            out[i] = (int)(Math.random() * 100);
        }
        return out;
    }

    /**
     * Generate a new 2D-Array containing all arrays a1[i] and a2[i]
     * @param a1 First set of arrays
     * @param a2 Second set of arrays
     * @return New 2D-Array containing all arrays of the input
     */
    private int[][] merge(int[][] a1, int[][] a2){
        int[][] out = new int[a1.length + a2.length][];
        for(int i = 0; i < a1.length; i++){
            out[i] = a1[i];
        }
        for(int i = 0; i < a2.length; i++){
            out[a1.length + i] = a2[i];
        }
        return out;
    }
}
