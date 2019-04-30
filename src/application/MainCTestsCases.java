//import javafx.event.ActionEvent;
//import javafx.scene.image.Image;
//import javafx.scene.image.PixelReader;
//import javafx.scene.image.PixelWriter;
//import javafx.scene.image.WritableImage;
//
////package application;
////
////import static org.junit.jupiter.api.Assertions.*;
////
////import java.util.Arrays;
////
////import org.junit.jupiter.api.Test;
////
////class MainCTestsCases {
////
////	@Test
////	void test() {
////		fail("Not yet implemented");
////	}
////
////	
////	 @org.junit.Test
////	    public void sortByNameASC() {
////	        quickSort("Name", UnionFind .SORT_ASC);
////	        assert (Arrays.equals(characters, backCharacters));
////	    }
////
////	    @org.junit.Test
////	    public void sortByNameDESC() {
////	        quickSort("Name", QuickSort.SORT_DESC);
////	        assert (characters[0].getName().equals(backCharacters[backCharacters.length - 1].getName()));
////	        assert (characters[characters.length-1].getName().equals(backCharacters[0].getName()));
////	    }
//
//
////@org.junit.Test
////public void grayImage(ActionEvent event) {
////
////		imageView.setImage(convertToGrayscale(image));
////
////	public static Image convertToGrayscale(Image image) {
////		PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();
////
////		int width = (int) image.getWidth();
////		int height = (int) image.getHeight();
////		WritableImage grayImage = new WritableImage(width, height);
////    	PixelWriter pixelWriter = grayImage.getPixelWriter();
////    	for(int i = 0; i <width; i++) {
////
////    		for(int j = 0; j <height; j++){
////    			
////    			int color = pixelReader.getArgb(i, j);
////    			
////    			//int alpha = (color >> 24 ) & 0xff;
////    			int r = (color >> 16 ) & 0xff;
////    			int g = (color >> 8) & 0xff;
////    			int b = color & 0xff;
////    			int grayL = (int) (0.299 * r + 0.587 * g + 0.114 * b);
////    			
////    			
////    			grayL = grayL <128 ? 0 : 255; //GrayLevel adjusted to 128 between 0 & 255
////			
////    			
////    			int newPixel = argbToPixel(255, grayL, grayL, grayL);
////    			
////    			pixelWriter.setArgb(i, j, newPixel);
////		
////			}
////		}	
////		return grayImage;
////	}
