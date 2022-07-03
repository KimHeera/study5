package Process;

import java.awt.Image;
import java.io.File;

import javax.swing.JFileChooser;

public class Data {
	JFileChooser fileComponent = new JFileChooser();
	File file;
	Image image;
	
	Data(){
		
	}
	
	public JFileChooser getfilechooser() {
		return fileComponent ;
	}
	
	public void setfilechooser(JFileChooser fileComponent) {
		this.fileComponent = fileComponent;
	}
	
	public void setfile(File file) {
		this.file = file;
	}
	
	public File getfile() {
		return file;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

}
