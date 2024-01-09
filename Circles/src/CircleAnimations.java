import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleAnimations
{
	private ArrayList<Circle> circles; //the circles to animate
	private int               size;    //canvas width and height (will be square)
	private Random            rng;     //use to make random numbers

	/** create a drawing pane of a particular size */
	public CircleAnimations(int s) {
		circles = new ArrayList<>();
		size    = s;
		rng     = new Random();

		//don't mess with this
		StdDraw.setCanvasSize(size, size); //set up drawing canvas
		StdDraw.setXscale(0, size);        //<0, 0> is bottom left.  <size-1, size-1> is top right
		StdDraw.setYscale(0, size);
	}

	public void drawCircles()
	{
		for (int i = 0; i < circles.size(); i++)
		{
			circles.get(i).draw();
		}
	}

	public void addCircles()
	{
		for (int i = 1; i <= 3; i++)
		{
			StdDraw.setPenColor(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255));
			StdDraw.filledCircle(rng.nextInt(size), rng.nextInt(size), rng.nextInt(140) + 50);
		}
	}

	public void addCircles(int number)
	{
		for (int i = 0; i < number; i++)
		{
			int radius = rng.nextInt(50) + 1;
			int x = rng.nextInt(size - 2 * radius) + radius;
			int y = rng.nextInt(size - 2 * radius) + radius;
			Color color = new Color(rng.nextInt(255) + 1, rng.nextInt(255) + 1, rng.nextInt(255) + 1);
			circles.add(new Circle(x, y, radius, color));
		}
		for (int i = 0; i < number; i++)
		{
			circles.get(i).draw();
		}
	}


	public void noOverlapping(int number)
	{
		int i = 0;
		while (i < number) {
			Circle circle = new Circle(rng.nextInt(size), rng.nextInt(size), rng.nextInt(60) + 5, new Color(0, 0, 0));
			boolean overlaps = false;
			for (int e = 0; e < circles.size(); e++) {
				if (circle.overlaps(circles.get(e))) {
					overlaps = true;
					break;
				}
			}
			if (!overlaps) {
				circles.add(circle);
				circle.draw();
				i++;
			}
		}
	}

	public void movingCircles() {
		addCircles(150);
		for (int i = 0; i < circles.size(); i++)
		{
			circles.get(i).setDx(rng.nextInt(5) + 1);
			circles.get(i).setDy(rng.nextInt(5) + 1);
		}

		while (true)
		{
			for(int i = 0; i < circles.size(); i++)
			{

				circles.get(i).draw();
				circles.get(i).update();

			}
			StdDraw.show(10);
			StdDraw.clear();
		}
	}

	public void removeClicked()
	{
		addCircles(10);



		while (true)
		{

			drawCircles();

			if (StdDraw.isMousePressed())
			{
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();


				for (int i = 0; i < circles.size(); i++)
				{
					Circle circle = circles.get(i);
					double distance = Math.sqrt(Math.pow(mouseX - circle.x, 2) + Math.pow(mouseY - circle.y, 2));
					if (distance <= circle.radius)
					{
						circles.remove(i);
					}
				}


			}

			StdDraw.show(10);
			StdDraw.clear();
		}
	}





}
