package BoardPack;
public interface LocationI {
        /**
         * 
         * @return  the value of X (rows)
         */
        public int getX();
        /**
         * 
         * @return  the value of Y (cols)
         */
        public int getY();
        /**
         * 
         * @return  a string of the coordinates
         */
        public String toString();
        public void setX(int x);
        public void setY(int y);


    
}
