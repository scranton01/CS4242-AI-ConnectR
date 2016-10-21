package connectR;

import javax.swing.*;
import java.applet.Applet;
import java.util.Scanner;

public class Test extends Applet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(3, 3, 3, Board.Turn.X);

        board.placeDisc(1);
        board.placeDisc(0);
        board.placeDisc(1);
        board.placeDisc(2);
        board.placeDisc(0);
        board.placeDisc(2);
        board.placeDisc(1);


        board.printBoard();

        JOptionPane.showMessageDialog(null,"Hi");
        Utility util = new Utility();
        System.out.println(util.evaluateBoard(board, Board.Turn.O));
        System.out.println(util.minMaxDecision(board, 4, Board.Turn.O));
        board.printBoard();
        System.out.println(util.evaluateBoard(board, Board.Turn.O));
        System.out.println(scanner.nextInt());
        System.out.println(scanner.nextLine());



    }
}
