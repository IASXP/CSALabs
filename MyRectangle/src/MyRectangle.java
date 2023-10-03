public class MyRectangle {
    private int length;
    private int width;

    public MyRectangle() {
        length = (int) (Math.random()*(9)) + 1;
        width = (int) (Math.random()*(9)) + 1;
    }
    public MyRectangle(int len, int wid) {
        length = len;
        width = wid;
    }
    public int getWidth() {
        return width;
    }
    public int getLength()
    {
        return length;
    }

    public void setWidth(int wid) {
        width = wid;
    }

    public void setLength(int len) {
        length = len;
    }

    public int getArea()
    {
        return length * width;
    }
    public int getPerimeter()
    {
        return (length + length + width + width);
    }

    public String toString()
    {
        return ("L: " + length + " W: " + width);
    }








}
