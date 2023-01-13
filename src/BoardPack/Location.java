package BoardPack;
public class Location implements LocationI{
     int x,y;
    /**
     * Location represents the x and y 
     * @param x  the rows
     * @param y  the cols
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String toString()
    {
        return x + " " + y;
    }

    @Override
    public void setX(int x) {
        // TODO Auto-generated method stub
        this.x = x;
        
    }

    @Override
    public void setY(int y) {
        // TODO Auto-generated method stub
        this.y = y;
    }
}
