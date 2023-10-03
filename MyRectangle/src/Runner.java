public class Runner {

    public static void main(String[] args) {
        MyRectangle one = new MyRectangle(3,4);
        MyRectangle two = new MyRectangle(2,2);
        MyRectangle three = new MyRectangle(1,6);
        System.out.println(one);
        one.setWidth(6);
        System.out.println(one.getWidth());
        System.out.println(two.getArea());
        System.out.println(three.getPerimeter());

        //add code here:
        MyRectangle[] myRectangles = new MyRectangle[10];
        for (int i = 0; i < 10; i++)
        {
            myRectangles[i] = new MyRectangle();
        }

        int area = 0;
        for (int i = 0; i < 10; i++)
        {
            area += myRectangles[i].getArea();
        }

        System.out.println(area);

    }


}
