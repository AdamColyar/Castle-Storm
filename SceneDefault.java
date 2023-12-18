import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class SceneDefault extends VBox {
	private VBox sceneRoot;
	private VBox leftControlArea;
	private VBox rightControlArea;
	private static TextArea rightTextAreaTop;
	private static TextArea rightTextAreaBot;
	private static TextArea dayCountTextArea;
	private GridPane stats;
	private SplitPane controlsArea;
	private static Label goldLabel;
	private static Label energyLabel;
	private static Label healthLabel;
	private String goldImage = "goldCoin.png";
	private String energyImage = "lightning.png";
	private String heartImage = "heart.png";
	private String textAreaStyle1;
	private String textAreaStyle2;
	private String textAreaStyle3;
	private ImageView centerImageView;
	private String centerImage = "CenterImage.png";
	private Button buttonAction;
	private Button buttonUpgrade;
	private Button buttonNextDay;
	private static int energyCurrent = 0;
	private static int healthCurrent = 0;
	private static int goldCurrent = 0;
	private static int goldTotal = 0;
	private static int energyTotal = 0;
	private static int healthTotal = 0;
	private final static int goldDefault = 200;
	private final static int goldTotalDefault = 1000;
	private final static int healthDefault = 100;
	private final static int healthTotalDefault = 100;
	private final static int energyDefault = 2;
	private final static int healthPerRoundDefault = 1;
	private static int healthPerRound = 0;
	private static int happinessRating = 100;
	private static int dayCount;
	public static int r = 255;
    public static int g = 120;
    public static int b = 120;
    public static String labelStyle1;
	public static String labelStyle2;
	public static String labelStyle3;
	public static String labelStyleExit = "-fx-font-size:20; -fx-text-fill: black; -fx-border-width: 5; -fx-border-color: black; -fx-font-weight: 900; -fx-background-color: rgb(" + r + "," + g + ", " + b + ")";
	
	//Loads up the default scene display
	public SceneDefault() {
		
		//Creating the VBox scene
		sceneRoot = new VBox();
        sceneRoot.setAlignment(Pos.CENTER);
        
        //Instantiating labels and default values for stats (only on day 0)
        if(getDayCount() == 0) {
        	happinessRating = 100;
        	
        	energyCurrent = energyDefault;
            energyTotal = energyDefault;
            
            goldCurrent = goldDefault;
            goldTotal = goldTotalDefault;
            
            healthCurrent = healthDefault;
            healthTotal = healthTotalDefault;
            
            healthPerRound = healthPerRoundDefault;
        }
        
        labelStyle1 = "-fx-text-fill: white; -fx-font-size:22; -fx-font-weight: 500; -fx-background-color: chocolate; -fx-border-color: black; -fx-border-width: 4";
        labelStyle2 = "-fx-text-fill: black; -fx-font-size:22; -fx-font-weight: 500; -fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 4";
        labelStyle3 = "-fx-text-fill: white; -fx-font-size:22; -fx-font-weight: 500; -fx-background-color: red; -fx-border-color: black; -fx-border-width: 4";
        
        goldLabel = new Label("Gold: " + goldCurrent + "/" + goldTotal);
        goldLabel.setStyle(labelStyle1);
        
        energyLabel = new Label("Energy: " + energyCurrent + "/" + energyTotal);
        energyLabel.setStyle(labelStyle2);
        
        healthLabel = new Label("Health: " + healthCurrent + "/" + healthTotal);
        healthLabel.setStyle(labelStyle3);
        
        //Create imageView slots and attempt to add them to the stats bar
        ImageView goldImageView = new ImageView();
        ImageView energyImageView = new ImageView();
        ImageView healthImageView = new ImageView();
        
        //Setting center imageView and size
        centerImageView = new ImageView();
        centerImageView.setFitWidth(1280);
        centerImageView.setFitHeight(500);
	    
	    //Adding images to the stats bar
	  	ImageAdder.addImageStats(goldImageView, goldImage);
	  	ImageAdder.addImageStats(energyImageView, energyImage);
	  	ImageAdder.addImageStats(healthImageView, heartImage);
	  	
	  	ImageAdder.addImage(centerImageView, centerImage, false);
        
        //Setup top stats GridPane column locations
        stats = new GridPane();
        GridPane.setConstraints(goldImageView, 1, 0);
        GridPane.setConstraints(goldLabel, 3, 0);
        GridPane.setConstraints(energyImageView, 5, 0);
        GridPane.setConstraints(energyLabel, 7, 0);
        GridPane.setConstraints(healthImageView, 9, 0);
        GridPane.setConstraints(healthLabel, 11, 0);
        
        //Attempt background image for stats bar setup
        try {
        	String bImageString = "bImage.png";
            ImageView bImageView = new ImageView();
            Image bImage = ImageAdder.returnImage(bImageView, bImageString);
            stats.setBackground(new Background(ImageAdder.addBackgroundImage(bImage)));
        }
        catch(NullPointerException e) {
        	stats.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        }
        
        //Creating unique column restraints/spacing for stats bar
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(6);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(2);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(0);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(3);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(2);
        ColumnConstraints column6 = new ColumnConstraints();
        column6.setPercentWidth(0);
        ColumnConstraints column7 = new ColumnConstraints();
        column7.setPercentWidth(10);
        ColumnConstraints column8 = new ColumnConstraints();
        column8.setPercentWidth(10);
        ColumnConstraints column9 = new ColumnConstraints();
        column9.setPercentWidth(2);
        ColumnConstraints column10 = new ColumnConstraints();
        column10.setPercentWidth(0);
        ColumnConstraints column11 = new ColumnConstraints();
        column11.setPercentWidth(25);
        ColumnConstraints column12 = new ColumnConstraints();
        column12.setPercentWidth(0);
        
        //Add all stats components to the scene
        stats.getChildren().addAll(goldImageView, goldLabel, energyImageView, energyLabel, healthImageView, healthLabel);
        stats.getColumnConstraints().addAll(column0, column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12);
//		stats.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null,null)));
		
        //Setting styles and buttons for scene changes
		String buttonStyle1 = "-fx-text-fill: lightgreen; -fx-border-color: white; -fx-border-insets: 2; -fx-border-width: 2; -fx-font-size:25; -fx-background-color:" + SceneUpgrade.customBlue;
		String buttonStyle2 = "-fx-text-fill: yellow; -fx-border-color: white; -fx-border-insets: 2; -fx-border-width: 2; -fx-font-size:25; -fx-background-color:" + SceneUpgrade.customBlue;
		String buttonStyle3 = "-fx-text-fill: red; -fx-border-color: white; -fx-border-insets: 2; -fx-border-width: 2; -fx-font-size:25; -fx-background-color:" + SceneUpgrade.customBlue;
		
        buttonAction = new Button("Actions");
        buttonAction.setOnAction(new ButtonHandler());
        buttonAction.setMinWidth(400);
        buttonAction.setMinHeight(60);
        buttonAction.setStyle(buttonStyle1);
         
        buttonUpgrade = new Button("Upgrades");
        buttonUpgrade.setOnAction(new ButtonHandler());
        buttonUpgrade.setMinWidth(400);
        buttonUpgrade.setMinHeight(60);
        buttonUpgrade.setStyle(buttonStyle2);
        
        buttonNextDay = new Button("Next Day");
        buttonNextDay.setOnAction(new ButtonHandler());
        buttonNextDay.setMinWidth(400);
        buttonNextDay.setMinHeight(60);
        buttonNextDay.setStyle(buttonStyle3);
        
        //Setup dayCount text area
        textAreaStyle2 = "-fx-text-fill: black; -fx-font-size:25; -fx-background-color: burlywood; -fx-border-width: 1; -fx-border-color: black; -fx-control-inner-background: burlywood; -fx-font-weight: 600";
        
      	dayCountTextArea = new TextArea();
      	dayCountTextArea.setEditable(false);
      	dayCountTextArea.setStyle(textAreaStyle2);
      	dayCountTextArea.setText("\t\tCurrent Day: " + dayCount);
      	
      	//Settings for main control area
        controlsArea = new SplitPane();
      	controlsArea.setDividerPositions(0.3f, 0.6f);
      	controlsArea.setMinHeight(150);
      	
      	//Setup Right Text Areas
      	textAreaStyle1 = "-fx-text-fill: black; -fx-font-size:20; -fx-background-color: burlywood; -fx-border-width: 1; -fx-border-color: black; -fx-control-inner-background: burlywood";
      	textAreaStyle3 = "-fx-text-fill: red; -fx-font-size:20; -fx-background-color: burlywood; -fx-border-width: 1; -fx-border-color: black; -fx-control-inner-background: burlywood";
      	
      	rightTextAreaTop = new TextArea();
      	rightTextAreaBot = new TextArea();
      	rightTextAreaTop.setEditable(false);
      	rightTextAreaBot.setEditable(false);
      	rightTextAreaTop.setWrapText(true);
      	rightTextAreaBot.setWrapText(true);
      	rightTextAreaTop.setPrefHeight(1);
      	rightTextAreaTop.setStyle(textAreaStyle3);
      	rightTextAreaBot.setStyle(textAreaStyle1);
      	
        //Create splitPane and its components
      	leftControlArea = new VBox();
      	rightControlArea = new VBox();
      	
      	//Add the sub-control areas onto the main control area
      	controlsArea.getItems().addAll(leftControlArea, rightControlArea);

      	//Add subcomponents of both control areas
      	leftControlArea.getChildren().addAll(buttonAction, buttonUpgrade, buttonNextDay, dayCountTextArea);
      	rightControlArea.getChildren().addAll(rightTextAreaTop, rightTextAreaBot);
      	
      	//Make sure that the buttons scale up with client
      	buttonAction.setMaxWidth(Double.MAX_VALUE);
      	buttonAction.setMaxHeight(Double.MAX_VALUE);
      	buttonUpgrade.setMaxWidth(Double.MAX_VALUE);
      	buttonUpgrade.setMaxHeight(Double.MAX_VALUE);
      	buttonNextDay.setMaxWidth(Double.MAX_VALUE);
      	buttonNextDay.setMaxHeight(Double.MAX_VALUE);

		//Add elements to the scene
		this.getChildren().addAll(stats, centerImageView, controlsArea);
		
		//Update the textArea with a random default event
//		EventManager.createAllEvents();
		
		//Initalize text area
		setEventStringDefault();
		updateTextArea();
		
		//Neccessary stats on this scene when called
		updateAllStats();
	}
		
	public static void setEventStringDefault() {
		String startA = "Welcome to Castle Stormy! Important information about defense will appear here.";
		String startB = "You've just usurped the King of Castle Stormy! As the new King, you have representatives of the people waiting on your decisions. They will appear in the throne room (actions tab) with requests, but it will drain your energy! Answer wisely. Make sure you answer them at least once a day! If you don't, the people will grow unhappy and revolt. Additionally, manage the Kingdom's potential upgrades to keep profits high and enemies far. Information about your condition and the Kingdom will be displayed here";
		
		Event.setEventAString(startA);
		Event.setEventBString(startB);
	}
	
	public static int getGoldTotal() {
		return goldTotal;
	}
	
	public static int getGoldCurrent() {
		return goldCurrent;
	}
	
	public static int getEnergyTotal() {
		return energyTotal;
	}
	
	public static int getEnergyCurrent() {
		return energyCurrent;
	}
	
	public static int getHealthCurrent() {
		return healthCurrent;
	}
	
	public static int getHealthTotal() {
		return healthTotal;
	}
	
	public static int getDayCount() {
		return dayCount;
	}
	
	public static void setDayCount(int count) {
		dayCount = count;
	}
	
	public static int getGoldDefault() {
		return goldDefault;
	}
	
	public static int getHealthDefault() {
		return healthDefault;
	}
	
	public static int getHappinessRating() {
		return happinessRating;
	}

	public static void setHappinessRating(int happiness) {
		happinessRating = happiness;
	}
	
	public static void changeHappinessRating(int happinessChange) {
		happinessRating += happinessChange;
	}
	
	public static void changeGoldCurrent(int goldChange) {
		int newGold = goldCurrent + goldChange;
		
		//Gold overflow checker
		if(newGold >= goldTotal) {
			goldCurrent = goldTotal;
		}
		else if(newGold < 0) {
			goldCurrent = 0;
		}
		else {
			goldCurrent += goldChange;
		}
	}
	
	public static void changeHealthCurrent(int healthChange) {
		healthCurrent += healthChange;
	}
	
	public static void changeHealthPerRound(int healthChange) {
		healthPerRound += healthChange;
	}
	
	public static int getHealthPerRound() {
		return healthPerRound;
	}
	
	public static void setGoldCurrent(int goldCurrent1) {
		goldCurrent = goldCurrent1;
	}
	
	public static void setEnergyTotal(int energyTotal1) {
		energyTotal = energyTotal1;
	}
	
	public static void setGoldTotal(int goldTotal1) {
		goldTotal = goldTotal1;
	}
	
	public static void setHealthTotal(int healthTotal1) {
		//Not correct right now
		healthTotal = healthTotal1;
		setHealthCurrent(getHealthCurrent() + healthTotal1-healthTotal);
	}
	
	public static void setHealthCurrent(int healthCurrent1) {
		healthCurrent = healthCurrent1;
	}
	
	public static void returnHealthDefault() {
		healthCurrent = healthDefault;
		healthTotal = healthTotalDefault;
	}
	
	public static void returnEnergyDefault() {
		energyCurrent = energyDefault;
		energyTotal = energyDefault;
	}
	
	public static void returnGoldDefault() {
		goldCurrent = goldDefault;
		goldTotal = goldTotalDefault;
	}
	
	public static void decrementEnergy() {
		if(energyCurrent > 0) {
			energyCurrent--;
		}
	}
	
	//Resets energy in a new day
	public static void resetEnergy() {
		energyCurrent = energyTotal;
	}
	
	//resets happiness in a new game
	public static void newGameHappiness() {
		happinessRating = 100;
	}
	
	public static void updateAllStats() {
		goldLabel.setText("Gold: " + goldCurrent + "/" + goldTotal);
		energyLabel.setText("Energy: " + energyCurrent + "/" + energyTotal);
		healthLabel.setText("Health: " + healthCurrent + "/" + healthTotal);
		dayCountTextArea.setText("\t\tCurrent Day: " + dayCount);
	}
	
	//Update local text areas with event strings
	public static void updateTextArea() {
		rightTextAreaTop.setText(Event.getEventAString());
		rightTextAreaBot.setText(Event.getEventBString());
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
	
		@Override
		public void handle(ActionEvent event) {
			
			//Handles for scene switches
			if(event.getSource() == buttonAction) { 
				SceneController.getStage().setScene(SceneController.getSceneOnAction());
				SceneController.mainStage.setFullScreen(true);
			}
			
			else if(event.getSource() == buttonUpgrade) { 
				SceneController.getStage().setScene(SceneController.getSceneOnUpgrade());
				SceneController.mainStage.setFullScreen(true);
			}
			
			////////
			//Handle for next day button
			else if(event.getSource() == buttonNextDay) {
				System.out.println("\nNew Day\n");
				
				//Reset energy count for a new day
				resetEnergy();
				
				//Handles day increase and attack event check
				dayCount++;
				if(HealthManager.getAttackTriggered() == true && dayCount != HealthManager.getDayOfAttack()) {
					HealthManager.decrementDayOfAttackTimer();
				}
				
				//Generate string events, then perform a healthUpdate for checking attacks and changes to health
				EventGenerator.generateEvent();
				HealthManager.healthUpdate();
				
				//Punish user based on # of decisions made in actions tab yesterday
	//DISABLE FOR DEBUG
	SceneAction.decisionPunisher();
				
				//Perform generation update of actions tab representatives
				RepGenerator.generateRepEvent(EventGenerator.getNumReps(), EventGenerator.getRepRNG());
				
				//Perform revenue changes to gold
				CostManager.incrementGold();
				
				//Set decisions made back to false once new events are generated
				//User must interact during their turn to avoid punishment at start of next round button
				SceneAction.setDecisionsMade(false);
				
				//Change the middle scene, useful when layering new images from upgrade choices
				//Might change this to an upgradeScene thing later
//				SceneController.getStage().setScene(SceneController.getSceneDefault());
				
				//Update all text/stat areas
				UpdateAll.updateEverything();
				
				//Lastly, check for a game over
				HealthManager.checkGameOver();
				
				//Debug
				System.out.println(happinessRating);
			}
			else {
				System.out.println("No source");
			}
		}
	}
}
