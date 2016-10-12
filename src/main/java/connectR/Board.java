package connectR;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    List<List<StringBuilder>> board;
    int connect;

    Board(int n, int m, int r) {
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

    void addRow(List<StringBuilder> row) {
        this.board.add(row);
    }

    void setValue(int n, int m, String value) {
        this.board.get(m).get(n).replace(0, 1, value);
    }

    boolean placeDisc(int n,String value) {
        if (n<0||n>=this.board.get(0).size()){
            return false;
        }
        for (int m = this.board.size() - 1; m >= 0; m--) {
            if(this.board.get(m).get(n).toString().equals(" ")){
                setValue(n,m,value);
                return true;
            }
        }
        return false;
    }

    boolean isWonBy(){
        return false;
    }


    void printBoard() {
        System.out.print(" ");
        for(int i =0;i<this.board.get(0).size();i++) {
            System.out.print(i+ "  ");
        }
        System.out.println();
        this.board.forEach(System.out::println);
    }

}
