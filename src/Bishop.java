public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color); // Конструктор принимает только цвет и передает его в базовый класс
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Слон не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, что движение происходит по диагонали
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if (deltaX == deltaY) { // Диагональное движение
            // Определяем направление движения
            int stepX = (toLine - line) / deltaX;
            int stepY = (toColumn - column) / deltaY;

            // Проверяем, что путь свободен (нет фигур на пути)
            int currentLine = line + stepX;
            int currentColumn = column + stepY;
            while (currentLine != toLine && currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false;
                }
                currentLine += stepX;
                currentColumn += stepY;
            }

            // Проверка, что конечная позиция либо пуста, либо занята фигурой противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }

        // Если движение не по диагонали, возвращаем false
        return false;
    }

    @Override
    public String getSymbol() {
        return "B"; // Символ слона — "B"
    }
}
