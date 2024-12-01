public abstract class ChessPiece {
    protected String color;
    protected boolean check = true;

    // Конструктор, принимающий цвет фигуры
    public ChessPiece(String color) {
        this.color = color;
    }

    // Метод, возвращающий цвет фигуры
    public String getColor() {
        return color;
    }


    public boolean getCheck() {
        return check;
    }


    public void setCheck(boolean check) {
        this.check = check;
    }


    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);


    public abstract String getSymbol();
}
