//Software Engineering Team #7
//View File for Laser Tag Project

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

//adds button and image to program
class View extends JPanel
{
	BufferedImage loadUpImage;
	Model model;
	//add start up picture here?
	View(Controller c, Model m)
    {
        model = m;
        c.setView(this);
        try
        {
            this.loadUpImage = ImageIO.read(new File("logo.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
	public BufferedImage scale(BufferedImage src, int w, int h)
	{
		BufferedImage img = 
				new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		int x, y;
		int ww = src.getWidth();
		int hh = src.getHeight();
		int[] ys = new int[h];
		for (y = 0; y < h; y++)
			ys[y] = y * hh / h;
		for (x = 0; x < w; x++) {
			int newX = x * ww / w;
			for (y = 0; y < h; y++) {
				int col = src.getRGB(newX, ys[y]);
				img.setRGB(x, y, col);
			}
		}
		return img;
	}
	public void paintComponent(Graphics g)
   	{
        	g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//scale(loadUpImage, 3487, 2221);
		g.drawImage(this.scale(loadUpImage, 1200, 700), 0, 0, null);
	}
	
	int startUpTimer = 0;
	public void update()
	{
		startUpTimer++;
		if (startUpTimer > 10)
		{
			//code to switch to play entry screen goes here
		}
	}
}
