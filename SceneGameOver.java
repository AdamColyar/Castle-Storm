import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SceneGameOver extends VBox {
	private Button restartGameButton;
	private static Label gameOverLabel;
	private VBox sceneGameOver;
	private GridPane buttonContainer;
	private GridPane emptyContainerTop;
	private HBox emptyContainerBot;
	private static String lossCause = "";
	
	public SceneGameOver() {
		//Default pane
		sceneGameOver = new VBox();
		
		//Try catch for creating bimage
		try {
        	String bImageString = "bImageGameOver.jpg";
            ImageView bImageView = new ImageView();
            Image bImage = ImageAdder.returnImage(bImageView, bImageString);
            sceneGameOver.setBackground(new Background(ImageAdder.addBackgroundImage(bImage)));
        }
        catch(NullPointerException e) {
        	sceneGameOver.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        }
		
		//Create vertical empty containers for spacing
		emptyContainerTop = new GridPane();
		emptyContainerTop.setMinSize(500, 310);
		emptyContainerBot = new HBox(345);
		emptyContainerBot.setMinSize(500, 500);
		buttonContainer = new GridPane();
		
		//Create label
		System.out.println(lossCause);
		gameOverLabel = new Label(lossCause);
		gameOverLabel.setStyle("-fx-text-fill: red; -fx-font-size:30");
 
		//Restart button styling and handling
        restartGameButton = new Button("Begin Again");
        restartGameButton.setMinSize(400, 130);
        restartGameButton.setOnAction(new restartGameHandler());
        restartGameButton.setStyle("-fx-font-size:45; -fx-background-color: burlywood; -fx-border-width: 3; -fx-border-color: black	");
        
        //Sets the column sizes of the gridpane
        buttonContainer.getColumnConstraints().add(new ColumnConstraints(SceneController.WINSIZE_X/3));
        emptyContainerTop.getColumnConstraints().add(new ColumnConstraints(300));
        emptyContainerTop.getRowConstraints().add(new RowConstraints(250));
        
        //Gridpane contraints need to be set as well, in a static way
        GridPane.setConstraints(restartGameButton, 1, 0);
        GridPane.setConstraints(gameOverLabel, 1, 5);
        
        //Add all items to their panes
        emptyContainerTop.getChildren().addAll(gameOverLabel);
        buttonContainer.getChildren().addAll(restartGameButton);
        sceneGameOver.getChildren().addAll(emptyContainerTop, buttonContainer, emptyContainerBot);
        this.getChildren().addAll(sceneGameOver);
	}
	
	public static void setLossCause(String loss) {
		lossCause = loss;
		gameOverLabel.setText(lossCause);
	}
	
	private class restartGameHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			//Restart game handler
			if(event.getSource() == restartGameButton) { 
				//Reset all of the games properties to their initial (yikes)
					//Set stats totals back to original
					SceneDefault.returnEnergyDefault();
					SceneDefault.returnGoldDefault();
					SceneDefault.returnHealthDefault();
					SceneDefault.newGameHappiness();
					
					//Set upgrade buttons back to their default
					SceneUpgrade.upgradeButtonHandler.resetButtons();
					
					//Handle upgrade button effect resets?
					
					//Reset attack event settings
					HealthManager.resetAttackAndUpgrades();
					
					//Set dayCount back to 0
					SceneDefault.setDayCount(0);
					
					//Set text boxes to default
					SceneDefault.setEventStringDefault();
					
					
					//Finally, update text boxes and stat areas with changes to default
					UpdateAll.updateEverything();
				
				//Return the scene to default
				SceneController.getStage().setScene(SceneController.getSceneDefault());
				SceneController.mainStage.setFullScreen(true);
			}
			else {
				System.out.println("No source");
			}
		}
	}
	
}
