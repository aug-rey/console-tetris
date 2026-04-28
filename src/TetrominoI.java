public class TetrominoI extends Tetromino
{
    public TetrominoI(boolean solid)
    {
        super(4);
        super.setBlock(new Block(2, solid), 1, 0);
        super.setBlock(new Block(2, solid), 1, 1);
        super.setBlock(new Block(2, solid), 1, 2);
        super.setBlock(new Block(2, solid), 1, 3);
        setLeftRight();
    }
    
    public void rotate()
    {
        if (super.getRotation() % 2 == 1)
        {
            super.rotate();
        }
        else
        {
            super.rotateReverse();
        }
    }
    
    @Override
    public Tetromino clone() {
        Tetromino c = new TetrominoI(false);
        c.copyTetromino(this);
        return c;
    }
}