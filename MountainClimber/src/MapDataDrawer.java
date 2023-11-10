import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MapDataDrawer
{
	/** the 2D array containing the elevations */
	private int[][] grid;
	private int rows;
	private int columns;
	File file;
	Scanner console;

	/** constructor, parses input from the file into grid */
	public MapDataDrawer(String fileName) throws IOException
	{
		file = new File(fileName);
		console = new Scanner(file);
		rows = console.nextInt();
		columns = console.nextInt();
		grid = new int[rows][columns];

		for (int i = 0; i < grid.length; i++) {
			for (int e = 0; e < grid[0].length; e++) {
				grid[i][e] = console.nextInt();
			}
		}
	}

	/** @return the min value in the entire grid */
	public int findMin()
	{
		int min = grid[0][0];
		for (int i = 0; i < grid.length; i++)
		{
			for (int f = 0; f < grid[0].length; f++)
			{
				if (grid[i][f] < min)
				{
					min = grid[i][f];
				}
			}
		}
		return min; //REPLACE
	}

	/** @return the max value in the entire grid */
	public int findMax()
	{
		int max = grid[0][0];
		for (int i = 0; i < grid.length; i++)
		{
			for (int f = 0; f < grid[0].length; f++)
			{
				if (grid[i][f] > max)
				{
					max = grid[i][f];
				}
			}
		}
		return max; //REPLACE
	}

	/**
	 * Draws the grid using the given Graphics object.
	 * Colors should be grayscale values 0-255, scaled based on min/max values in grid
	 */
	public void drawMap(Graphics g)
	{
		int min = findMin();
		int max = findMax();
		for (int i = 0; i < grid.length; i++) {
			for (int f = 0; f < grid[0].length; f++) {
				g.setColor(new Color(100 * (grid[i][f] - min)/(max - min),
									 100 * (grid[i][f] - min)/(max - min),
									 100 * (grid[i][f] - min)/(max - min)));
				g.fillRect(f, i, 1, 1);
			}
		}
	}

	/**
	 * Find a path from West-to-East starting at given row.
	 * Choose a forward step out of 3 possible forward locations, using greedy method described in assignment.
	 * @return the total change in elevation traveled from West-to-East
	 */
	public int drawLowestElevPath(Graphics g, int row) {
		int elevChange = 0;
		int currentRow = row;


		g.fillRect(0, currentRow, 1, 1);

		for (int column = 1; column < grid[0].length - 1; column++)
		{
			int fwdUp = 1000000000;
			int fwdDown = 1000000000;

			if (currentRow > 0)
			{
				fwdUp = Math.abs(grid[currentRow][column] - grid[currentRow - 1][column + 1]);
			}

			if (currentRow < grid.length - 1)
			{
				fwdDown = Math.abs(grid[currentRow][column] - grid[currentRow + 1][column + 1]);
			}

			int fwdOnly = Math.abs(grid[currentRow][column] - grid[currentRow][column + 1]);

			int leastElevChange = Math.min(fwdUp, Math.min(fwdOnly, fwdDown));

			if (leastElevChange == fwdUp )
			{
				currentRow--;
			} else if (leastElevChange == fwdDown )
			{
				currentRow++;
			}
			g.fillRect(column + 1, currentRow, 1, 1);
			elevChange += leastElevChange;
		}

		return elevChange;
	}


	/** @return the index of the starting row for the lowest-elevation-change path in the entire grid. */
	public int indexOfLowestElevPath(Graphics g) {
		int[] numpaths = new int[480];
		int lowestElevationChange = 100000000;
		int lowestElevationRow = -1;

		for (int i = 0; i < grid.length; i++) {
			numpaths[i] = drawLowestElevPath(g, i);
			if (numpaths[i] < lowestElevationChange) {
				lowestElevationChange = numpaths[i];
				lowestElevationRow = i;
			}
		}

		return lowestElevationRow;
	}


	public int getRows()
	{

		return rows;
	}
	
	public int getCols()
	{

		return columns;
	}
}
