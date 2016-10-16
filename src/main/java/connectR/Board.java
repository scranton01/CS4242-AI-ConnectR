package connectR;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    List<List<StringBuilder>> board;
    int column;
    int row;
    int connect;

    Board(int n, int m, int r) {
        this.column = n;
        this.row = m;
        this.connect = r;
        board = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<StringBuilder> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(new StringBuilder(" "));
            }
            addRow(row);
        }
    }

//    public Board clone (){
//        Board newBoard = new Board(this.column, this.row, this.connect);
//        List<List<StringBuilder>> gridValue = new ArrayList<>();
//        for (int m = 0; m < this.row; m++) {
//            List<StringBuilder> row = new ArrayList<>();
//            for (int n = 0; n < this.column; n++) {
//                row.add(new StringBuilder(this.getValue(n,m)));
//            }
//            gridValue.add(row);
//        }
//        newBoard.setBoard(gridValue);
//        return newBoard;
//    }


    private void addRow(List<StringBuilder> row) {
        this.board.add(row);
    }

    private void setValue(int n, int m, String value) {
        this.board.get(m).get(n).replace(0, 1, value);
    }

    public StringBuilder getValue(int n, int m) {
        return this.board.get(m).get(n);
    }

    public boolean placeDisc(int n, String value) {
        if (n < 0 || n >= this.column) {
            return false;
        }
        for (int m = this.row - 1; m >= 0; m--) {
            if (this.board.get(m).get(n).toString().equals(" ")) {
                setValue(n, m, value);
                return true;
            }
        }
        return false;
    }

    public boolean isWonBy() {
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
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
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
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
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
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
                return true;
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < this.row - m && i < this.column; i++) {
                line.append(getValue(this.column - i - 1, m + i));
            }
            if (line.toString().contains(xLine)) {
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
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
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
                return true;
            }
            line.replace(0, line.length(), "");
        }
        for (int m = 0; m < this.row; m++) {
            for (int i = 0; i < (this.row - m) && i < this.column; i++) {
                line.append(getValue(i, m + i));
            }
            if (line.toString().contains(xLine)) {
                System.out.println("X won the game");
                return true;
            } else if (line.toString().contains((oLine))) {
                System.out.println("O won the game");
                return true;
            }
            line.replace(0, line.length(), "");
        }
        return false;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < this.column; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.row; i++) {
            System.out.println(Integer.toString(i) + this.board.get(i));
        }
    }
}
