public class Tetromino
{
    private Block[][] tetromino;
    
    private int rotation;
    private int row;
    private int col;
    private int left;
    private int right;
    
    private int size = 0;
    
    
    public Tetromino(int size)
    {
        this.size = size;
        this.tetromino = new Block[this.size][this.size];
        this.rotation = 0;
    }
    
    public Tetromino()
    {
        this.size = 3;
        this.tetromino = new Block[this.size][this.size];
        this.rotation = 0;
    }
    
    public void rotate()
    {
        Block[][] rotated = new Block[size][size];
        
        for (int r = 0; r < size; r++)
        {
            for (int c = 0; c < size; c++)
            {
                rotated[c][size - 1 - r] = tetromino[r][c];
            }
        }
        
        tetromino = rotated;
        rotation++;
        setLeftRight();
    }
    
    public void rotateReverse()
    {
        Block[][] rotated = new Block[size][size];
        
        for (int r = 0; r < size; r++)
        {
            for (int c = 0; c < size; c++)
            {
                rotated[size - 1 - c][r] = tetromino[r][c];
            }
        }
        
        tetromino = rotated;
        rotation--;
        setLeftRight();
    }
    
    public void setLeftRight()
    {
        int left = -1;
        int right = size - 1;
        for (int col = 0; col < size; col++)
        {
            for (int row = 0; row < size; row++)
            {
                if (tetromino[row][col] != null)
                {
                    if (left == -1) left = col;
                    right = col;
                    break;
                }
            }
        }
        this.left = left;
        this.right = right;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getLeft()
    {
        return left;
    }
    
    public int getRight()
    {
        return right;
    }
    
    public void setCoordinates(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public void changeCoordinates(int row, int col)
    {
        this.row += row;
        this.col += col;
    }
    
    public Block[][] getTetromino()
    {
       return tetromino;
    }
    
    public void copyTetromino(Tetromino other)
    {
        this.size = other.size;
        this.tetromino = other.tetromino;
        this.rotation = other.rotation;
        this.row = other.row;
        this.col = other.col;
    }
    
    public int getRotation()
    {
       return rotation;
    }
    
    public Block getBlock(int row, int col)
    {
        return tetromino[row][col];
    }
    
    public void setBlock(Block block, int row, int col)
    {
        tetromino[row][col] = block;
    }
    
    public String[] toStrings()
    {
        String[] strings = {"", "", "", ""};
        int h = 4;
        for (int row = 0; row < this.size; row++)
        {
            int l = 4;
            for (int col = 0; col < this.size; col++)
            {
                if (this.getBlock(row, col) == null)
                {
                    strings[row] += ("  ");
                }
                else
                {
                    strings[row] += "" + this.getBlock(row, col);
                }
                l--;
            }
            
            while (l > 0)
            {
                strings[row] += "  ";
                l--;
            }
            h--;
        }
        
        while (h > 0)
        {
            strings[4 - h] += "        ";
            h--;
        }
        
        return strings;
    }
    
    public Tetromino clone() {
        Tetromino c = new Tetromino();
        c.copyTetromino(this);
        return c;
    }
    
    @Override
    public String toString()
    {
        return "Tetromino: " + row + "," + col + " : " + left + "," + right;
    }
}