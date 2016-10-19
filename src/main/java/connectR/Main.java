package connectR;


import org.apache.commons.lang3.StringUtils;

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

        Board board = new Board(7, 6, 4, Board.Turn.O);
        board.printBoard();
        Board prevBoard=board.clone();
        String input;
        A: while (true) {
            System.out.println(board.getTurn() + "'s turn");
            input = scanner.nextLine();
            if (StringUtils.isNumeric(input)) {
                prevBoard = board.clone();
                while (!board.placeDisc(Integer.parseInt(input))) {
                    System.out.println("invalid move");
                    input = scanner.nextLine();
                }
                board.printBoard();
                if (board.isWon()) {
                    if(board.getTurn()==Board.Turn.X){
                        System.out.println("O won the game");
                    } else if(board.getTurn()==Board.Turn.O){
                        System.out.println("X won the game");
                    }
                    break A;
                }
            } else if(input.equals("q")){
                break A;
            } else if(input.equals("r")){
                board = prevBoard;
                board.printBoard();
            }
            //AI make move
        }
    }
}
