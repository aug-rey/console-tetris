public class TetrominoS extends Tetromino
{
    public TetrominoS(boolean solid)
    {
        super.setBlock(new Block(2, solid), 0, 1);
        super.setBlock(new Block(2, solid), 0, 2);
        super.setBlock(new Block(2, solid), 1, 0);
        super.setBlock(new Block(2, solid), 1, 1);
        setLeftRight();
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoS(false);
        c.copyTetromino(this);
        return c;
    }
}