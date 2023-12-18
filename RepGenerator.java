import javafx.scene.image.ImageView;

public class RepGenerator extends SceneAction {
	private static String defaultStrLeft = "Gain the peoples ire";
	private static String defaultStrRight = "Gain the peoples admiration";
	private static String defaultStrMiddle = "Will you help the people?";
	
	private static String emptyStrMiddle = "There are no more Representatives today.";
	
	private static int textRNG;
	private static int nameRNG;
	private static int pastRepRNG;
	
	public RepGenerator() {
		super();
	}
	
	public static void generateRepEvent(int numReps, int rng) {
		
		//Generating new event, check if we should display none left
		if(numReps == 0) {
			setEmptyRepEvent();
		}
		else {
			//Create a rep image, randomly
			createRepImage(getRepImage(), rng);
			
			//Generates text and sets it
			generateTextEvent();
			
			//Generates representative name and sets it
			generateRepName();
		}
	}
	
	public static void generateTextEvent() {
		textRNG = EventGenerator.RNG(100);
		TextReader.readText("prompts.txt", 1, textRNG);
	}
	
	public static void createRepImage(ImageView imgView, int imgNumRNG) {
		String imgStr;
		
		//prevent rep images generating the same twice in a row
		if(imgNumRNG == pastRepRNG) {
			while(imgNumRNG == pastRepRNG) {
				imgNumRNG = EventGenerator.RNG(4);
			}
		}
		
		if(imgNumRNG == 0) {
			imgStr = "defaultRep.png";
			ImageAdder.addImage(imgView, imgStr ,false);
		}
		else if(imgNumRNG == 1) {
			imgStr = "repImage1.png";
			ImageAdder.addImage(imgView, imgStr ,false);
		}
		else if(imgNumRNG == 2){
			imgStr = "repImage2.png";
			ImageAdder.addImage(imgView, imgStr ,false);
		}
		else if(imgNumRNG == 3){
			imgStr = "repImage3.png";
			ImageAdder.addImage(imgView, imgStr ,false);
		}
		else if(imgNumRNG == 4){
			imgStr = "repImage4.png";
			ImageAdder.addImage(imgView, imgStr ,false);
		}
		
		pastRepRNG = imgNumRNG;
	}
	
	public static void generateRepName() {
		//Call read text with nameRNG to be replaced
		TextReader.readText("Names.txt", 0, nameRNG);
	}
	
	public static void setEmptyRepEvent() {
		//Create and set default empty text areas when no more reps
		setTextAreas("", emptyStrMiddle, "");
		
		//Set the rep name
		setRepNameDefault();
		
		//Set no image
		createRepImage(getRepImage(), 0);
	}
	
	public static void setDefaultRepEvent() {
		//Create and set default rep text areas with instructions
		setTextAreas(defaultStrLeft, defaultStrMiddle, defaultStrRight);
		
		//Set the rep name
		setRepNameDefault();
		
		//Set no image
		createRepImage(getRepImage(), 0);
	}
	
	
}
