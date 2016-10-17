package connectR;

import java.util.Scanner;

/**
 * Created by Jun on 10/17/2016.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(3, 2, 2, "X");
        board.printBoard();

        board.placeDisc(0);
        board.printBoard();
        Node node = new Node(board);
        System.out.println("----------------------");
        node.printTree();

    }
}
