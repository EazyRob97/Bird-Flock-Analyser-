package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.HashSet;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController {


	@FXML
	private ImageView imageView2;
	private static ImageView imageView;

	@FXML
	private Button returnBtn;

	@FXML
	private CheckMenuItem grayBtn;

	@FXML
	private CheckMenuItem redBtn;

	@FXML
	private CheckMenuItem greenBtn;

	@FXML
	private CheckMenuItem blueBtn;

	@FXML
	private CheckMenuItem bAndwBtn;
	
	@FXML
	private Button exitBtn2;
	
	@FXML
	private MenuItem openBtn;
	
	@FXML
	private CheckMenuItem originalImageBtn;
	
	@FXML
	private Image image;
	
	@FXML
	private Pane mypane;
	
	@FXML 
	private Button closeButton;

	@FXML
	private Slider bSlider;
	
	@FXML
	private Slider cSlider;
	
	@FXML
	private Labeled bValue;
	
	@FXML
	private Labeled cValue;
	
	@FXML
	private Label myLab;
	
	@FXML
	private Label myLab2;
	
	@FXML
	private CheckMenuItem detectOBtn;
	
	@FXML
	private CheckMenuItem removeOSB;
	
	@FXML
	private CheckMenuItem detectPB;
	
	@FXML
	private CheckMenuItem sequentialNumBtn;
	
	private HashSet<Integer> newSet;
	
	private int[] average;

	//private int pixelHolder[];
	private int[] dset;
	
	 int counter;

	 UnionFind uf;

	 
	 
	 
	public void initialize() {
		imageView=imageView2;
		bSlider.setMin(-1);
		bSlider.setMax(1);
		cSlider.setMin(-1);
		cSlider.setMax(1);
		
		setupSliders();
		

		// in any function
			ColorAdjust colorAdjust = new ColorAdjust();
			bSlider.valueProperty().addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						if (newValue != null) {
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							bValue.setText(String.valueOf(df.format(newValue)));
							colorAdjust.setBrightness(Double.valueOf(bValue.getText()));
							imageView.setEffect(colorAdjust);
						}
					}
			});
				
			cSlider.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						if (newValue != null) {
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							cValue.setText(String.valueOf(df.format(newValue)));
							colorAdjust.setContrast(Double.valueOf(cValue.getText()));
							imageView.setEffect(colorAdjust);
						}		
			}
			});

		
	}
	
	
	 /*
     * Method for prompting the file manager and browsing for the relevant file type which in this case is a image file type (JPG) in the tableview model on the GUI via FXML 
     */
	@FXML
	private void openImageButton(ActionEvent event) {
		FileChooser fc = new FileChooser();
		
		Stage stage = new Stage();
		fc.setInitialDirectory(new File("C:\\Users\\IoT97\\Desktop\\BSc IoT (Level 8 Hounours)\\Semester 4 (IoT Year 2)\\Data Structures & Algorithms II\\Images for CA 2019"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("JPG Files", "*.jpg","PNG Files","*.png"));
		File selectedFile = fc.showOpenDialog(stage);
		if(selectedFile != null) {
			//textArea.setText(selectedFile.getAbsolutePath());
			image = new Image(selectedFile.toURI().toString(),450,420,true,false);

			dset = new int[(int) (image.getWidth()*image.getHeight())];
			//	uf=new UnionFind((int) (image.getWidth()*image.getHeight()));
				
			imageView.setImage(image);
			
		}
	}

	
	public void setupSliders(){
		
	}
		
				
	
	/*
	 * When this Action Event is called, the Program 
	 */
	
	@FXML
	private void closeProgram(ActionEvent event) {
		System.out.println("Program Closed");
		Main.window.close();
	}
		
		// Boolean answer = Confirm.display("Title", "Are you sure you wan to exit?")

	
	
	
/*
 * This method is called, it sets the image that is passed into the system and calls the following method that is also called 'convertToGrayscale'  and sets the image into Grayscale format by making the following processes below 
 */
	@FXML
	private void grayImage(ActionEvent event) {

		imageView.setImage(convertToGrayscale(image));

	}

	
	/*
	 * This method is called, it sets the image that is passed into the system and calls the following method that is also called 'convertToGrayscale'  and sets the image into Grayscale format by making the following processes below 
	 */
	
	public static Image convertToGrayscale(Image image) {
		PixelReader pixelReader = image.getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		WritableImage grayImage = new WritableImage(width, height);
    	PixelWriter pixelWriter = grayImage.getPixelWriter();
    	for(int i = 0; i <width; i++) {

    		for(int j = 0; j <height; j++){
    			
    			int color = pixelReader.getArgb(i, j);
    			
    			//int alpha = (color >> 24 ) & 0xff;
    			int r = (color >> 16 ) & 0xff;
    			int g = (color >> 8) & 0xff;
    			int b = color & 0xff;
    			int grayL = (int) (0.299 * r + 0.587 * g + 0.114 * b);
    			
    			
    			grayL = grayL <128 ? 0 : 255; //GrayLevel adjusted to 128 between 0 & 255
			
    			
    			int newPixel = argbToPixel(255, grayL, grayL, grayL);
    			
    			pixelWriter.setArgb(i, j, newPixel);
		
			}
		}	
		return grayImage;
	}
    		

	
	/*
	 * This method is called, it sets the image that is passed into the system and calls the following method that is also called 'convertToBandWscale'  and sets the image into Black and White scale format by making the following processes below 
	 * 
	 * This method also contains several sub-methods that are called to set the the dimensions of any image that is uploaded/passed into the system accordingly and prints them into the console 
	 */
	@FXML
	private void blackAndWhiteImage(ActionEvent event) {
		
		imageView.setImage(convertToBandWscale(image));
		
		uf=new UnionFind((int) (image.getWidth()*image.getHeight())); 
		
		System.out.println("The number of pixels in this image is:"+uf.numberOfNodes());
		
		processBWImage(convertToBandWscale(image),uf);

		
		System.out.println("--------------------------------------------------------");
	
		/*
		 *	(short for loop is written to the root of every set after up 100 digits starting at index 0 )
		 *
		 */ 
		for(int i=0; i<uf.root.length; i++)
		{	              
				counter++;
	            if (counter % 100 == 0) {
	            	System.out.println(uf.root[i]+ " ");
	            }    
		       else
		            {
		            	
			System.out.print(uf.parent(i)+" "); //uf.root[i]+ " ");
	}
		}
	
		/*
		 * A String is created here to hold the no. of Sets in the Image including any other (noise) pixels that may be interpreted 
		 * as a Bird in this instance and its value is passed into the String that will be assigned as a Label when the program is run
		 */
		
		 String myEstVal = Integer.toString(uf.getNumberOfTrees());
    	 System.out.println("Estimated Value of Birds: " + uf.getNumberOfTrees());
    	 
    	 myLab2.setText(myEstVal);
    	 myLab2.setVisible(true);
		
		}

	
	/*
	 * sets the image to Black and White as mentioned in the previous comments above in which this method is apart of.
	 */
	public static Image convertToBandWscale(Image image) {
		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage bAndWImage = new WritableImage(width, height);
		PixelWriter pixelWriter = bAndWImage.getPixelWriter();
		
		for(int i = 0; i <width; i++) {
			for(int j = 0; j <height; j++){

				int color = pixelReader.getArgb(i, j);

				int alpha = ((color >> 24 ) & 0xff);
				int r = ((color >> 16 ) & 0xff);
				int g = ((color >> 8) & 0xff);
				int b = (color & 0xff);
				
				int rgb = (r + g + b) / 3;				
				
				if(rgb > 127) {
					color = (alpha<<24)|(255<<16)|(255<<8)|255;
				}
				
				else if (rgb <=127) {
					color = (alpha<<24)|(0<<16)|(0<<8)|0;
				}
				
				pixelWriter.setArgb(i, j, color);
				
			}
			
			}
		return bAndWImage;
	}

	
	/*
	 * This method is called by the public void method: 'blackAndWhiteImage' to process 
	 * the to Black and White and also consists of a sophisticated algorithm to union every 
	 * pixel that is Black to the previous pixel that was already checked  
	 */
	
	public  void processBWImage(Image image, UnionFind uf) {
		
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		
		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();
		WritableImage wI = new WritableImage(width, height);
		
		
	//Question Peter if the type pixelWriter can be written in that way!
		PixelWriter pixelWriter = wI.getPixelWriter();
		//PixelWriter pixelWriter = wI.getPixelWriter();
		pixelWriter = wI.getPixelWriter();
		
		
		for (int i = 0; i < width; i++) {
	    	for (int j = 0; j < height; j++) {
	    		int pixel = pixelReader.getArgb(i, j);
	    		int red = ((pixel >> 16) & 0xff);
				int green = ((pixel >> 8) & 0xff);
				int blue = (pixel & 0xff);
		
				if(red > 125 && green > 125 && blue > 125) {
					//pixels[x][y]=-1;
					uf.root[j*width+i]=-1;
				}
				else {
					
				}
	    	}
	    		
	    }
		
	for(int i = 0; i < width; i++) { 
		for(int j = 0; j <height; j++){
	
	int pos = j*width+i;
	if(uf.root[pos]!=-1) {
		uf.root[pos] = uf.find(pos);
	}
}
			

}

	imageView.setImage(image);	

	
	/*
	 * This specific algorithm locates starting the point of the image that is
	 * passed in to the system and checks each pixel whether its black and moves into the next pixel/node
	 * If so then it calls the union algorithm to union the coordinates or pixels that are 
	 * black to become the same color or number as you see in the console
	 */
		for(int i = 0; i <width; i++) { 
			for(int j = 0; j <height; j++){ 

				
				 if(uf.root[j*width+i] != -1) {
	    			 if(i < width - 1 && uf.root[j*width+i+1]!= -1) { //pixel to the right
	    				 uf.union(j*width+i+1, j*width+i);
	    				 
	    				/*
	    				 * This algorithm allows for the previous algorithm which checks whether each pixel
	    				 * accross the width is black to move into the next column below 
	    				 * that is after it has reached the edge/border of the image dimension
	    				 */
	    			 }
	    			 if(j < height - 1 && uf.root[(j+1)*width+i]!= -1) { //pixel below
	    				 uf.union((j+1)*width+i, j*width+i);
	    			
	    			
	    			 }
	    			 
				 }
			}
		}
		
		
		
		imageView.setImage(image);
		
//		int f=1;
		//-----------------------------------------
		
		 for(int i=0;i<uf.root.length;i++)
			 if(uf.root[i]!=-1) uf.s.add(uf.parent(i));
		 
	
		 
	 /*
	  * A new Hashset of newSet is created to hold the average number of black pixels
	  * is created to hold the newSet of Black Sets (Bird)
	  */	
		newSet = new HashSet<>();
		average = new int[uf.s.size()];
		
		int avg = 0;
		int a=0;
		int v=0;
		
		for(int bid : uf.s) {
			 average[a]=0;
			 
			for(int i=0;i<image.getWidth()*image.getHeight();i++) {
//			for(int i=0;i<uf.root.length;i++)	
//			 {
					if(uf.find(i) == bid) 
					{
						average[a]++;
					}
				}
			a++;
	}
		
			for(int k=0;k<average.length;k++) {
			    avg+=average[k];
			    
			    System.out.println("Average"+k+": "+average[k]);
			}
			    avg=avg/uf.s.size();
			    System.out.println("-----------Average"+avg);
//			System.out.println("Average"+k+": "+average[k]);
		
			    
			for(int bid2 : uf.s) {
				if (average[v] < avg/4 || average[v] > avg*4) {
			}
				else {
					newSet.add(bid2);
				}
				
				v++;
			}
			
			System.out.println("Size" + newSet.size());
			}
			//System.out.println("New Set: "+newSet);
	
	
	

	/*
	 * This Method is called to create Rectangles/Identifiers to identify the Sets/Birds that are Black
	 */
	    	 
	public void Identifier(ActionEvent event) {
		
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		int f=1;
		
		for(int bid : newSet) {
			
	    	 double top=image.getHeight(),left=image.getWidth(),bottom=0,right=0;
	    	 for(int i=0;i<uf.root.length;i++)
	 		{
	    		 
	    		 
	    		 int y=i/width; //row
	    		 int x=i%width; //col
	 			
	    		 
	 			if(uf.find(i) ==bid) {
	 				if(x<left) left=x;
	 				if(x>right) right=x;
	 				if(y<top) top=y;
	 				if(y>bottom) bottom=y;
	 			
	 			}
	 		}
		
	    	 /*
	    	  * Rectangle object that is created
	    	  */
	    	 Rectangle r=new Rectangle(left,top,right-left,bottom-top);
	    	 
	    	 /*
	    	  * A String is created here to hold the no. of Sets or Birds in the Image from the new HashSet discarding 
	    	  * any other pixels (noise) that may be interpreted as a Bird in this instance and its value is 
	    	  * passed into the String that will be assigned as a Label when the program is run 
	    	  */
	    	 String myEvaVal = Integer.toString(newSet.size());
	    	 System.out.println("Size" + uf.getNumberOfTrees());
	    	 
	    	 myLab.setText(myEvaVal);
	    	 myLab.setVisible(true);
	   
	    	 
	    	 //System.out.println("XX Bird Id: "+bid+", Bounds: "+left+", "+top+", "+bottom+", "+right);
	    	
	    	 mypane.getChildren().add(r); //mypane is a Pane object containing the imageView	    	
	    	
	    	 r.setFill(Color.TRANSPARENT);
	    	 r.setStroke(Color.GREEN);
		}
			
	    	// System.out.println("Bird Id: "+bid+", Bounds: "+r.getX()+", "+r.getY()+", "+r.getWidth()+", "+r.getHeight());	
	}
		//imageView.setImage(image);
	
	
public void sequentialNum(ActionEvent event) {
		
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		int f=1;
		
		for(int bid : newSet) {
			
	    	 double top=image.getHeight(),left=image.getWidth(),bottom=0,right=0;
	    	 for(int i=0;i<uf.root.length;i++)
	 		{
	    		 
	    		 
	    		 int y=i/width; //row
	    		 int x=i%width; //col
	 			
	    		 
	 			if(uf.find(i) ==bid) {
	 				if(x<left) left=x;
	 				if(x>right) right=x;
	 				if(y<top) top=y;
	 				if(y>bottom) bottom=y;
	 			
	 			}
	 		}
		
	
 	 /*
	  * Numerical Values that created beside the top right of each rectangle that is 
	  * created on each identified Set, hence (right+2,top)
	  */
	 Text t = new Text(right+2,top, String.valueOf(f));
	 f++;
	 mypane.getChildren().add(t);
		}
		}



@FXML
public void removeRec() {
mypane.getChildren().remove(1, mypane.getChildren().size());
}

	
	/*
	 * Method that is called to create the Pattern or Formation of the Bird Flock
	 */
@FXML
public void patternRec(ActionEvent event) {
		
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
	
	
	double top=image.getHeight(),top2 = image.getHeight(),left=image.getWidth(),left2=image.getWidth(),bottom=0,bottom2=0,right=0;
    for(int bid : uf.s) {
   	 
   	 for(int i=0;i<uf.root.length;i++)
 		{
    		 int y=i/width; //row
    		 int x=i%width; //col
    		 
    		 if(uf.find(i)==bid) {
    			if(x<left) 
    				{ 
    				left=x;
    				top2 = y;
    				
    				}
				if(x>right) {
					right=x;
					bottom2 = y;
				}
				if(y<top) top=y;
				if(y>bottom) 
					{
					bottom=y; 
                   left2=x; 
				}
    		 }
 		}
    }
       System.out.println("left:"+left+"right:"+right+"top:"+top+"bottom:"+bottom+"top2:"+top2+"bottom2:"+bottom2);
   	 Line line = new Line(left,top2,right,bottom2);
   	 Line line2 = new Line(right,bottom2,left2,bottom);
        mypane.getChildren().add(line);
        mypane.getChildren().add(line2);
}


	/*
	 * This method is called, it sets the image in to a Red Filtered format by making the following processes below 
	 */

	@FXML
	private void redImage(ActionEvent event) {
		PixelReader pixelReader = image.getPixelReader();  //imageView.getImage().getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage grayImage = new WritableImage(width, height);
		PixelWriter pixelWriter = grayImage.getPixelWriter();
		for(int i = 0; i <width; i++) {
			for(int j = 0; j <height; j++){

				int color = pixelReader.getArgb(i, j);

				int r = (color >> 16 ) & 0xff;
				int g = (color >> 8) & 0xff;
				int b = color & 0xff;
				int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

				int newPixel = argbToPixel(255, gray, 0, 0);

				pixelWriter.setArgb(i, j, newPixel);

			}
		}
		imageView.setImage(grayImage);
	}

	/*
	 * This method is called, it sets the image in to a Green Filtered format by making the following processes below 
	 */
	
	@FXML
	private void greenImage(ActionEvent event) {
		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage grayImage = new WritableImage(width, height);
		PixelWriter pixelWriter = grayImage.getPixelWriter();
		for(int i = 0; i <width; i++) {
			for(int j = 0; j <height; j++){

				int color = pixelReader.getArgb(i, j);
				
				int r = (color >> 16) & 0xff;
				int g = (color >> 8) & 0xff;
				int b = color & 0xff;
				int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

				int newPixel = argbToPixel(255, 0, gray, 0);

				pixelWriter.setArgb(i, j, newPixel);

			}
		}
		imageView.setImage(grayImage);
	}

	/*
	 * This method is called, it sets the image in to a Blue Filtered format by making the following processes below 
	 */
	
	@FXML
	private void blueImage(ActionEvent event) {
		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage grayImage = new WritableImage(width, height);
		PixelWriter pixelWriter = grayImage.getPixelWriter();
		for(int i = 0; i <width; i++) {
			for(int j = 0; j <height; j++){

				int color = pixelReader.getArgb(i, j);

				int r = (color >> 16 ) & 0xff;
				int g = (color >> 8) & 0xff;
				int b = color & 0xff;
				int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

				int newPixel = argbToPixel(255, 0, 0, gray);

				pixelWriter.setArgb(i, j, newPixel);

			}
		}
		imageView.setImage(grayImage);
	}
	
	/*
	 * This method is called, it sets the image back to the same image that was passed in originally, removing any other filtered methods that may or may not have been called prior to this method being called. 
	 */
	@FXML
	private void originalImage(ActionEvent event) {
		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage grayImage = new WritableImage(width, height);
		PixelWriter pixelWriter = grayImage.getPixelWriter();
		for(int i = 0; i <width; i++) {
			for(int j = 0; j <height; j++){

				int color = pixelReader.getArgb(i, j);

				int r = (color >> 16 ) & 0xff;
				int g = (color >> 8) & 0xff;
				int b = color & 0xff;
				int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

				int newPixel = argbToPixel(-8, -8, -8, -8);

				pixelWriter.setArgb(i, j, newPixel);

			}
		}
		imageView.setImage(image);
	}

	
	private static int argbToPixel(int alpha, int red, int green, int blue) {

		int newPixel = 0;

		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;
	}

	@FXML
	private void returnSceneChange(ActionEvent event) throws IOException {

		Stage book_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		book_stage.setScene(Main.scene1); //firstscene_scene);
		book_stage.show();
	}



}



