import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cipher {
    private static char[][] code = 
    { {'.', ',', '(', ')', '-', '_', '"', '?', '|', ';'}, {'A', 'B', 'C', '<', 'a', 'b', 'c', '>', '2', '#'},
         {'D', 'E', 'F', '{', 'd', 'e', 'f', '}', '3', '*'}, {'G', 'H', 'I', '{', 'g', 'h', 'i', '}', '4', '@'},
         {'J', 'K', 'L', 'L', 'j', 'k', 'l', 'l', '5', '^'}, {'M', 'N', 'O', 'O', 'm', 'n', 'o', 'o', '6', '\''},
         {'P', 'Q', 'R', 'S', 'p', 'q', 'r', 's', '7', '~'}, {'T', 'U', 'V', 'V', 't', 'u', 'v', 'v', '8', '.'},
         {'W', 'X', 'Y', 'Z', 'w', 'x', 'y', 'z', '9', '0'}, {'!', '=', '+', 'x', '/', '/', '\\', ':', '0', ' '} };

    private static List<Integer> index(char c) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < code.length; i++) {
            for(int j = 0; j < code[i].length; j++) {
                if (code[i][j] == c) {
                    Integer a = Integer.valueOf(i);
                    Integer b = Integer.valueOf(j);
                    return new ArrayList<Integer>(Arrays.asList(a,b));
                }
            }
        }
        return l;

    }

    private static String decode(String msg) {
        String out = "";

        int length = msg.length();

        if(length % 2 != 0) {
            out = "length not divisble by 2\n";
            String[] parts = msg.split("00");
            for(String a : parts) {
                if (a.length() % 2 != 0) {
                    out = out + "Error is this part: " + a + '\n';
                }
            }
        }
        else {
            for(int x = 0; x < length; x +=2 ) {
                int index1 = Character.getNumericValue(msg.charAt(x)) - 1;
                int index2 = Character.getNumericValue(msg.charAt(x+1)) - 1;
                out = out + code[index1][index2];
            }
         }
        return out;
    }

    private static String encode(String msg) {
        String out = "";
        int length = msg.length();

        for(int x = 0; x < length; x++) {
            char c = msg.charAt(x);
            List<Integer> l = index(c);
            int index1 = l.get(0).intValue() + 1;
            int index2 = l.get(1).intValue() + 1;
            if (index1 == 10) {
                index1 = 0;
            }
            if(index2 == 10) {
                index2 = 0;
            }
            out = out + String.valueOf(index1) + String.valueOf(index2);
        }
        return out;
    }
    public static void main(String[] args) {
        /*
        Scanner in = new Scanner(System.in);
        String crypt = "";
        if(in.hasNextLine()) {
        do {
            System.out.print("Enter 'e' for encrpyt or 'd' for decrypt: ");
            crypt = in.next();
        } while(!(crypt != "e" || crypt != "d"));
    }
        System.out.println("\nType message: ");
        String msg = "";
        if(in.hasNextLine()) {
            msg = in.nextLine();
        }
        in.close();
        String coded = "";
        if(crypt == "e") {
            coded = encode(msg);
        }
        else {
            coded = decode(msg);
        }
        System.out.println("Output: " + coded);
        */

        if(args.length != 2) {
            System.out.println("Wrong number of arguments");
        }

        String type = args[0];
        String msg = args[1];
        String coded = "";
        if(type.compareTo("e") == 0) {
            coded = encode(msg);
        }
        else {
            System.out.println("Decoding");
            coded = decode(msg);
        }

        System.out.println("Original: " + msg);
        System.out.println("Output: " + coded);

    }
}