public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color); // Конструктор принимает только цвет и передает его в базовый класс
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ферзь не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, что ход идет по прямой (как ладья) или по диагонали (как слон)
        if (line == toLine || column == toColumn || Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            int stepX = Integer.compare(toLine, line);
            int stepY = Integer.compare(toColumn, column);

            int currentLine = line + stepX;
            int currentColumn = column + stepY;

            // Проверяем, что путь свободен
            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false;
                }
                currentLine += stepX;
                currentColumn += stepY;
            }

            // Проверка конечной позиции
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "Q"; // Символ Ферзя — "Q"
    }
}
