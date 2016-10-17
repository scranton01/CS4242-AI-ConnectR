package connectR;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_jun on 10/13/2016.
 */
@Data
@NoArgsConstructor
public class Node {
    private Board board;
    private List<Node> children;

    Node(Board board) {
        this.board = board.clone();
        children = new ArrayList<>();
        createChildren(this);
    }

    public void printTree() {
        printTreeDfs(this);

    }
    private void printTreeDfs(Node node){
        node.getBoard().printBoard();
        System.out.println();
        if (!node.children.isEmpty()) {
            for(Node child : node.children){
                printTreeDfs(child);
            }
        }
        System.out.println("LEAF");
    }

    private void createChildren(Node node) {
        for (int n = 0; n < node.board.getColumn(); n++) {
            Board child = node.board.clone();
            if (child.placeDisc(n)) {
                if(child.isWon()){
                    Node leaf = new Node();
                    leaf.setBoard(child);
                    leaf.setChildren(new ArrayList<>());
                    node.children.add(leaf);
                }
                else {
                    node.children.add(new Node(child));
                }
            }
        }
    }


}
