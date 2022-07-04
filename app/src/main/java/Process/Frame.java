package Process;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame{
	RightPanel right;
	LeftPanel left;
	Data data;
	
	static JFileChooser chooser;
	BufferedImage image1;
	BufferedImage image2;
	BufferedImage image3;
	Image image;
	
	int flag = 0;
	int x , y, w, h;
	int cropf = 0;
	int click = 0;
	
	JPanel homePanel = new JPanel();
	JPanel btnPanel = new JPanel();
	
	JSlider slider;
	
	
	JButton imbtn = new JButton();
	JButton outbtn = new JButton();
	JButton graybtn = new JButton();
	JButton resetbtn = new JButton();
	JButton bdbtn = new JButton();
	JButton cropbtn = new JButton();
	JButton inversionbtn = new JButton();
	
	Frame(){
		this.setLayout(null);
		this.setTitle("Image Processing"); //타이틀
		this.setSize(1400, 1000); //프레임 초기 사이즈
		this.setLocationRelativeTo(null);
		this.setResizable(true); //프레임크기 조절 가능
		this.setVisible(true); //프레임이 보이도록
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x버튼 활성
	}
	
	
	public void frameMake() {
		
		right = new RightPanel();
		homePanel.add(right);
		
		left = new LeftPanel();
		homePanel.add(left);
		
//		right.bright();
		
	
//버튼들
		//사진 불러오는 버튼
		imbtn.setText("import");
		imbtn.setFont(new Font("Arial", Font.BOLD, 15));
		imbtn.setBounds(65, 10, 100, 80);
		imbtn.addActionListener(clk);
		imbtn.setVisible(true);
		btnPanel.add(imbtn);
		
		//Gray scale
		graybtn.setText("Gray scale");
		graybtn.setFont(new Font("Arial", Font.BOLD, 15));
		graybtn.setBounds(265, 10, 100, 80);
		graybtn.addActionListener(clk);
		graybtn.setVisible(true);
		btnPanel.add(graybtn);
		
		//밝기 조절 버튼 
		bdbtn.setText("brightness");
		bdbtn.setFont(new Font("Arial", Font.BOLD, 15));
		bdbtn.setBounds(365, 10, 100, 80);
		bdbtn.addActionListener(clk);
		bdbtn.setVisible(true);
		btnPanel.add(bdbtn);
		

		slider = new JSlider(-50, 50, 0);
		slider.setBounds(465,10,300,80);
		slider.setMajorTickSpacing(10); 
        slider.setMinorTickSpacing(1); 
        slider.setPaintTicks(true); //눈금을 표시한다.
        slider.setPaintLabels(true); //값을 레이블로 표시한다.
        slider.setVisible(false);
        homePanel.add(slider);
		
		//자르기 버튼
		cropbtn.setText("crop");
		cropbtn.setFont(new Font("Arial", Font.BOLD, 15));
		cropbtn.setBounds(165, 10, 100, 80);
		cropbtn.addActionListener(clk);
		cropbtn.setVisible(true);
		btnPanel.add(cropbtn);
		
		//색반전
		inversionbtn.setText("inversion");
		inversionbtn.setFont(new Font("Arial", Font.BOLD, 15));
		inversionbtn.setBounds(465, 10, 100, 80);
		inversionbtn.addActionListener(clk);
		inversionbtn.setVisible(true);
		btnPanel.add(inversionbtn);
		
		//리셋 버튼
		resetbtn.setText("reset");
		resetbtn.setFont(new Font("Arial", Font.BOLD, 15));
		resetbtn.setBounds(1135, 10, 100, 80);
		resetbtn.addActionListener(clk);
		resetbtn.setVisible(true);
		btnPanel.add(resetbtn);
		
		//저장 버튼
		outbtn.setText("save");
		outbtn.setFont(new Font("Arial", Font.BOLD, 15));
		outbtn.setBounds(1235, 10, 100, 80);
		outbtn.addActionListener(clk);
		outbtn.setVisible(true);
		btnPanel.add(outbtn);
		
//버튼 넣을 패널
		btnPanel.setLayout(null);
		btnPanel.setBounds(0, 0, 1400, 100); //프레임 초기 사이즈
		btnPanel.setBackground(Color.GRAY);
		btnPanel.setVisible(true); //프레임이 보이도록
		homePanel.add(btnPanel);
		
		
		
		
//메인 프레임에 얹을 홈 패널
		homePanel.setLayout(null);
		homePanel.setBounds(0, 0, 1400, 900); //프레임 초기 사이즈
		homePanel.setBackground(Color.LIGHT_GRAY);
		homePanel.setVisible(true); //프레임이 보이도록
		this.add(homePanel);
		
//메인 프레임		
		
	}
	
	ActionListener clk = new ActionListener() {
		public JFileChooser chooser = new JFileChooser();
//		chooser = new JFileChooser();
		
		@Override
    	public void actionPerformed(ActionEvent e) {
    		String cmd = e.getActionCommand();
//    		System.out.println(cmd);
    		switch(cmd) {
    		case "import": {
    			
    			System.out.println("뿅");
    			
    			
				
				chooser.setMultiSelectionEnabled(false);
				
				int result = chooser.showOpenDialog(Frame.this);
				
				if (result == JFileChooser.APPROVE_OPTION) {
               	    try {
               	    	
               	    	image1 = ImageIO.read(chooser.getSelectedFile());
               	    	image2 = ImageIO.read(chooser.getSelectedFile());
               	    	
               	    	
               	    	left.setImage(image1);
               	    	right.setImage(image2);
               	  
               	    	
               	    	left.repaint();
               	    	right.repaint();
               	    } catch (IOException ex) {
               	        ex.printStackTrace();
               	    }
				}
				
				
			
				break;
    			
    		}
    		case "Gray scale" : {
    			System.out.println("이건 흑백");
    			try {
					right.grayScale();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				right.repaint();
				break;
    		}
    		
    		case "reset" : {
    			System.out.println("이건 리셋");
    			try {
    				image3 = ImageIO.read(chooser.getSelectedFile());
    				right.setImage(image3);
    				
           	    } catch (IOException ex) {
           	        ex.printStackTrace();
           	    }
    			right.repaint();
    			break;
    		}
    		case "save" : {
    			image =right.getImage();
    			try {
    	               image= new Robot().createScreenCapture(new Rectangle(right.getPanel().getLocationOnScreen().x, right.getPanel().getLocationOnScreen().y,right.getPanel().getWidth(), right.getPanel().getHeight()));
    	               } catch (AWTException e1) {
    	               e1.printStackTrace();
    	               } 
    	            chooser = new JFileChooser();
    	            chooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
    	            chooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
    	            
    	            chooser.setMultiSelectionEnabled(false);
    	            chooser.setVisible(true);
    	            
    	            int result = chooser.showSaveDialog(Frame.this);
    	            if (result == JFileChooser.APPROVE_OPTION) {
    	               
    	               if(chooser.getFileFilter().toString().contains(".png")) {
    	                  String fileName = chooser.getSelectedFile().getPath() + ".png";
    	                  File pngFile = new File(fileName);
    	                  try {
    	                     ImageIO.write((RenderedImage) image, "png", pngFile);
    	                  } catch (IOException e1) {
    	                     e1.printStackTrace();
    	                  }
    	               }
    	               else {
    	                  String fileName = chooser.getSelectedFile().getPath() + ".jpg";
    	                  File imgFile = new File(fileName);
    	                  
    	                  try {
    	                     ImageIO.write((RenderedImage) image, "jpg", imgFile);
    	                  } catch (IOException e1) {
    	                     e1.printStackTrace();
    	                  }
    	               }
    	            }
    			break;
    		}
    		case "brightness" :{
    			System.out.println("이건 밝기조절");
    			if(flag == 0) {
    				flag = 1;
    				slider.setValue(0);
    				slider.setVisible(true);
    				inversionbtn.setVisible(false);
    				
    				slider.addChangeListener(new ChangeListener() {
    		            public void stateChanged(ChangeEvent e) {
    		            	try {
    							image3 = ImageIO.read(chooser.getSelectedFile());
    							  int b = slider.getValue();
    				                System.out.println(image3);
    				                for (int y = 0; y < image3.getHeight(); y++) {
    				                   for (int x = 0; x < image3.getWidth(); x++) {
    				                      int pixel = image3.getRGB(x, y);
    				                      Color color = new Color(pixel, true);
    				                      int red = color.getRed() + b;
    				                      if (red > 255)
    				                         red = 255;
    				                      else if (red < 0)
    				                         red = 0;
    				                      int green = color.getGreen() + b;
    				                      if (green > 255)
    				                         green = 255;
    				                      else if (green < 0)
    				                         green = 0;
    				                      int blue = color.getBlue() + b;
    				                      if (blue > 255)
    				                         blue = 255;
    				                      else if (blue < 0)
    				                         blue = 0;

    				                      color = new Color(red, green, blue);
    				                      image3.setRGB(x, y, color.getRGB());
    				                   
    				                  }
    				               }
    				                right.setImage(image3);
    				                right.repaint();
    						} catch (IOException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    		               }
    		           });
    			}
    			else if(flag == 1) {
    				flag = 0;
    				inversionbtn.setVisible(true);
    				slider.setVisible(false);
    			}
    			
    			break;
    		}
    		case "crop" : {
    			System.out.println("이건 crop");
    			
    			if(cropf==0) {
    				cropf=1;
					cropImage();
    				break;
    			}
    			else if(cropf ==1) {
    				cropf = 0;
    				image2 = image2.getSubimage(x, y, w, h);
    				right.setImage(image2);
    				right.repaint();
    			}
    			break;
    		}
    		case "inversion" : {
    			slider.setVisible(false);
    			
    			right.invert();
    			right.repaint();
    			
    		}
    		}
    	}
	};
	
	public void cropImage(){
		left.getPanel().addMouseListener(new MyMouseListener());
		left.getPanel().addMouseMotionListener(new MyMouseListener());
	}
	
	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX() * image2.getWidth() / 599;
			y =  e.getY() * image2.getHeight() / 689;

			System.out.println("클릭좌표 : " + x + "  " + y);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			w =e.getX()* image2.getWidth() / 599 - x;
			h =e.getY()* image2.getHeight() / 689 - y;

			System.out.println("마지막  좌표 : " + w + "  " + h);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame();
		frame.frameMake();

	}
	

}
