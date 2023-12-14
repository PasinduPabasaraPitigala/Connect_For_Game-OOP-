package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private BoardUI boardUI;

    private Piece[][] pieces = new Piece[6][5];


    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }

        }
        this.boardUI = boardUI;
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        /*int nextAvailableSpot = findNextAvailableSpot(col);
        if (nextAvailableSpot==-1){
            return false;
        }
        return true;*/

        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            if (isLegalMove(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int avs = findNextAvailableSpot(col);
        pieces[col][avs] = move;
    }

    @Override
    public void updateMove(int col, int raw, Piece move) {
        pieces[col][raw] = move;
    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i < pieces.length; i++) {
            boolean f1 = pieces[i][2] == pieces[i][1];
            boolean f2 = pieces[i][2] == pieces[i][3];
            if (pieces[i][2] == Piece.EMPTY){
                continue;
            }
            if (f1 && f2) {
                if (pieces[i][2] == pieces[i][0]) {
                    int col1 = i;
                    int col2 = i;
                    int row1 = 0;
                    int row2 = 3;

                    Winner winner = new Winner(pieces[i][2], col1, col2, row1, row2);
                    return winner;
                }

                if (pieces[i][2] == pieces[i][4]) {
                    int col1 = i;
                    int col2 = i;
                    int row1 = 1;
                    int row2 = 4;

                    Winner winner = new Winner(pieces[i][2], col1, col2, row1, row2);
                    return winner;
                }


            }
        }
        for (int i = 0; i < 5; i++) {
            if (pieces[2][i] == Piece.EMPTY)continue;
            boolean f1 = pieces[2][i] == pieces[3][i];
            boolean f2 = pieces[0][i] == pieces[1][i];
            boolean f3 = pieces[4][i] == pieces[5][i];


            boolean f4 = pieces[2][i] == pieces[1][i];
            boolean f5 = pieces[3][i] == pieces[4][i];
            boolean f6 = pieces[4][i] == pieces[1][i];

            if (f1 && f2 && f4){
                int col1 = 0;
                int col2=3;
                int row1=i,row2 = i;
                return new Winner(pieces[2][i],col1,col2,row1,row2);
            }

            if (f1 && f3 && f5){
                int col1 = 2;
                int col2=5;
                int row1=i,row2 = i;
                return new Winner(pieces[2][i],col1,col2,row1,row2);
            }

            if (f1 && f6 && f4){
                int col1 = 1;
                int col2=4;
                int row1=i,row2 = i;
                return new Winner(pieces[2][i],col1,col2,row1,row2);
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
