package com.marihacks.chessai.game.ai;

import com.marihacks.chessai.game.ui.Node;

import java.util.List;

public class Minimax {

    public static Node minimax(Node node, int layer, boolean maximizingPlayer) {

        List<Node> children = node.calculatePosition();
        if (layer == 0 || children.isEmpty()) {
            return (Node)(node).clone();
        }
        if (maximizingPlayer) {
            int worth = Integer.MIN_VALUE;
            Node bestNode = null;
            for (Node child : children) {
                Node value = minimax(child, layer - 1, false);
                if (value.calculateWorth() > worth) {
                    bestNode = child;
                }
            }
            return bestNode;

        } else {
            int worth = Integer.MAX_VALUE;
            Node bestNode = null;
            for (Node child : children) {
                Node value = minimax(child, layer - 1, true);
                if (value.calculateWorth() < worth) {
                    bestNode = child;
                }
            }
            return bestNode;
        }
    }

}
