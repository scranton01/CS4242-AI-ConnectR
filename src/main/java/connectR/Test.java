package connectR;

import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(7, 6, 4, Board.Turn.X);


        Node node = new Node (board,6);
        node.printTree();

    }
}
