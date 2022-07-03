package Process;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class RightPanel extends JPanel{
	BufferedImage image;
	JSlider slider;
	
	int w;
	int h;
	
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
	
	public void bright() {
		slider = new JSlider(-50, 50, 0);
		slider.setBounds(465,10,300,80);
		slider.setMajorTickSpacing(10); 
        slider.setMinorTickSpacing(1); 
        slider.setPaintTicks(true); //눈금을 표시한다.
        slider.setPaintLabels(true); //값을 레이블로 표시한다.
        slider.setVisible(false);
//        Frame.this.add(slider);
        
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

               int bright = slider.getValue();
               System.out.println(bright);
               for (int y = 0; y < image.getHeight(); y++) {
                  for (int x = 0; x < image.getWidth(); x++) {
                     int pixel = image.getRGB(x, y);
                     Color color = new Color(pixel, true);
                     int red = color.getRed() + bright;
                     if (red > 255)
                        red = 255;
                     else if (red < 0)
                        red = 0;
                     int green = color.getGreen() + bright;
                     if (green > 255)
                        green = 255;
                     else if (green < 0)
                        green = 0;
                     int blue = color.getBlue() + bright;
                     if (blue > 255)
                        blue = 255;
                     else if (blue < 0)
                        blue = 0;

                     color = new Color(red, green, blue);
                     image.setRGB(x, y, color.getRGB());
                   
                  }
               }
               repaint();
               }
           });
       
	}
	
	public void rotateImage() {
//		flag = 1;
	
//        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
// 
//        // creating Graphics in buffered image
//        Graphics2D g2 = newImage.createGraphics();
// 
//      
//        
//        g2.drawImage(image, null, 0, 0);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
//		System.out.println("안녀엉");
		if(image!= null) {
//			if(flag==1) {
//				BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
//				Graphics2D g3 = newImage.createGraphics();
//				g3.rotate(Math.toRadians(90), w/2, h/2);
//				g3.drawImage(image, null, 0, 0);
//			}
			
			double ratio = 0.0;
	         int w = 0;
	         int h = 0;
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
	         Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);      

	         
	         if(resizeImage.getWidth(null)>resizeImage.getHeight(null)) {
	             g2.drawImage(resizeImage,0,690/2 - h/2,this);            
	          }else {
	             g2.drawImage(resizeImage,600/2 - w/2,0,this);   
	          }
		}
			
	}
	
	public JSlider getslider() {
		return slider;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setw(int w) {
		this.w = w;
	}
	
	public void seth(int h) {
		this.h = h;
	}


}
