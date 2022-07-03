package Process;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class LeftPanel extends JPanel{
	BufferedImage image;
	Image resizeImage;
	
	double ratio = 0.0;
    int w = 0;
    int h = 0;

	LeftPanel(){
		this.setLayout(null);
		this.setBounds(65, 130, 600, 690); //프레임 초기 사이즈
		this.setBackground(Color.WHITE);
		this.setVisible(true); //프레임이 보이도록
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Graphics2D g2=(Graphics2D)g;

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
	         
	         if(image.getWidth(null)>image.getHeight(null)) {
	             g2.drawImage(resizeImage,0,690/2 - h/2,this);            
	          }else {
	             g2.drawImage(resizeImage,600/2 - w/2,0,this);   
	          }
		}
			
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	
	public JPanel getPanel() {
		return this;
	}
	
	public Image getResize() {
		return resizeImage;
	}
	
	public int getw() {
		return w;
	}
	
	public int geth() {
		return h;
	}

}
