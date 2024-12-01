public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }


        if (line == toLine && column == toColumn) {
            return false;
        }


        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        boolean isLMove = (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);

        if (!isLMove) {
            return false;
        }


        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
