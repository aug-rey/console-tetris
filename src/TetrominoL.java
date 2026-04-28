public class TetrominoL extends Tetromino
{
    public TetrominoL(boolean solid)
    {
        super.setBlock(new Block(1, solid), 0, 1);
        super.setBlock(new Block(1, solid), 1, 1);
        super.setBlock(new Block(1, solid), 2, 1);
        super.setBlock(new Block(1, solid), 2, 2);
        setLeftRight();
    }

    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoL(false);
        c.copyTetromino(this);
        return c;
    }
}