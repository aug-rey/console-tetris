public class Colors
{
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String RESET = "\u001B[0m";
    
    public static String getColor(int colorCode)
    {
        switch (colorCode)
        {
            case 0:  return BLACK;
            case 1:  return RED;
            case 2:  return GREEN;
            case 3:  return YELLOW;
            case 4:  return BLUE;
            case 5:  return MAGENTA;
            case 6:  return CYAN;
            case 7:  return WHITE;
            case 8:  return BRIGHT_BLACK;
            case 9:  return BRIGHT_RED;
            case 10: return BRIGHT_GREEN;
            case 11: return BRIGHT_YELLOW;
            case 12: return BRIGHT_BLUE;
            case 13: return BRIGHT_MAGENTA;
            case 14: return BRIGHT_CYAN;
            case 15: return BRIGHT_WHITE;
            default: return RESET;
        }
    }
}