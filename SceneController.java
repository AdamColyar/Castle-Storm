import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController extends Application {
	
	public static final int WINSIZE_X = 1280, WINSIZE_Y = 800;
	public static Stage mainStage;
	public static Scene sceneDefault;
	public static Scene sceneOnAction;
	public static Scene sceneOnUpgrade;
	public static Scene sceneGameOver;
	
	    public static void main(String[] args) {
	        Application.launch(args);
	    }
	    
	    public static Stage getStage() {
	    	return mainStage;
	    }
	    
	    public static Scene getSceneDefault() {
	    	return sceneDefault;
	    }

	    public static Scene getSceneOnAction() {
	    	return sceneOnAction;
	    }
	    
	    public static Scene getSceneOnUpgrade() {
	    	return sceneOnUpgrade;
	    }
	    
	    public static Scene getSceneOnGameOver() {
	    	return sceneGameOver;
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	    	//  Initialize the Stage, groups, and scene
	    	mainStage = primaryStage;
	        mainStage.setTitle("Castle Stormy!");	
	        
	        //Objects of classes to be used as the scene root
	        SceneDefault rootDefault = new SceneDefault();
	        SceneAction rootAction = new SceneAction();
	        SceneUpgrade rootUpgrade = new SceneUpgrade();
	        SceneGameOver rootGameOver = new SceneGameOver();

	        //Creates the different scenes for buttons to change to
	        sceneDefault = new Scene(rootDefault, WINSIZE_X, WINSIZE_Y);
	        sceneOnAction = new Scene(rootAction, WINSIZE_X, WINSIZE_Y);
	        sceneOnUpgrade = new Scene(rootUpgrade, WINSIZE_X, WINSIZE_Y);
	        sceneGameOver = new Scene(rootGameOver, WINSIZE_X, WINSIZE_Y);
	        
	        //Display scene
	        mainStage.setScene(sceneDefault);
	        mainStage.setFullScreenExitHint("");
	        mainStage.setFullScreen(true);
	        mainStage.show();
	     
	    }
}
