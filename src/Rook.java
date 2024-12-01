public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color); // Конструктор принимает только цвет и передает его в базовый класс
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ладья не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что движение происходит только по горизонтали или вертикали
        if (line == toLine || column == toColumn) {
            int stepX = Integer.compare(toLine, line);   // Шаг по линии
            int stepY = Integer.compare(toColumn, column); // Шаг по столбцу

            // Проверяем, что путь свободен (нет фигур на пути)
            int currentLine = line + stepX;
            int currentColumn = column + stepY;
            while (currentLine != toLine || currentColumn != toColumn) {
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

        // Если движение не по прямой линии, возвращаем false
        return false;
    }

    @Override
    public String getSymbol() {
        return "R"; // Символ ладьи — "R"
    }
}
