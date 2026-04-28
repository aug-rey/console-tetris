public class Playfield
{
    private Block[][] playfield;
    private Tetromino currentTetromino;
    private Tetromino rotateChecker;
    
    public Playfield()
    {
        this.playfield = new Block[Game.HEIGHT][Game.WIDTH];
    }
    
    public Block getBlock(int row, int col)
    {
       return playfield[row][col];
    }
    
    public void setBlock(Block block, int row, int col)
    {
       playfield[row][col] = block;
    }
    
    public Block removeBlock(int row, int col)
    {
        Block temp = playfield[row][col];
        playfield[row][col] = null;
        return temp;
    }
    
    public boolean checkRotateTetromino()
    {
        rotateChecker = currentTetromino.clone();
        rotateChecker.rotate();
        if (rotateChecker.getCol() + rotateChecker.getLeft() < 0)
        {
            rotateChecker.changeCoordinates(0, -(rotateChecker.getCol() + rotateChecker.getLeft()));
        }
        
        if (rotateChecker.getCol() + rotateChecker.getRight() > Game.WIDTH)
        {
            rotateChecker.changeCoordinates(0, -(rotateChecker.getCol() + rotateChecker.getRight() - Game.WIDTH));
        }
        
        return checkAddTetromino(rotateChecker);
    }
    
    public void rotateTetromino()
    {
        if (checkRotateTetromino())
        {
            clearGhostTetrominoes();
            currentTetromino.rotate();
            addTetromino(currentTetromino, rotateChecker.getRow(), rotateChecker.getCol());
        }
    }
    
    public boolean checkAddTetromino(Tetromino t)
    {
        int row = t.getRow();
        int col = t.getCol();
        for (int i = 0; i < t.getSize(); i++)
        {
            for (int j = 0; j < t.getSize(); j++)
            {
                if (t.getBlock(i, j) == null)
                {
                    continue;
                }
                else
                {
                    if (row+i >= Game.HEIGHT || col+j >= Game.WIDTH) return false;
                    if (row+i < 0 || col+j < 0) return false;
                    if (playfield[row+i][col+j] != null && playfield[row+i][col+j].getSolid()) return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean checkMoveTetrominoDown()
    {
        int counted = 0;
        for (int row = 0; row < Game.HEIGHT - 1; row++)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] != null && !playfield[row][col].getSolid())
                {
                    if (playfield[row + 1][col] != null && playfield[row + 1][col].getSolid()) return false;
                    counted++;
                    if (counted >= 4) return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkMoveTetromino(int direction)
    {
        int counted = 0;
        for (int row = 0; row < Game.HEIGHT; row++)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                // for (Block b : Tetromino.getTetromino())
                
                if (playfield[row][col] != null && !playfield[row][col].getSolid())
                {
                    if (col + direction >= Game.WIDTH || col + direction < 0) return false;
                    if (playfield[row][col + direction] != null && playfield[row][col + direction].getSolid()) return false;
                    counted++;
                    if (counted >= 4) return true;
                }
            }
        }
        return false;
    }
    
    public boolean moveTetromino(int direction)
    {
        if (!checkMoveTetromino(direction)) return false;
        for (int row = 0; row < Game.HEIGHT; row++)
        {
            if (direction == -1)
            {
                for (int col = 0; col < Game.WIDTH; col++)
                {
                    if (playfield[row][col] == null || playfield[row][col].getSolid()) continue;
                    playfield[row][col + direction] = playfield[row][col];
                    playfield[row][col] = null;
                }
            }
            else if (direction == 1)
            {
                for (int col = Game.WIDTH - 1; col >= 0; col--)
                {
                    if (playfield[row][col] == null || playfield[row][col].getSolid()) continue;
                    playfield[row][col + direction] = playfield[row][col];
                    playfield[row][col] = null;
                }
            }
        }
        currentTetromino.changeCoordinates(0, direction);
        return true;
    }
    
    public boolean moveTetrominoDown()
    {
        if (!checkMoveTetrominoDown())
        {
            return false;
        }
        
        for (int row = Game.HEIGHT - 2; row >= 0; row--)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] == null || playfield[row][col].getSolid()) continue;
                playfield[row + 1][col] = playfield[row][col];
                playfield[row][col] = null;
            }
        }
        currentTetromino.changeCoordinates(1, 0);
        return true;
    }
    
    public void addTetromino(Tetromino t, int row, int col)
    {
        this.currentTetromino = t;
        t.setCoordinates(row, col);
        
        for (int i = 0; i < t.getSize(); i++)
        {
            for (int j = 0; j < t.getSize(); j++)
            {
                if (t.getBlock(i, j) == null)
                {
                    continue;
                }
                else
                {
                    playfield[row+i][col+j] = t.getBlock(i, j);
                }
            }
        }
    }
    
    public void solidifyTetrominoes()
    {
        for (int row = 0; row < Game.HEIGHT; row++)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] != null && !playfield[row][col].getSolid()) playfield[row][col].setSolid(true);
            }
        }
    }
    
    public void clearGhostTetrominoes()
    {
        for (int row = 0; row < Game.HEIGHT; row++)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] != null && !playfield[row][col].getSolid()) playfield[row][col] = null;
            }
        }
    }
    
    public int clearLines()
    {
        int cleared = 0;
        int[] lines = {-1, -1, -1, -1};
        boolean clear;
        
        for (int row = Game.HEIGHT - 1; row >= 0; row--)
        {
            clear = true;
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] == null)
                {
                    clear = false;
                    break;
                }
            }
            if (clear)
            {
                lines[lines.length - 1 - cleared] = row;
                cleared++;
            }
        }
        
        for (int line : lines)
        {
            if (line == -1) continue;
            for (int i = 0; i < Game.WIDTH; i++)
            {
                playfield[line][i] = null;
            }
            for (int row = line - 1; row >= 0; row--)
            {
                for (int col = 0; col < Game.WIDTH; col++)
                {
                    playfield[row + 1][col] = playfield[row][col];
                    playfield[row][col] = null;
                }
            }
        }
        switch (cleared)
        {
            case 1:
                Game.score += (Game.MAX_LEVEL - Game.level + 1) * 40;
                break;
            case 2:
                Game.score += (Game.MAX_LEVEL - Game.level + 1) * 100;
                break;
            case 3:
                Game.score += (Game.MAX_LEVEL - Game.level + 1) * 300;
                break;
            case 4:
                Game.score += (Game.MAX_LEVEL - Game.level + 1) * 1200;
        }
        Game.lines += cleared;
        Game.level = Game.MAX_LEVEL - Game.lines /  10;
        return cleared;
    }
    
    public boolean checkLose()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] != null && playfield[row][col].getSolid()) return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        String board = "╔════════════════════╗\r\n";
        board += "║ Lines: " + Game.lines;
        for (int i = 0; i < (6 - this.getDigits(Game.lines)); i++)
        {
            board += " ";
        }
        board += "      ║\r\n";
        board += "╚════════════════════╝\r\n";
        board += "┌";
        String[] next = Game.nextTetromino.toStrings();
        for (int i = 0; i < Game.WIDTH * 2; i++)
        {
            board += "─";
        }
        board += "┐\r\n";
        for (int row = 3; row < Game.HEIGHT; row++)
        {
            board += "│";
            for (int col = 0; col < Game.WIDTH; col++)
            {
                if (playfield[row][col] == null)
                {
                    board += " ⸱";
                }
                else
                {
                    board += playfield[row][col];
                }
            }
            if (row == 6)
            {
                board += "│ ╔═══════════════╗\r\n";
            }
            // else if (row == 4)
            // {
                
            // }
            else if (row == 8)
            {
                board += "│ ╚═══════════════╝\r\n";
            }
            else if (row == 7)
            {
                board += "│ ║ Score: ";
                for (int i = 0; i < (6 - this.getDigits(Game.score)); i++)
                {
                    board += "0";
                }
                board += Game.score + " ║\r\n";
            }
            else if (row == 9)
            {
                board += "│ ╔═Next Block═╗\r\n";
            }
            else if (row >= 10 && row <= 13)
            {
                board += "│ ║   " + next[row - 10] + " ║\r\n";
            }
            else if (row == 14)
            {
                board += "│ ╚════════════╝\r\n";
            }
            else
            {
                board += "│\r\n";
            }
        }
        board += "└";
        for (int i = 0; i < Game.WIDTH * 2; i++)
        {
            board += "─";
        }
        board += "┘";
        if (currentTetromino != null)
        {
            // board += "\r\n" + Game.nextTetromino;
        }
        // board += "\r\n" + currentTetromino.toString();
        // if (rotateChecker != null)
        // {
        //     board += "\r\n" + rotateChecker.toString();
        // }
        board += "\r\n" + Game.message;
        return board;
    }
    
    private int getDigits(int n)
    {
        if (n == 0) return 1;
        else return ((int)(Math.log10(n)) + 1);
    }
    
    public void print()
    {
        Utils.clearScreen();
        // System.out.println("\r\n");
        System.out.println(this);
    }
}