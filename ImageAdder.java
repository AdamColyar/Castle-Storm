import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ImageAdder {

	public static void addImageStats(ImageView imageObj, String imageTxt) {
		
		//Try catch block for adding selected image
		FileInputStream input;
		try {
			input = new FileInputStream(imageTxt);
			Image image = new Image(input);
			imageObj.setImage(image);
			imageObj.setPreserveRatio(true);
			imageObj.setFitWidth(100);
			imageObj.setFitHeight(30); 
			imageObj.setFitWidth(30);  
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find" + imageTxt);
			imageObj.setImage(null);
		}
	}
	public static void addImage(ImageView imageObj, String imageTxt, boolean preserve) {
		
		//Try catch block for adding selected image
		FileInputStream input;
		try {
			input = new FileInputStream(imageTxt);
			Image image = new Image(input);
			imageObj.setImage(image);
			if(preserve == false) {
				imageObj.setPreserveRatio(false);
			}
			else {
				imageObj.setPreserveRatio(true);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find" + imageTxt);
			imageObj.setImage(null);
		}
	}
	
	public static Image returnImage(ImageView imageObj, String imageTxt) {
		
		//Try catch block for adding selected image
		FileInputStream input;
		try {
			input = new FileInputStream(imageTxt);
			Image image = new Image(input);
			imageObj.setImage(image);
			imageObj.setPreserveRatio(false);
			
			return image;
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("Couldn't find" + imageTxt);
			imageObj.setImage(null);
		}
		
		return null;
	}
	
	public static BackgroundImage addBackgroundImage(Image image1) {
        return new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
    }
}
