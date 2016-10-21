package connectR;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String n = JOptionPane.showInputDialog("Enter board dimension. n=?");
        String m = JOptionPane.showInputDialog("Enter board dimension. m=?");
        String r = JOptionPane.showInputDialog("Enter require connect. r=?");
        String depth = JOptionPane.showInputDialog("MinMax Tree depth");
        Board board = new Board(Integer.parseInt(n), Integer.parseInt(m), Integer.parseInt(r), Board.Turn.O);
        board.printBoard();

        String pattern = JOptionPane.showInputDialog("choose one\n1: Human vs Human\n2: Human vs AI\n3: AI vs AI");
        Board prevBoard = board.clone();

        switch (pattern) {
            case "1":
                while (true) {
                    System.out.println(board.getTurn() + "'s turn\nr=redo\nq=quite");
                    if (board.getTurn() == Board.Turn.O) {
                        String input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                    } else {
                        String input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                    }
                    String input = JOptionPane.showInputDialog(board.getTurn() + "'s turn\nr=redo\nq=quite");
                    if (StringUtils.isNumeric(input)) {
                        prevBoard = board.clone();
                        while (!board.placeDisc(Integer.parseInt(input))) {
                            System.out.println("invalid move");
                            JOptionPane.showMessageDialog(null, "invalid move");

                            if (board.getTurn() == Board.Turn.O) {
                                input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                            } else {
                                input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                            }
                        }
                        board.printBoard();
                        if (board.isWon()) {
                            if (board.getTurn() == Board.Turn.X) {
                                System.out.println("O won the game");
                                JOptionPane.showMessageDialog(null, "Red won the game");
                            } else if (board.getTurn() == Board.Turn.O) {
                                System.out.println("X won the game");
                                JOptionPane.showMessageDialog(null, "Black won the game");
                            }
                            break;
                        }
                    } else if (input.equals("q")) {
                        break;
                    } else if (input.equals("r")) {
                        board = prevBoard;
                        board.printBoard();
                    }
                }
                break;
            case "2":
                System.out.println("Who goes first?\n1: AI\n2: Human");
                String first = JOptionPane.showInputDialog("Who goes first?\n1: AI\n2: Human");
                switch (first) {
                    case "1":
                        A:
                        while (true) {
                            System.out.println(board.getTurn() + "'s turn");
                            Utility util = new Utility();
                            board.placeDisc(util.minMaxDecision(board, Integer.parseInt(depth), Board.Turn.X));
                            board.printBoard();
                            if (board.isWon()) {
                                if (board.getTurn() == Board.Turn.X) {
                                    System.out.println("O won the game");
                                    JOptionPane.showMessageDialog(null, "Red won the game");
                                } else if (board.getTurn() == Board.Turn.O) {
                                    System.out.println("X won the game");
                                    JOptionPane.showMessageDialog(null, "Black won the game");
                                }
                                break;
                            }
                            //human's turn
                            boolean redoFlag = true;
                            while (redoFlag) {
                                System.out.println(board.getTurn() + "'s turn\nr=redo\nq=quite");
                                String input;
                                if (board.getTurn() == Board.Turn.O) {
                                    input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                                } else {
                                    input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                                }
                                if (StringUtils.isNumeric(input)) {
                                    prevBoard = board.clone();
                                    while (!board.placeDisc(Integer.parseInt(input))) {
                                        System.out.println("invalid move");
                                        if (board.getTurn() == Board.Turn.O) {
                                            input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                                        } else {
                                            input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                                        }
                                    }
                                    board.printBoard();
                                    redoFlag = false;
                                    if (board.isWon()) {
                                        if (board.getTurn() == Board.Turn.X) {
                                            System.out.println("O won the game");
                                            JOptionPane.showMessageDialog(null, "Red won the game");
                                        } else if (board.getTurn() == Board.Turn.O) {
                                            System.out.println("X won the game");
                                            JOptionPane.showMessageDialog(null, "Black won the game");
                                        }
                                        break A;
                                    }
                                } else if (input.equals("q")) {
                                    break A;
                                } else if (input.equals("r")) {
                                    board = prevBoard;
                                    board.printBoard();
                                    redoFlag = true;
                                }
                            }
                        }
                        break;
                    case "2":
                        String input;
                        while (true) {
                            boolean redoFlag = true;
                            while (redoFlag) {
                                System.out.println(board.getTurn() + "'s turn\nr=redo\nq=quite");
                                if (board.getTurn() == Board.Turn.O) {
                                    input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                                } else {
                                    input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                                }
                                if (StringUtils.isNumeric(input)) {
                                    prevBoard = board.clone();
                                    while (!board.placeDisc(Integer.parseInt(input))) {
                                        System.out.println("invalid move");
                                        if (board.getTurn() == Board.Turn.O) {
                                            input = JOptionPane.showInputDialog("Red's turn\nr=redo\nq=quite");
                                        } else {
                                            input = JOptionPane.showInputDialog("Black's turn\nr=redo\nq=quite");
                                        }
                                    }
                                    board.printBoard();
                                    redoFlag = false;
                                    if (board.isWon()) {
                                        if (board.getTurn() == Board.Turn.X) {
                                            System.out.println("O won the game");
                                            JOptionPane.showMessageDialog(null, "Red won the game");
                                        } else if (board.getTurn() == Board.Turn.O) {
                                            System.out.println("X won the game");
                                            JOptionPane.showMessageDialog(null, "Black won the game");
                                        }
                                        break;
                                    }
                                } else if (input.equals("q")) {
                                    break;
                                } else if (input.equals("r")) {
                                    board = prevBoard;
                                    board.printBoard();
                                    redoFlag = true;
                                }
                            }
                            // AI move
                            System.out.println(board.getTurn() + "'s turn");
                            Utility util = new Utility();
                            board.placeDisc(util.minMaxDecision(board, Integer.parseInt(depth), Board.Turn.X));

                            board.printBoard();
                            if (board.isWon()) {
                                if (board.getTurn() == Board.Turn.X) {
                                    System.out.println("O won the game");
                                    JOptionPane.showMessageDialog(null, "Red won the game");
                                } else if (board.getTurn() == Board.Turn.O) {
                                    System.out.println("X won the game");
                                    JOptionPane.showMessageDialog(null, "Black won the game");
                                }
                                break;
                            }
                        }
                        break;
                }

                break;
            case "3":
                while (true) {
                    System.out.println(board.getTurn() + "'s turn");
                    Utility util = new Utility();
                    board.placeDisc(util.minMaxDecision(board, Integer.parseInt(depth), Board.Turn.X));
                    prevBoard = board.clone();

                    board.printBoard();
                    if (board.isWon()) {
                        if (board.getTurn() == Board.Turn.X) {
                            System.out.println("O won the game");
                            JOptionPane.showMessageDialog(null, "Red won the game");
                        } else if (board.getTurn() == Board.Turn.O) {
                            System.out.println("X won the game");
                            JOptionPane.showMessageDialog(null, "Red won the game");
                        }
                        break;
                    }
                }
                break;
        }

    }
}

