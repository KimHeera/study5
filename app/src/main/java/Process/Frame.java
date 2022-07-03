package Process;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame{
	RightPanel right;
	LeftPanel left;
	Data data;
	
	JFileChooser chooser;
	BufferedImage image1;
	BufferedImage image2;
	
	int flag = 0;
	
	JPanel homePanel = new JPanel();
	JPanel btnPanel = new JPanel();
	
	
	JButton imbtn = new JButton();
	JButton outbtn = new JButton();
	JButton graybtn = new JButton();
	JButton resetbtn = new JButton();
	JButton rotatebtn = new JButton();
	JButton bdbtn = new JButton();
	JButton cropbtn = new JButton();
	
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
		
		
		right.bright();
		homePanel.add(right.getslider());
	
//버튼들
		//사진 불러오는 버튼
		imbtn.setText("import");
		imbtn.setFont(new Font("Arial", Font.BOLD, 15));
		imbtn.setBounds(65, 10, 100, 80);
		imbtn.addActionListener(clk);
		imbtn.setVisible(true);
		btnPanel.add(imbtn);
		
		//회전 버튼
		rotatebtn.setText("rotate");
		rotatebtn.setFont(new Font("Arial", Font.BOLD, 15));
		rotatebtn.setBounds(165, 10, 100, 80);
		rotatebtn.addActionListener(clk);
		rotatebtn.setVisible(true);
		btnPanel.add(rotatebtn);
		
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
		
		//자르기 버튼
		cropbtn.setText("crop");
		cropbtn.setFont(new Font("Arial", Font.BOLD, 15));
		cropbtn.setBounds(465, 10, 100, 80);
		cropbtn.addActionListener(clk);
		cropbtn.setVisible(true);
		btnPanel.add(cropbtn);
		
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
		homePanel.setBounds(0, 0, 1400, 1000); //프레임 초기 사이즈
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
    				image2 = ImageIO.read(chooser.getSelectedFile());
    				right.setImage(image2);
    				right.repaint();
           	    } catch (IOException ex) {
           	        ex.printStackTrace();
           	    }
    			break;
    		}
    		case "save" : {
    			break;
    		}
    		case "rotate" : {
    			System.out.println("이건 rotate");
//    			right.rotateImage();
//    			right.repaint();
    			break;
    		}
    		case "brightness" :{
    			System.out.println("이건 밝기조절");
    			
    			if(flag == 0) {
    				flag = 1;
    				cropbtn.setVisible(false);
    				right.getslider().setVisible(true);
    				right.repaint();
    			}
    			else {
    				cropbtn.setVisible(true);
    				right.getslider().setVisible(false);
    				flag = 0;
    			}
    				
    			
    			right.repaint();
    			break;
    		}
    		case "crop" : {
    			System.out.println("이건 crop");
    			
    			break;
    		}
    		}
    	}
	};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame();
		frame.frameMake();

	}
	

}
