import java.awt.*;

public class Circle {
    int x;
    int y;

    int dx;
    int dy;

    int radius;
    Color color;

    public Circle (int xval, int yval,int rad, Color col )
    {
        x = xval;
        y = yval;
        radius = rad;
        color = col;

    }

    public Circle (int xval, int yval, int dxf, int dyf, int rad, Color col)
    {
        x = xval;
        y = yval;
        radius = rad;
        color = col;
        dx = dxf;
        dy= dyf;

    }


    public void draw()
    {
        StdDraw.setPenColor(color);         //set the current color of the drawing window
        StdDraw.filledCircle(x, y, radius); //draw a filled circle at <x, y>, diam of radius*2
    }

    public boolean overlaps (Circle other)
    {
        int fx = this.x;
        int fy = this.y;
        int fr = this.radius;

        int sx = other.x;
        int sy = other.y;
        int sr = other.radius;

        int dist = (int) Math.sqrt(Math.pow((sx - fx), 2) + Math.pow((sy - fy), 2));

        int srfr = sr + fr;


        if (dist >= srfr)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public void update() {
        x += dx;
        y += dy;
        bounce();

    }

    public void setDx (int fx)
    {
        dx = fx;
    }

    public void setDy (int fy)
    {
        dy = fy;
    }
    public void bounce()
    {
        if (this.x >= 600 - this.radius || this.x <= this.radius)
                setDx(-dx);
        if (this.y >= 600 - this.radius || this.y <= this.radius)
                setDy(-dy);
    }











}
