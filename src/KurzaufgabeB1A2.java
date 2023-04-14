import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KurzaufgabeB1A2 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[] arr = readArray(input);
        System.out.println(printPermutations(arr, 0));
    }

    /**
     * From 1b): Puts the Input-Lines into an Integer-Array
     * @param in A scanner with the input to read
     * @return The array, filled with all numbers in the Input-Stream
     */
    private static int[] readArray(Scanner in){
        ArrayList<Integer> list = new ArrayList<>();
        while(in.hasNextLine()){
            try {
                int current = Integer.parseInt(in.nextLine());
                list.add(current);
            }catch(Exception e){
                System.err.println("Input read was NaN");
            }
        }
        //Händisch: ArrayList<Integer> -> int[]
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * Prints all possible Permutations of the array (1 Permutation per line)
     * @param array The array to permute
     * @param d The number of already set / determined values
     * @return The number of permutations computed
     */
    public static int printPermutations(int[] array, int d){
        if(d >= array.length){
            //If all values are selected, print the array and return 1 as "only possible permutation"
            System.out.println(Arrays.toString(array));
            return 1;
        }
        int out = 0;
        int current_d = array[d];
        for(int i = d; i < array.length; i++){
            //Swap first not-selected position with (every) following one
            array[d] = array[i];
            array[i] = current_d;
            //Take this new selected value at a[d] and print all permutations of it
            out += printPermutations(array, d + 1);
            //Revert to original array
            array[i] = array[d];
            array[d] = current_d;
        }
        return out;
    }
}
