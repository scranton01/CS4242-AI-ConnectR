package connectR;


import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    @Value
    @Getter
    private class StateValue {
        double value;
        int index;
        Board board;
    }

    public int minMaxDecision(Board board, int depth, Board.Turn maxPlayer) {
        Node node = new Node(board,depth);
        int index = evaluateNode(node, maxPlayer).getIndex();
        return board.findColumn(node.getChildren().get(index).getBoard());

    }

    private StateValue evaluateNode(Node node, Board.Turn maxPlayer) {
        if (node.getChildren().isEmpty()) {
            return new StateValue(evaluateBoard(node.getBoard(), maxPlayer), -1, node.getBoard());
        }
        List<StateValue> stateValueList = new ArrayList<>();
        for (Node child : node.getChildren()) {
            stateValueList.add(evaluateNode(child, maxPlayer));
        }
        if (node.getBoard().getTurn() == maxPlayer) {
            return findMax(stateValueList);
        } else {
            return findMin(stateValueList);
        }
    }

    private StateValue findMax(List<StateValue> stateValueList) {
        double maxValue = stateValueList.get(0).getValue();
        int maxIndex = 0;
        for (int i = 0; i < stateValueList.size(); i++) {
            if (stateValueList.get(i).getValue() > maxValue) {
                maxValue = stateValueList.get(i).getValue();
                maxIndex = i;
            }
        }
        return new StateValue(maxValue, maxIndex, stateValueList.get(maxIndex).getBoard());
    }

    private StateValue findMin(List<StateValue> stateValueList) {
        double minValue = stateValueList.get(0).getValue();
        int minIndex = 0;
        for (int i = 0; i < stateValueList.size(); i++) {
            if (stateValueList.get(i).getValue() < minValue) {
                minValue = stateValueList.get(i).getValue();
                minIndex = i;
            }
        }
        return new StateValue(minValue, minIndex, stateValueList.get(minIndex).getBoard());
    }

    static double evaluateBoard(Board board, Board.Turn maxPlayer) {
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
