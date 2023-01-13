package WorldOrigin;
public interface itemI {
    /**
     *
     * @return  gets the name of the string name
     */
    public String getName();
    /**
     * sets the name
     * @param name  string of name
     */
    public void setName(String name);
    /**
     * sets the number of the item
     * @param i  the number of items held
     */
    public void setQuanity(int i);
    /**
     * gets the number of items
     * @return  the number of items held
     */
    public int getQuanity();
    /**
     * 
     * @return the string representation - just the name
     */
    public String toString();
    
}
