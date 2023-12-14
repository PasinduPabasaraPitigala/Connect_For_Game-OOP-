package lk.ijse.dep.service;

import java.util.*;
import java.util.stream.Collectors;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    public void movePiece(int col) {
        while (true) {
            col = minMax();
            if (board.isLegalMove(col)) {
                break;
            }
        }

        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);

        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.EMPTY) {
            boolean fl = board.existLegalMoves();
            if (!fl) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        } else {
            board.getBoardUI().notifyWinner(winner);
        }
    }

    public int minMax() {
        List<Holder> set = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            set.add(new Holder(i, 0));
        }
        for (int i = 0; i < set.size(); i++) {
            int avs = board.findNextAvailableSpot(i);
            if (avs==-1)continue;
            board.updateMove(i, Piece.GREEN);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() == Piece.GREEN) {
                for (Holder holder : set) {
                    if (holder.getCol() == i) {
                        int mark = holder.getMark();
                        mark += 100;
                        holder.setMark(mark);
                    }
                }
            }
            board.updateMove(i, avs, Piece.EMPTY);

        }

        for (int i = 0; i < 6; i++) {
            int avs = board.findNextAvailableSpot(i);
            if (avs==-1)continue;
            board.updateMove(i, Piece.BLUE);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() == Piece.BLUE) {
                for (Holder holder : set) {
                    if (holder.getCol() == i) {
                        int mark = holder.getMark();
                        mark += 99;
                        holder.setMark(mark);
                    }
                }
            }
            board.updateMove(i, avs, Piece.EMPTY);
        }
        return max(set);
    }

    private int max(List<Holder> list){
        int max = Integer.MIN_VALUE;
        int mi = -1;
        for (Holder holder : list) {
            if (max<holder.getMark()){
                max=holder.getMark();
                mi=holder.getCol();
            }
        }
        if (max==0){
            return new Random().nextInt(6);
        }
        return mi;
    }

}

// Java program demonstrate how to Sort TreeSet using
// Comparable interface in descending order


