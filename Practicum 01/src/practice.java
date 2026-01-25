import java.util.Scanner;

public class practice {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        Double num;
        Double doublednum;

        System.out.println("enter number to double");
        num = Double.parseDouble(in.nextLine());

        doublednum = num*2;
        System.out.println("Answer: " + doublednum);
    }
}
