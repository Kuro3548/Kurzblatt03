import java.util.Arrays;

public class AufgabeB3A2 {
    public int[] data;

    public static void main(String[] args){
        try {

            int[] input = AufgabeB3A1.readStandardIn();
            int[] k = new int[args.length];
            for(int i = 0; i < args.length; i++){
                k[i] = Integer.parseInt(args[i]);
            }


            //Input: Unsorted input with duplicates, k: Array of all k values
            //Need to do: Sort, Remove Duplicates, Register at task2, Print
            //int[] input = generateArray(12);
            //int[] k = new int[]{1, 4, 17, 42, 432101234};

            AufgabeB3A1 task1 = new AufgabeB3A1(-1);
            int[] sorted = task1.removeDuplicates(input);
            System.out.println("Sorted input: ");
            System.out.println(Arrays.toString(sorted));
            AufgabeB3A2 task2 = new AufgabeB3A2(sorted);
            for(int i = 0; i < k.length; i++){
                int[] permutation = task2.choosePermutation(k[i]);
                System.out.println("The " + k[i] + "-smallest permutation is:");
                System.out.println(Arrays.toString(permutation));
            }
        }catch(Exception e){

        }
    }

    public AufgabeB3A2(int[] data){
        this.data = data;
    }
    private static int[] generateArray(int size){
        int[] out = new int[size];
        for(int i = 0; i < out.length; i++){
            out[i] = i + 1;
        }
        return out;
    }
    public int[] choosePermutation(int kSmallest){
        //System.out.println("find(" + Arrays.toString(data) + ", k = " + kSmallest + ")");
        if(kSmallest > fac(data.length) || kSmallest < 1 || data.length == 0){
            System.out.println("Error: Invalid arguments!");
            return null;
        }
        //Case: find([a_0], 1) => [a_0]
        if(kSmallest == 1){
            return data.clone();
        }
        //Case: find(a_0,...,a_{n - 1}, k) => a_i o find(a_0, ..., a_{i - 1}, a_{i + 1}, ..., a_{n - 1}, k')
        int n = data.length;
        int i = (int)((kSmallest - 1) / (fac(n - 1)));
        int k_new = kSmallest - i * (int) fac(data.length - 1);
        int first = data[i]; //Produces out-of-bounds??(i == -1 or i == 2, n = 0): Case k ==0
        //Create temporary data-array without ith element
        int[] rest;
        {
            int[] backup = data.clone();
            this.data = cut(data, i);
            rest = choosePermutation(k_new);
            this.data = backup;
        } //New scope: Removes backup and reduced array from memory
        //System.out.println("find(" + Arrays.toString(data) + ", k = " + kSmallest + "): Subarray got: " + Arrays.toString(rest));
        //Create output: [first, <find(reduced, k')>]
        int[] out = new int[n];
        out[0] = first;
        for(int i3 = 1; i3 < out.length; i3++){
            out[i3] = rest[i3 - 1];
        }
        //System.out.println("Output: " + Arrays.toString(out));
        return out;
    }
    private long fac(int x){
        if(x == 0){
            return 1;
        }
        return x * fac(x - 1);
    }
    private int[] cut(int[] arr, int x){
        int[] out = new int[arr.length - 1];
        for(int i = 0; i < out.length; i++){
            if(i >= x){
                out[i] = arr[i + 1];
            }else{
                out[i] = arr[i];
            }
        }
        return out;
    }

}
