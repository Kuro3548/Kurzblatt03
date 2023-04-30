import java.util.Arrays;

public class AufgabeB3A2 {
    private int[] data;
    public static void main(String[] args){
        AufgabeB3A2 task2 = new AufgabeB3A2(AufgabeB3A1.readStandardIn());

        for(int i = 1; i < args.length; i++){
            int k = Integer.parseInt(args[i]);
            int[] out = task2.choosePermutation(k);
            System.out.println("The " + k + "-th smallest permutation is:");
            System.out.println(Arrays.toString(out));
        }
    }
    public AufgabeB3A2(int[] data){
        AufgabeB3A1 task1 = new AufgabeB3A1(0);
        this.data = task1.removeDuplicates(data);
        System.out.println("Sorted input: ");
        Arrays.sort(this.data);
        System.out.println(Arrays.toString(this.data));
    }
    public int[] choosePermutation(int kSmallest){
        if(kSmallest == 1){
            return data.clone();
        }
        int temp = kSmallest - 1;
        temp = (int)(temp / faculty(data.length - 1));
        int first = data[temp];
        int[] backup = data.clone();
        //Create temporary data-array without ith element
        int[] reduced = new int[data.length - 1];
        for(int i = 0; i < temp; i++){
            reduced[i] = data[i];
        }
        for(int i = temp + 1; i < reduced.length; i++){
            reduced[i] = data[i];
        }
        this.data = reduced;
        int[] rest = choosePermutation(1); //TODO: k' reduced to 1??
        this.data = backup;
        int[] out = new int[rest.length + 1];
        for(int i = 1; i < out.length; i++){
            out[i] = rest[i - 1];
        }
        out[0] = first;
        return out;
    }
    private long faculty(int x){
        if(x == 0){
            return 1;
        }
        return x * faculty(x - 1);
    }
}
