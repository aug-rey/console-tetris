public class TetrominoO extends Tetromino
{
    public TetrominoO(boolean solid)
    {
        super.setBlock(new Block(0, solid), 0, 0);
        super.setBlock(new Block(0, solid), 0, 1);
        super.setBlock(new Block(0, solid), 1, 0);
        super.setBlock(new Block(0, solid), 1, 1);
        setLeftRight();
    }
    
    @Override
    public void rotate()
    {
        
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoO(false);
        c.copyTetromino(this);
        return c;
    }
}