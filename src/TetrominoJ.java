public class TetrominoJ extends Tetromino
{
    public TetrominoJ(boolean solid)
    {
        super.setBlock(new Block(0, solid), 0, 1);
        super.setBlock(new Block(0, solid), 1, 1);
        super.setBlock(new Block(0, solid), 2, 1);
        super.setBlock(new Block(0, solid), 2, 0);
        setLeftRight();
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoJ(false);
        c.copyTetromino(this);
        return c;
    }
}