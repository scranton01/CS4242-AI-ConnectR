package connectR;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("n=");
//        int n = scanner.nextInt();
//        System.out.print("m=");
//        int m = scanner.nextInt();
//        System.out.print("r=");
//        int r = scanner.nextInt();

        Board board = new Board(7, 6, 4);
        int input;
        while(true) {
            board.printBoard();
            input = scanner.nextInt();
            if(!board.placeDisc(input, "O")){
                System.out.println("invalid move");
            }
            board.printBoard();
            input = scanner.nextInt();
            if(!board.placeDisc(input, "X")){
                System.out.println("invalid move");
            }

        }
    }
}
