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
        board.printBoard();
        int input = 0;
        A: while (true) {
            System.out.println("O's turn");
            input = scanner.nextInt();
            while (!board.placeDisc(input, "O")) {
                if (input == -1) {
                    break A;
                }
                System.out.println("invalid move");
                input = scanner.nextInt();
            }
            board.printBoard();
            if(board.isWonBy()){
                break A;
            }

            System.out.println("X's turn");
            input = scanner.nextInt();
            while (!board.placeDisc(input, "X")) {
                if (input == -1) {
                    break A;
                }
                System.out.println("invalid move");
                input = scanner.nextInt();
            }
            board.printBoard();
            if(board.isWonBy()){
                break A;
            }
        }
    }
}
