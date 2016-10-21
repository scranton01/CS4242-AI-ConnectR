package connectR;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.black;
import static java.awt.Color.red;


@Data
public class Board {
    List<List<StringBuilder>> board;
    int column;
    int row;
    int connect;
    Turn turn;

    Board(int n, int m, int r, Turn t) {
        this.column = n;
        this.row = m;
        this.connect = r;
        this.turn = t;
        board = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<StringBuilder> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(new StringBuilder(" "));
            }
            addRow(row);
        }
    }

    public enum Turn {
        O,
        X
    }

    public Board clone() {
        Board newBoard = new Board(this.column, this.row, this.connect, this.turn);
        List<List<StringBuilder>> gridValue = new ArrayList<>();
        for (int m = 0; m < this.row; m++) {
            List<StringBuilder> row = new ArrayList<>();
            for (int n = 0; n < this.column; n++) {
                row.add(new StringBuilder(this.getValue(n, m)));
            }
            gridValue.add(row);
        }
        newBoard.setBoard(gridValue);
        return newBoard;
    }


    private void addRow(List<StringBuilder> row) {
        this.board.add(row);
    }

    private void setValue(int n, int m, Turn value) {
        this.board.get(m).get(n).replace(0, 1, value.toString());
    }

    public StringBuilder getValue(int n, int m) {
        return this.board.get(m).get(n);
    }

    public boolean placeDisc(int n) {
        if (n < 0 || n >= this.column) {
            return false;
        }
        for (int m = this.row - 1; m >= 0; m--) {
            if ((" ").equals(this.board.get(m).get(n).toString())) {
                setValue(n, m, this.getTurn());
                switch (this.turn) {
                    case X:
                        this.turn = Turn.O;
                        break;
                    case O:
                        this.turn = Turn.X;
                        break;
                }
                return true;
            }

        }
        return false;
    }

    public boolean isWon() {
        StringBuilder line = new StringBuilder();
        StringBuilder xLine = new StringBuilder();
        StringBuilder oLine = new StringBuilder();
        for (int i = 0; i < this.connect; i++) {
            xLine.append('X');
            oLine.append('O');
        }
        //check horizontal
        for (int m = 0; m < this.row; m++) {
            for (int n = 0; n < this.column; n++) {
                line.append(getValue(n, m));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        //check vertically
        for (int n = 0; n < this.column; n++) {
            for (int m = 0; m < this.row; m++) {
                line.append(getValue(n, m));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        //check diagonally upper-right to lower-left
        for (int n = 0; n < this.column; n++) {
            for (int i = 0; i <= n && i < this.row; i++) {
                line.append(getValue(n - i, i));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < this.row - m && i < this.column; i++) {
                line.append(getValue(this.column - i - 1, m + i));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        //check diagonally upper-left to lower-right
        for (int n = 0; n < this.column; n++) {
            for (int i = 0; i < this.column - n && i < this.row; i++) {
                line.append(getValue(n + i, i));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < (this.row - m) && i < this.column; i++) {
                line.append(getValue(i, m + i));
            }
            if (line.toString().contains(xLine)) {
                return true;
            } else if (line.toString().contains((oLine))) {
                return true;
            }
            line.replace(0, line.length(), "");
        }
        return false;
    }

    public boolean isTie() {
        boolean isFull = true;
        for (int n = 0; n < this.column; n++) {
            for (int m = 0; m < this.row; m++) {
                if ((" ").equals(getValue(n, m).toString())) {
                    isFull = false;
                }
            }
        }
        return (!isWon()) && isFull;
    }

    void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < this.column; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        this.board.forEach(System.out::println);

        ClassLoader classLoader = this.getClass().getClassLoader();
        ImageIcon red = new ImageIcon(classLoader.getResource("red.png"));
        ImageIcon black = new ImageIcon(classLoader.getResource("black.png"));
        ImageIcon blank = new ImageIcon(classLoader.getResource("blank.png"));

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(this.row+1, this.column));
        for(int i=0;i<this.column;i++){
            grid.add(new JLabel(String.valueOf(i)));
        }
        for (int m = 0; m < this.row; m++) {
            for (int n = 0; n < this.column; n++) {
                if(getValue(n,m).toString().equals("O")) {
                    grid.add(new JLabel(red));
                }
                else if(getValue(n,m).toString().equals("X"))
                    grid.add(new JLabel(black));
                else{
                    grid.add(new JLabel(blank));
                }
            }
        }
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 500));
        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
    }

    int findColumn(Board board) {
        int columnIndex = -1;
        for (int m = 0; m < this.row; m++) {
            for (int n = 0; n < this.column; n++) {
                if (!getValue(n, m).toString().equals(board.getValue(n, m).toString())) {
                    columnIndex = n;
                }
            }
        }
        return columnIndex;
    }

    int mostConnect(Turn turn) {
        StringBuilder line = new StringBuilder();
        StringBuilder maxX = new StringBuilder();
        StringBuilder maxO = new StringBuilder();

        //check horizontal
        for (int m = 0; m < this.row; m++) {
            for (int n = 0; n < this.column; n++) {
                line.append(getValue(n, m));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        //check vertically
        for (int n = 0; n < this.column; n++) {
            for (int m = 0; m < this.row; m++) {
                line.append(getValue(n, m));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        //check diagonally upper-right to lower-left
        for (int n = 0; n < this.column; n++) {
            for (int i = 0; i <= n && i < this.row; i++) {
                line.append(getValue(n - i, i));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < this.row - m && i < this.column; i++) {
                line.append(getValue(this.column - i - 1, m + i));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        //check diagonally upper-left to lower-right
        for (int n = 0; n < this.column; n++) {
            for (int i = 0; i < this.column - n && i < this.row; i++) {
                line.append(getValue(n + i, i));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < (this.row - m) && i < this.column; i++) {
                line.append(getValue(i, m + i));
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.toString().contains(maxX + "X")) {
                    maxX.append("X");
                } else if (line.toString().contains((maxO + "O"))) {
                    maxO.append("O");
                }
            }
            line.replace(0, line.length(), "");
        }
        if (turn == Turn.O) {
            return maxO.length();
        } else {
            return maxX.length();
        }
    }
}
