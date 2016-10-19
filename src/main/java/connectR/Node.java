package connectR;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Node {
    private Board board;
    private List<Node> children;
    private double stateValue;
    private int depth;

    Node(Board board, int depth) {
        this.board = board.clone();
        children = new ArrayList<>();
        this.depth = depth;
        generatePossibleStates(this, 0);
    }

    public void printTree() {
        printTreeDfs(this, 0);

    }

    private void printTreeDfs(Node node, int depth) {
        System.out.println("level: "+depth);
        depth++;
        node.getBoard().printBoard();
        System.out.println();
        if (!node.children.isEmpty()) {
            for (Node child : node.children) {
                printTreeDfs(child, depth);
            }
        }
        System.out.println("LEAF");
    }

    private void generatePossibleStates(Node node, int depthCounter) {
        if(depthCounter==this.depth){
            return;
        }
        depthCounter++;
        for (int n = 0; n < node.board.getColumn(); n++) {
            Board child = node.board.clone();
            if (child.placeDisc(n)) {
                if (child.isWon()) {
                    Node leaf = new Node();
                    leaf.setBoard(child);
                    leaf.setChildren(new ArrayList<>());
                    leaf.setDepth(this.depth);
                    node.children.add(leaf);
                } else {
                    Node newNode = new Node();
                    newNode.setBoard(child);
                    newNode.setChildren(new ArrayList<>());
                    newNode.setDepth(this.depth);
                    node.children.add(newNode);
                    generatePossibleStates(newNode, depthCounter);
                }
            }
        }
    }


}
