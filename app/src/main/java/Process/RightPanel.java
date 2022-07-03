package Process;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;


public class RightPanel extends JPanel{
	BufferedImage image;
	BufferedImage bright;

	Image resizeImage;
	
	int w;
	int h;
	double ratio = 0.0;
	
	int flag = 0;
	
	RightPanel(){
		this.setLayout(null);
		this.setBounds(735, 130, 600, 690); //프레임 초기 사이즈
		this.setBackground(Color.WHITE);
		this.setVisible(true); //프레임이 보이도록
		
	}
	
	public void grayScale() throws IOException{
		for(int y = 0; y < image.getHeight(); y++) {
			   for(int x = 0; x < image.getWidth(); x++) {
			       Color colour = new Color(image.getRGB(x, y));
//			       Choose one from below
			       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
			       image.setRGB(x, y, new Color(Y, Y, Y).getRGB());
			   }
			}
	}
	
	public void invert() {
		int red, green, blue;
		for(int y = 0; y < image.getHeight(); y++) {
 		   for(int x = 0; x < image.getWidth(); x++) {
 		       Color colour = new Color(image.getRGB(x, y));
 		       red = 255 - colour.getRed();
 		       green = 255 - colour.getGreen();
 		       blue = 255 - colour.getBlue();
 		       image.setRGB(x, y, new Color(red, green, blue).getRGB());
// 		       image.setRGB(x, y, new Color(red, green, blue).getRGB());
 		   }
 		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
//		System.out.println("안녀엉");
		if(image!= null) {
		
	         System.out.println(image.getWidth(null));
	         System.out.println(image.getHeight(null));
	         
	         if(image.getWidth(null)>image.getHeight(null)) {
	            System.out.println("1");
	            ratio = ((double)600)/((double)image.getWidth(null));
	            w = (int)(image.getWidth(null) * ratio);
	            h = (int)(image.getHeight(null) * ratio);
	         }else {
	            System.out.println("2");
	            ratio = ((double)690)/((double)image.getHeight(null));
	            w = (int)(image.getWidth(null) * ratio);
	            h = (int)(image.getHeight(null) * ratio);
	         }
	         resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);      

	         
	         if(resizeImage.getWidth(null)>resizeImage.getHeight(null)) {
	             g2.drawImage(resizeImage,0,690/2 - h/2,this);            
	          }else {
	             g2.drawImage(resizeImage,600/2 - w/2,0,this);   
	          }
		}
			
	}

	
	public void setImage(BufferedImage image) {
		
		this.image = image;
	}
	public void settImage(BufferedImage image) {
		
		this.bright = image;
	}
	
	public Image getImage() {
		return resizeImage;
	}
	
	public BufferedImage getbImage() {
		return bright;
	}
	
	public JPanel getPanel() {
		return RightPanel.this;
	}
	
	
	public void setw(int w) {
		this.w = w;
	}
	
	public void seth(int h) {
		this.h = h;
	}


}
