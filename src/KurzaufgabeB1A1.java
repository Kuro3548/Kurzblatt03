import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class KurzaufgabeB1A1 {

    public static void main(String[] args){
        //Teil b)
        Scanner input = new Scanner(System.in); //Starten des Lesen vom Standard-In-Stream
        ArrayList<Integer> list = new ArrayList<>();
        while(input.hasNextLine()){
            try {
                int current = Integer.parseInt(input.nextLine()); //Konversion: String -> Integer (Fehleranfällig)
                list.add(current);
            }catch(Exception e){
                System.err.println("Input read was NaN");
                return;
            }
        }
        //Händisch: ArrayList<Integer> -> int[]
        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }

        //Teil c)
        Arrays.sort(arr);
        try {
            int select = Integer.parseInt(args[0]);
            if(select >= arr.length){
                System.err.println("The list must contain at least " + select + " numbers; only " + arr.length + " provided");
            }else if(select <= 0){
                System.out.println("Argument needs to be a positive, provided: " + select);
            }else{
                System.out.println("The " + select + prefix_generator(select) + "-smallest value is " + arr[select - 1]);
            }
        }catch(Exception e){
            System.err.println("Was unable to find input");
        }
    }

    /**
     * Returns the correct English Prefix for a given integer
     * @param x The integer used in an English sentence
     * @return Fitting prefix for x as a String
     */
    private static String prefix_generator(int x){
        int prefix_int = Math.abs(x) % 10;
        if(prefix_int == 1){
            return "st";
        }else if(prefix_int == 2){
            return "nd";
        }else if(prefix_int == 3){
            return "rd";
        }
        return "th";
    }
}
