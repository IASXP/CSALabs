import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class that represents a picture made up of a rectangle of {@link Pixel}s
 */
public class Picture {

    /** The 2D array of pixels that comprise this picture */
	private Pixel[][] pixels;

    /**
     * Creates a Picture from an image file in the "images" directory
     * @param picture The name of the file to load
     */
    public Picture(String picture) {
        File file = new File("./images/"+picture);
        BufferedImage image;
        if (!file.exists()) throw new RuntimeException("No picture at the location "+file.getPath()+"!");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        pixels = new Pixel[image.getHeight()][image.getWidth()];
        for (int y = 0; y<pixels.length; y++) {
            for (int x = 0; x<pixels[y].length; x++) {
                int rgb = image.getRGB(x, y);
                /*
                 * For the curious - BufferedImage saves an image's RGB info into a hexadecimal integer
                 * The below extracts the individual values using bit-shifting and bit-wise ANDing with all 1's
                 */
                pixels[y][x] = new Pixel((rgb>>16)&0xff, (rgb>>8)&0xff, rgb&0xff);
            }
        }
    }

    /**
     * Creates a solid-color Picture of a given color, width, and height
     * @param red The red value of the color
     * @param green The green value of the color
     * @param blue The blue value of the color
     * @param height The height of the Picture
     * @param width The width of the Picture
     */
    public Picture(int red, int green, int blue, int height, int width) {
        pixels = new Pixel[height][width];
        for (int y = 0; y<pixels.length; y++) {
            for (int x = 0; x<pixels[y].length; x++) {
                pixels[y][x] = new Pixel(red, green, blue);
            }
        }
    }

    /**
     * Creates a solid white Picture of a given width and height
     * @param color The {@link Color} of the Picture
     * @param height The height of the Picture
     * @param width The width of the Picture
     */
    public Picture(int height, int width) {
        this(Color.WHITE, height, width);
    }

    /**
     * Creates a solid-color Picture of a given color, width, and height
     * @param color The {@link Color} of the Picture
     * @param width The width of the Picture
     * @param height The height of the Picture
     */
    public Picture(Color color, int height, int width) {
        this(color.getRed(), color.getGreen(), color.getBlue(), height, width);
    }

    /**
     * Creates a Picture based off of an existing {@link Pixel} 2D array
     * @param pixels A rectangular 2D array of {@link Pixel}s. Must be at least 1x1
     */
    public Picture(Pixel[][] pixels) {
        if (pixels.length==0 || pixels[0].length==0) throw new RuntimeException("Can't have an empty image!");
        int width = pixels[0].length;
        for (int i = 0; i<pixels.length; i++) if (pixels[i].length!=width)
            throw new RuntimeException("Pictures must be rectangles. pixels[0].length!=pixels["+i+"].length!");
        this.pixels = new Pixel[pixels.length][width];
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                this.pixels[i][j] = new Pixel(pixels[i][j].getColor());
            }
        }
    }

    /**
     * Creates a Picture based off of an existing Picture
     * @param picture The Picture to copy
     */
    public Picture(Picture picture) {
        this(picture.pixels);
    }

    /**
     * Gets the width of the Picture
     * @return The width of the Picture
     */
    public int getWidth() {
        return pixels[0].length;
    }

    /**
     * Gets the height of the Picture
     * @return The height of the Picture
     */
    public int getHeight() {
        return pixels.length;
    }

    /**
     * Gets the {@link Pixel} at a given coordinate
     * @param x The x location of the {@link Pixel}
     * @param y The y location of the {@link Pixel}
     * @return The {@link Pixel} at the given location
     */
    public Pixel getPixel(int x, int y) {
        if (x>=getWidth() || y>=getHeight() || x<0 || y<0) throw new RuntimeException("No pixel at ("+x+", "+y+")");
        return pixels[y][x];
    }

    /**
     * Sets the {@link Pixel} at a given coordinate
     * @param x The x location of the {@link Pixel}
     * @param y The y location of the {@link Pixel}
     * @param pixel The new {@link Pixel}
     */
    public void setPixel(int x, int y, Pixel pixel) {
        if (x>=getWidth() || y>=getHeight() || x<0 || y<0) throw new RuntimeException("No pixel at ("+x+", "+y+")");
        if (pixel==null) throw new NullPointerException("Pixel is null"); //guard is required because pixel's value isn't used in this method
        pixels[y][x] = pixel;
    }

    /**
     * Opens a {@link PictureViewer} to view this Picture
     * @return the {@link PictureViewer} viewing the Picture
     */
    public PictureViewer view() {
        return new PictureViewer(this);
    }

    /**
     * Return the 2D array of pixels that comprise this Picture
     * You can save a reference to a Picture's pixels with the following:
     * Pixel[][] pixels = pic.getPixels(); //for a Picture object called pic
     * @return 2D array of pixels that make up this Picture
     */
    public Pixel[][] getPixels() {
    		return pixels;
    }

    /********************************************************
     *************** STUDENT METHODS BELOW ******************
     ********************************************************/

    /** remove all blue tint from a picture */
    public void zeroBlue()
    {
    	for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[i].length; f++)
            {
                pixels[i][f].setBlue(0);
            }

        }
    }

    /** remove everything BUT blue tint from a picture */
    public void keepOnlyBlue()
    {
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                pixels[i][f].setRed(0);
                pixels[i][f].setGreen(0);
            }
        }
    }

    /** invert a picture's colors */
    public void negate()
    {
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                pixels[i][f].setRed(255-pixels[i][f].getRed());
                pixels[i][f].setGreen(255-pixels[i][f].getGreen());
                pixels[i][f].setBlue(255-pixels[i][f].getBlue());
            }
        }
    }

    /** simulate the over-exposure of a picture in film processing */
    public void solarize(int threshold)
    {
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                if (pixels[i][f].getRed() < threshold)
                {
                    pixels[i][f].setRed(255 - pixels[i][f].getRed());
                }
                else if (pixels[i][f].getGreen() < threshold)
                {
                    pixels[i][f].setGreen(255 - pixels[i][f].getGreen());
                }
                else if (pixels[i][f].getBlue() < threshold)
                {
                    pixels[i][f].setBlue(255 - pixels[i][f].getBlue());
                }
            }
        }
    }

    /** convert an image to grayscale */
    public void grayscale()
    {
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                int avg = (pixels[i][f].getRed() + pixels[i][f].getGreen() + pixels[i][f].getBlue())/3;
                pixels[i][f].setRed(avg);
                pixels[i][f].setGreen(avg);
                pixels[i][f].setBlue(avg);

            }
        }
    }

	/** change the tint of the picture by the supplied coefficients */
	public void tint(double red, double blue, double green)
	{
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                int r = (int) (pixels[i][f].getRed() * red);
                int g = (int) (pixels[i][f].getGreen() * green);
                int b = (int) (pixels[i][f].getBlue() * blue);
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                pixels[i][f].setRed(r);
                pixels[i][f].setGreen(g);
                pixels[i][f].setBlue(b);
            }
        }
	}
	
	/** reduces the number of colors in an image to create a "graphic poster" effect */
    public void posterize(int span)
    {
        //TODO
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {

                pixels[i][f].setRed(pixels[i][f].getRed() / span * span);

                pixels[i][f].setGreen(pixels[i][f].getGreen() / span * span);

                pixels[i][f].setBlue(pixels[i][f].getBlue() / span * span);

            }
        }
    }

    /** mirror an image about a vertical midline, left to right */
    public void mirrorVertical()
    {
		Pixel leftPixel  = null;
		Pixel rightPixel = null;

		int width = pixels[0].length;

		for (int i = 0; i < pixels.length; i++)
		{
			for (int f = 0; f < width / 2; f++)
			{
				leftPixel  = pixels[i][f];
				rightPixel = pixels[i][(width - 1) - f];

				rightPixel.setColor(leftPixel.getColor());
			}
		}
    }

    /** mirror about a vertical midline, right to left */
    public void mirrorRightToLeft()
    {
        //TODO
        Pixel leftPixel  = null;
        Pixel rightPixel = null;

        int width = pixels[0].length;

        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = width / 2; f < width; f++)
            {
                leftPixel  = pixels[i][f];
                rightPixel = pixels[f][(width - 1) - i];

                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** mirror about a horizontal midline, top to bottom */
    public void mirrorHorizontal()
    {
        Pixel leftPixel  = null;
        Pixel rightPixel = null;

        int width = pixels[0].length;

        for (int i = 0; i < pixels.length / 2; i++)
        {
            for (int f = 0; f < width; f++)
            {
                leftPixel  = pixels[i][f];
                rightPixel = pixels[(pixels.length - 1) - i][f];
                rightPixel.setColor(leftPixel.getColor());
            }
        }

    }

    /** flip an image upside down about its bottom edge */
    public void verticalFlip()
    {
        Pixel temp;
        for (int i = 0; i < pixels.length / 2; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                temp = pixels[i][f];
                pixels[i][f] = pixels[pixels.length - 1 - i][f];
                pixels[pixels.length - 1 - i][f] = temp;
            }
        }
    }

    /** fix roof on greek temple */
    public void fixRoof()
    {
        for (int i = 94; i > 29; i--)
        {
            for (int f = 17; f < 276; f++)
            {
                pixels[i][276 * 2 - f] = pixels[i][f];

            }
        }
    }

    /** detect and mark edges in an image */
    public void edgeDetection(int dist)
    {
        for (int i = 0; i < pixels.length - 1; i++)
        {
            for (int f = 0; f < pixels[0].length - 1; f++)
            {

                if (pixels[i][f].colorDistance(pixels[i][f + 1].getColor()) <= dist)
                {

                    if (pixels[i][f].colorDistance(pixels[i + 1][f].getColor()) >= dist)
                    {
                        pixels[i][f].setColor(0, 0, 0);
                    }
                    else
                    {
                        pixels[i][f].setColor(255, 255, 255);
                    }

                }
                else
                {
                    pixels[i][f].setColor(0, 0, 0);

                }


            }

        }
    }


	/** copy another picture's pixels into this picture, if a color is within dist of param Color */
	public void chromakey(Picture other, Color color, int dist)
	{
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {

                if (pixels[i][f].colorDistance(color) <= dist)
                {

                    pixels[i][f].setColor(other.pixels[i][f].getColor());

                }

            }
        }
	}

	/** steganography encode (hide the message in msg in this picture) */
	public void encode(Picture msg)
	{
        for (int i = 0; i < this.pixels.length; i++)
        {
            for (int f = 0; f < this.pixels[0].length; f++)
            {
                if (this.pixels[i][f].getRed() % 2 == 1)
                {
                    this.pixels[i][f].setRed(this.pixels[i][f].getRed() - 1);
                }
            }
        }
        for (int i = 0; i < pixels.length; i++)
        {
            for (int f = 0; f < pixels[0].length; f++)
            {
                if (msg.pixels[i][f].colorDistance(Color.BLACK) <= 40)
                {

                    this.pixels[i][f].setRed(this.pixels[i][f].getRed() + 1);
                }
            }
        }

    }

	/** steganography decode (return a new Picture containing the message hidden in this picture) */
	public Picture decode()
	{
        Picture picture = new Picture(this.pixels.length, this.pixels[0].length);
        for (int i = 0; i < pixels.length; i++) {
            for (int f = 0; f < pixels[0].length; f++) {

                if (this.pixels[i][f].getRed() % 2 == 1) {
                    picture.pixels[i][f].setColor(Color.BLACK);
                } else {
                    picture.pixels[i][f].setColor(Color.WHITE);
                }

            }
        }
        return picture;
	}
	/** perform a simple blur using the colors of neighboring pixels */
	public Picture simpleBlur()
	{
        Picture picture = new Picture(this.pixels.length, this.pixels[0].length);
        for (int i = 1; i < pixels.length - 1; i++) {
            for (int f = 1; f < pixels[0].length - 1; f++) {
                picture.pixels[i][f].setRed((this.pixels[i - 1][f].getRed() + this.pixels[i + 1][f].getRed() + this.pixels[i][f - 1].getRed() + this.pixels[i][f + 1].getRed())/4);
                picture.pixels[i][f].setGreen((this.pixels[i - 1][f].getGreen() + this.pixels[i + 1][f].getGreen() + this.pixels[i][f - 1].getGreen() + this.pixels[i][f + 1].getGreen())/4);
                picture.pixels[i][f].setBlue((this.pixels[i - 1][f].getBlue() + this.pixels[i + 1][f].getBlue() + this.pixels[i][f - 1].getBlue() + this.pixels[i][f + 1].getBlue())/4);

            }
        }
        return picture;

	}

	/** perform a blur using the colors of pixels within radius of current pixel */
	public Picture blur(int radius)
	{
        Picture picture = new Picture(this.pixels.length, this.pixels[0].length);
        for (int i = 1; i < pixels.length; i++)
        {
            for (int f = 1; f < pixels[0].length; f++)
            {
                if ((i - radius >= 0) && (f - radius >= 0) && (i + radius < pixels.length) && (f - radius < pixels[0].length))
                {
                    int redTot = 0;
                    int greenTot = 0;
                    int blueTot = 0;
                    for (int j = 1; j <= radius; j++)
                    {
                        if (i + j < this.pixels.length && f + j < this.pixels[0].length)
                        {
                            redTot = redTot + this.pixels[i - j][f].getRed() + this.pixels[i + j][f].getRed() + this.pixels[i][f - j].getRed() + this.pixels[i][f + j].getRed();
                            greenTot = greenTot + this.pixels[i - j][f].getGreen() + this.pixels[i + j][f].getGreen() + this.pixels[i][f - j].getGreen() + this.pixels[i][f + j].getGreen();
                            blueTot = blueTot + this.pixels[i - j][f].getBlue() + this.pixels[i + j][f].getBlue() + this.pixels[i][f - j].getBlue() + this.pixels[i][f + j].getBlue();
                        }
                    }
                    picture.pixels[i][f].setRed(redTot / (radius * 4));
                    picture.pixels[i][f].setGreen(greenTot / (radius * 4));
                    picture.pixels[i][f].setBlue(blueTot / (radius * 4));
                }

            }

        }
        return picture;
	}
	
	/**
	 * Simulate looking at an image through a pane of glass
	 * @param dist the "radius" of the neighboring pixels to use
	 * @return a new Picture with the glass filter applied
	 */
	public Picture glassFilter(int dist) 
	{
		//TODO
		
		return null; //REPLACE
	}
}
