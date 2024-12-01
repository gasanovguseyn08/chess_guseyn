public class King extends ChessPiece {

    public King(String color) {
        super(color); // Конструктор принимает только цвет и передает его в базовый класс
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Король не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, что король двигается на одну клетку в любую сторону
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            // Проверка, что конечная позиция либо пуста, либо занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return (targetPiece == null || !targetPiece.getColor().equals(this.color)) &&
                    !isUnderAttack(chessBoard, toLine, toColumn); // Король не может пойти под шах
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "K"; // Символ Короля — "K"
    }

    // Проверяет, находится ли клетка под атакой другой фигуры
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) { // Фигура противника
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // Клетка находится под атакой
                    }
                }
            }
        }
        return false; // Клетка безопасна
    }
}