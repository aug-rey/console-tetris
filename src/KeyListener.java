import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class KeyListener
{
    private final Set<Character> heldKeys = new HashSet<>();
    private boolean running = true;

    public boolean isKeyHeld(char key)
    {
        synchronized (heldKeys)
        {
            return heldKeys.contains(key);
        }
    }

    public void stop() {
        running = false;
    }

    public void startListening()
    {
        try
        {
            enableRawMode();
            while (running)
            {
                if (System.in.available() > 0) {
                    int key = System.in.read();

                    synchronized (heldKeys)
                    {
                        if (key == '\n') continue; // Ignore enter key presses
                        heldKeys.add((char) key);
                    }

                    if (key == 'q')
                    {
                        stop();
                        break;
                    }
                }

                // Sleep briefly to prevent excessive CPU usage
                Thread.sleep(10);
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                disableRawMode();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearHeldKeys()
    {
        synchronized (heldKeys)
        {
            heldKeys.clear();
        }
    }

    private void enableRawMode() throws IOException
    {
        new ProcessBuilder("sh", "-c", "stty raw -echo </dev/tty").inheritIO().start();
    }

    private void disableRawMode() throws IOException
    {
        new ProcessBuilder("sh", "-c", "stty -raw echo </dev/tty").inheritIO().start();
    }
}