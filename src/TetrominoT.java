public class TetrominoT extends Tetromino
{
    public TetrominoT(boolean solid)
    {
        super.setBlock(new Block(3, solid), 1, 0);
        super.setBlock(new Block(3, solid), 1, 1);
        super.setBlock(new Block(3, solid), 1, 2);
        super.setBlock(new Block(3, solid), 2, 1);
        setLeftRight();
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoT(false);
        c.copyTetromino(this);
        return c;
    }
}