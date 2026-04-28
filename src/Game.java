import java.util.ArrayList;

public class Game
{
    public static final int WIDTH = 10;
    public static final int HEIGHT = 23;
    public static final int MAX_LEVEL = 10;
    public static int level = 10;
    public static int frame = 0;
    public static int score = 0;
    public static int lines = 0;
    public static Tetromino nextTetromino;
    
    public static ArrayList<Tetromino> nextTetrominoes = new ArrayList<Tetromino>();
    
    public static String message = "";
    
    public static Tetromino randomTetromino()
    {
        if (nextTetrominoes.size() == 0)
        {
            ArrayList<Tetromino> allTetrominoes = new ArrayList<Tetromino>();
            
            allTetrominoes.add(new TetrominoI(false));
            allTetrominoes.add(new TetrominoO(false));
            allTetrominoes.add(new TetrominoL(false));
            allTetrominoes.add(new TetrominoJ(false));
            allTetrominoes.add(new TetrominoS(false));
            allTetrominoes.add(new TetrominoT(false));
            allTetrominoes.add(new TetrominoZ(false));
            
            while (allTetrominoes.size() > 0)
            {
                nextTetrominoes.add(allTetrominoes.remove((int) (Math.random() * allTetrominoes.size())));
            }
        }
        
        return nextTetrominoes.remove(0);
    }
    
    public static void setMessage(String message)
    {
        Game.message = message;
    }
}