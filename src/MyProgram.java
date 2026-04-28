public class MyProgram
{
    public static void main(String[] args)
    {
        KeyListener keyListener = new KeyListener();
        Thread keyThread = new Thread(keyListener::startListening);
        keyThread.setDaemon(true);
        keyThread.start();
        
        int number = 0;
        
        Playfield playfield = new Playfield();
        
        boolean refresh = true;
        
        Tetromino first = Game.randomTetromino();
        playfield.addTetromino(first, 0, (int) (Math.random() * (Game.WIDTH - first.getRight() - 1)));
        
        Game.nextTetromino = Game.randomTetromino();
        
        while (true)
        {
            try
            {
                Game.frame++;
                Thread.sleep(100);
                
                // playfield.currentTetromino.getRow();
                // playfield.currentTetromino.getRow();
                
                if (keyListener.isKeyHeld('a'))
                {
                    playfield.moveTetromino(-1);
                    refresh = true;
                }
                else if (keyListener.isKeyHeld('d'))
                {
                    playfield.moveTetromino(1);
                    refresh = true;
                }
                if (keyListener.isKeyHeld('w'))
                {
                    playfield.rotateTetromino();
                    refresh = true;
                }
                
                if (Game.level <= 1 || Game.frame % Game.level == 0 || keyListener.isKeyHeld('s'))
                {
                    if (!playfield.moveTetrominoDown())
                    {
                        playfield.solidifyTetrominoes();
                        playfield.clearLines();
                        if (playfield.checkLose()) break;
                        playfield.addTetromino(Game.nextTetromino, 0, (int) (Math.random() * (Game.WIDTH - Game.nextTetromino.getRight() - 1)));
                        Game.nextTetromino = Game.randomTetromino();
                    }
                    refresh = true;
                }
                
                if (Game.score > 999999) Game.score = 999999;
                
                if (refresh) {
                    playfield.print();
                    refresh = false;
                }
                keyListener.clearHeldKeys();
                
                if (Game.score >= 999999) break;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        if (Game.score >= 999999)
        {
            System.out.println("You Win!");
        }
        else
        {
            System.out.println("You Lose!");
        }
    }
}