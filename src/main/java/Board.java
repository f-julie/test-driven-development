public class Board {
    private int width, height;
    private Piece[][] board;

    public Board(int w, int  h) {
        this.board = new Piece[w][h];
        this.width = w;
        this.height = h;
    }

    public Piece get(int x, int y) {
        return this.board[x][y];
    }

    public boolean insertPiece(Piece newPiece, int insertX, int insertY) {
        this.board[insertX][insertY] = newPiece;
        return true;
    }

    public boolean checkBounds(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= this.width || startY < 0 || startY >= this.height) {
            return false;
        }
        if (endX < 0 || endX >= this.width || endY < 0 || endY >= this.height) {
            return false;
        }
        return true;
    }

    public boolean move(int startX, int startY, int deltaX, int deltaY) {
        if (deltaX == 0 && deltaY == 0) {
            return false;
        }

        if (startX < 0 || startX >= this.width || startY < 0 || startY >= 0) {
            return false;
        }

        int endX = startX + deltaX;
        int endY = startY + deltaY;

        if (!checkBounds(startX, startY, endX, endY)) {
            return false;
        }

        if (endX < 0 || endX >= this.width || endY < 0 || endY >= this.height) {
            return false;
        }

        if (Math.abs(deltaX) + Math.abs(deltaX) > 1) {
            return false;
        }

        if (this.board[endX][endY] instanceof Piece) {
            if (deltaX == 1) {
                endX += deltaX;
            } else if (deltaY == 1) {
                endY += deltaY;
            }

            if (!checkBounds(startX, startY, endX, endY)) {
                return false;
            }
        }

        this.board[endX][endY] = this.board[startX][startY];
        this.board[startX][startY] = null;

        return true;
    }
}
