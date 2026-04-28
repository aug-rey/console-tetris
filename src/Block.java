public class Block
{
    private int type;
    private boolean solid;
    private int color;

    public Block(int type, boolean solid)
    {
        this.type = type;
        this.solid = solid;
    }
    
    public Block(int type, boolean solid, int color)
    {
        this.color = color;
        this.type = type;
        this.solid = solid;
    }
    
    public int getType()
    {
       return type;
    }
    
    public void setType(int type)
    {
       this.type = type;
    }
    
    public boolean getSolid()
    {
       return solid;
    }
    
    public void setSolid(boolean solid)
    {
       this.solid = solid;
    }
    
    public String toString()
    {
        String block = Colors.getColor(color);
        switch (type)
        {
            case 0: block += "██"; break;
            case 1: block += "▓▓"; break;
            case 2: block += "▒▒"; break;
            case 3: block += "░░"; break;
            default: block += ":(";
        }
        block += Colors.RESET;
        return block;
    }
}