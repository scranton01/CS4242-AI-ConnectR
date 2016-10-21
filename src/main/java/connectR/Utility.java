package connectR;


public class Utility {


    int minMaxDecision(Board board, int depth, Board.Turn maxPlayer) {
        Node node = new Node(board, depth);
        Node maxNode = evaluateNode(node, maxPlayer);
        return board.findColumn(maxNode.getBoard());
    }

    private Node evaluateNode(Node node, Board.Turn maxPlayer) {
        if (node.getChildren().isEmpty()) {
            node.setStateValue(evaluateBoard(node.getBoard(), maxPlayer));
            return node;
        }
        for (Node child : node.getChildren()) {
            evaluateNode(child, maxPlayer);
        }
        if (node.getBoard().getTurn() == maxPlayer) {
            return findMax(node);
        } else {
            return findMin(node);
        }
    }

    private Node findMax(Node node) {
        double maxValue = node.getChildren().get(0).getStateValue();
        Node maxNode = new Node();
        for (Node child : node.getChildren()) {
            if (child.getStateValue() >= maxValue) {
                maxValue = child.getStateValue();
                maxNode = child;
            }
        }
        node.setStateValue(maxValue);
        return maxNode;
    }

    private Node findMin(Node node) {
        double minValue = node.getChildren().get(0).getStateValue();
        Node minNode = new Node();
        for (Node child : node.getChildren()) {
            if (child.getStateValue() <= minValue) {
                minValue = child.getStateValue();
                minNode = child;
            }
        }
        node.setStateValue(minValue);
        return minNode;
    }

    static double evaluateBoard(Board board, Board.Turn maxPlayer) {
        if (board.isWon()) {
            if (board.getTurn() == maxPlayer) {
                return -1;
            } else if (board.getTurn() != maxPlayer) {
                return 1;
            }
        }
        if (board.isTie()) {
            return 0;
        }
        if (board.getTurn() == maxPlayer) {
            if (maxPlayer == Board.Turn.O) {
                return -board.mostConnect(Board.Turn.X) / 10.0;
            } else {
                return -board.mostConnect(Board.Turn.O) / 10.0;
            }
        } else {
            return board.mostConnect(maxPlayer) / 10.0;
        }
    }
}
