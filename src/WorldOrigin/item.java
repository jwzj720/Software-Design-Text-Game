package WorldOrigin;
public class item implements itemI{
    private int quanity;
    private String name;

   /**
    * represents an item in inventory
    * @param name  the name
    * @param i  the quantity
    */
    public item(String name, int i)
    {
        this.name =name;
        this.quanity = i;
    }
    
    /**
     * @see WorldOrigin.itemI#getName()
     */
    public String getName()
    {
        return name;
    }
    /** 
     * @see WorldOrigin.itemI#setName(java.lang.String)
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @see WorldOrigin.itemI#setQuanity(int)
     */
    public void setQuanity(int i)
    {
        this.quanity = i;
    }
    /** 
     * @see WorldOrigin.itemI#getQuanity()
     */
    public int getQuanity()
    {
        return quanity;
    } 
    /**
     * @Override
     * @param other  the object to compare to
     * @return negative if the other's quanity is greater
     */ 
    public int compareTo(itemI other)
    {
        return this.quanity - other.getQuanity();
    }
   /**
    * @Override
    * @param other the object to compare to
    * @return true if the items are equal in name
    */
    public boolean equals(itemI other)
    {
        if(this.name.equals(other.getName()))
        {
            return true;
        }
        return false;
    } 
    /**
     * @see WorldOrigin.itemI#toString()
     * @Override
     */
    public String toString()
    {
        return name + " " + String.valueOf(quanity) + "\n";
    }
}
