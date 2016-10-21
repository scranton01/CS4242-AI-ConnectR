package connectR;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(3, 3, 3, Board.Turn.X);

        board.placeDisc(1);
        board.placeDisc(0);
        board.placeDisc(1);
        board.placeDisc(2);

        board.printBoard();
        Utility util = new Utility();
//        System.out.println(util.evaluateBoard(board, Board.Turn.O));
        System.out.println(util.minMaxDecision(board, 4, Board.Turn.O));
//        board.printBoard();
//        System.out.println(util.evaluateBoard(board, Board.Turn.O));





    }
}
