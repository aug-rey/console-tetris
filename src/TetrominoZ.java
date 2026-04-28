public class TetrominoZ extends Tetromino
{
    public TetrominoZ(boolean solid)
    {
        super.setBlock(new Block(1, solid), 0, 0);
        super.setBlock(new Block(1, solid), 0, 1);
        super.setBlock(new Block(1, solid), 1, 1);
        super.setBlock(new Block(1, solid), 1, 2);
        setLeftRight();
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoZ(false);
        c.copyTetromino(this);
        return c;
    }
}