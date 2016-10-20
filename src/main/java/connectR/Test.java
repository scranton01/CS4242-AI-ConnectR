package connectR;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(3, 3, 3, Board.Turn.X);

        board.placeDisc(0);
        board.placeDisc(2);
        board.placeDisc(0);
        board.placeDisc(0);
        board.placeDisc(1);
        Utility util = new Utility();
        board.printBoard();
        Node node = new Node(board,2);
        node.printTree();
        System.out.println(util.minMaxDecision(board,2, Board.Turn.O));
//        board.placeDisc(1);
//        board.printBoard();
//        System.out.println(util.evaluateBoard(board, Board.Turn.O));


    }
}
