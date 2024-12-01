public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color); // Конструктор принимает только цвет и передает его в базовый класс
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Пешка не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Определяем направление движения пешки в зависимости от её цвета
        int direction = color.equals("White") ? 1 : -1;

        // Пешка может ходить только вперед
        if (column == toColumn) {
            // Первый ход: пешка может переместиться на 2 клетки вперед
            if ((line == 1 && color.equals("White") || line == 6 && color.equals("Black")) &&
                    toLine - line == 2 * direction &&
                    chessBoard.board[line + direction][column] == null &&
                    chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
            // Обычный ход: пешка может переместиться на 1 клетку вперед
            if (toLine - line == direction && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
        }

        // Проверка на взятие по диагонали
        if (Math.abs(column - toColumn) == 1 && toLine - line == direction) {
            // Пешка может взять фигуру по диагонали
            if (chessBoard.board[toLine][toColumn] != null && !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }

        // Проверка на взятие на проходе
        if (toLine - line == direction && Math.abs(toColumn - column) == 1) {
            // Если пешка находит на поле рядом с противником, который двинулся на два поля
            ChessPiece piece = chessBoard.board[line][toColumn]; // смотрим на клетку, на которую мы могли бы взять
            if (piece instanceof Pawn && piece.getColor().equals(this.getColor())) {
                return false; // Пешка не может ходить на поле рядом с такой фигурой
            }
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P"; // Символ пешки — "P"
    }
}
