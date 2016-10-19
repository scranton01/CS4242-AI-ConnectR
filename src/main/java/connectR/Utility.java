package connectR;


import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    @Value
    @Getter
    private class StateValueIndex {
        double stateValue;
        Board board;
    }

    public int minMaxDecision(Board board, int depth, Board.Turn maxPlayer) {
        int stateIndex = evaluateNode(board, depth, maxPlayer).getIndex();
        Node node = new Node(board, depth);
        node.getChildren().get(stateIndex);
    }

    public StateValueIndex evaluateNode(Board board, int depth, Board.Turn maxPlayer) {
        Node node = new Node(board, depth);
        if (node.getChildren().isEmpty()) {
            return new StateValueIndex(evaluateBoard(board, maxPlayer), board);
        }
        List<Double> stateValueList = new ArrayList<>();
        for (Node child : node.getChildren()) {
            stateValueList.add(evaluateNode(child.getBoard(), depth, maxPlayer).getStateValue());
        }
        if (node.getBoard().getTurn() == maxPlayer) {
            return new StateValueIndex(findMax(stateValueList).getStateValue(), );
        } else {
            return new StateValueIndex(findMin(stateValueList).getStateValue(), findMax(stateValueList).getIndex());
        }
    }

    private StateValueIndex findMax(List<Double> stateValueList) {
        double maxValue = stateValueList.get(0);
        int maxIndex = 0;
        for (int i = 0; i < stateValueList.size(); i++) {
            if (stateValueList.get(i) > maxValue) {
                maxValue = stateValueList.get(i);
                maxIndex = i;
            }
        }
        return new StateValueIndex(maxValue, maxIndex);
    }

    private StateValueIndex findMin(List<Double> stateValueList) {
        double minValue = stateValueList.get(0);
        int minIndex = 0;
        for (int i = 0; i < stateValueList.size(); i++) {
            if (stateValueList.get(i) < minValue) {
                minValue = stateValueList.get(i);
                minIndex = i;
            }
        }
        return new StateValueIndex(minValue, minIndex);
    }

    public static double evaluateBoard(Board board, Board.Turn maxPlayer) {
        if (board.isWon()) {
            if (board.getTurn() == maxPlayer) {
                return -1;
            } else if (board.getTurn() == maxPlayer) {
                return 1;
            }
        }
        return 0;
    }
}
