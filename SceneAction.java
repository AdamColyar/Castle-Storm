import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SceneAction extends VBox {

	private VBox sceneAction;
	private HBox topContainer;
	private HBox mainContainer;
	
	private VBox leftPanel;
	private VBox middlePanel;
	private VBox rightPanel;
	
	private GridPane buttonHolder1;
	private GridPane buttonHolder2;
	
	private static TextArea leftTextArea;
	private static TextArea middleTextArea;
	private static TextArea rightTextArea;

	private static Label nameLabel;
	private static Label numRepsLabel;
	private static Label energyLabel;
	private static Label goldLabel;
	private static Label healthLabel;
	
	private Button buttonExit;
	private Button buttonYes;
	private Button buttonNo;
	
	private static ImageView repImage;
	private ImageView bActionView;
	private ImageView bTopView;
	private ImageView spacer1;
	private ImageView spacer2;
	
	private static boolean decisionsMade;
	private static int numDecisions;
	
	private static String answerCode1 = "";
	private static String answerCode2 = "";
	
	private int textAreaSizeX1 = 500;
	private int textAreaSizeX2 = 400;
	private int textAreaSizeY = 300;
	
	public SceneAction() {
		
		//Scene setup
		sceneAction = new VBox(50);
        sceneAction.setAlignment(Pos.CENTER);
        
        //HBox setup
        mainContainer = new HBox();
        topContainer = new HBox(110);
        topContainer.setMinSize(500, 50);
        
        //Vbox setup
        leftPanel = new VBox(30);
        middlePanel = new VBox(30);
        rightPanel = new VBox(30);
        
        //Button gridPanes creation
        buttonHolder1 = new GridPane();
        buttonHolder2 = new GridPane();
        
        //Setup Buttons and labels
        String buttonStyleYes = "-fx-text-fill: black; -fx-font-size:22; -fx-font-weight: 500; -fx-background-color: lightgreen; -fx-border-color: black; -fx-border-width: 4";
        String buttonStyleNo = "-fx-text-fill: black; -fx-font-size:22; -fx-font-weight: 500; -fx-background-color: crimson; -fx-border-color: black; -fx-border-width: 4";
        String textAreaStyle = "-fx-text-fill: black; -fx-font-size:25; -fx-background-color: burlywood; -fx-border-width: 1; -fx-border-color: black; -fx-control-inner-background: burlywood; -fx-font-weight: 600";
        
        spacer1 = new ImageView();
        spacer2 = new ImageView();
        spacer1.setFitWidth(100);
        spacer2.setFitWidth(100);
        spacer1.setFitHeight(250);
        spacer2.setFitHeight(250);

        //Instantiate representative based on day 0 or not
        if(SceneDefault.getDayCount() == 0) {
        	nameLabel = new Label("");
        	setRepNameDefault();
        }
        else {
        	nameLabel = new Label("");
        	RepGenerator.generateRepName();
        }
        
        //Display todays reps remaining to decide on on load
        numRepsLabel = new Label("Representatives remaining: " + EventGenerator.getNumReps());
        //uh i changed this/?
        energyLabel = new Label("Energy: " + SceneDefault.getEnergyCurrent() + "/" + SceneDefault.getEnergyTotal());
        goldLabel = new Label("Gold: " + SceneDefault.getGoldCurrent() + "/" + SceneDefault.getGoldTotal());
        healthLabel = new Label("Health: " + SceneDefault.getHealthCurrent() + "/" + SceneDefault.getHealthTotal());
        
        //Configures the labels
        nameLabel.setStyle(textAreaStyle);
        numRepsLabel.setStyle(textAreaStyle);
        energyLabel.setStyle(SceneDefault.labelStyle2);
        goldLabel.setStyle(SceneDefault.labelStyle1);
        healthLabel.setStyle(SceneDefault.labelStyle3);
        nameLabel.setMinSize(400, 50);
        numRepsLabel.setMinSize(25, 50);
        energyLabel.setMinSize(25, 50);
        goldLabel.setMinSize(25, 50);
        healthLabel.setMinSize(25, 50);
        
        //Configure yes/no buttons
        buttonYes = new Button("Yes");
        buttonNo = new Button("No");
        buttonYes.setStyle(buttonStyleYes);
        buttonNo.setStyle(buttonStyleNo);
        buttonYes.setMinSize(200, 50);
        buttonNo.setMinSize(200, 50);
        
        //Back button
        buttonExit = new Button("Back");
        buttonExit.setMinSize(25, 40);
        buttonExit.setStyle(SceneDefault.labelStyleExit);
        buttonExit.setOnAction(new buttonHandler());
        
        //Setup text areas
        leftTextArea = new TextArea("Information 1");
        middleTextArea = new TextArea("Information 2");
        rightTextArea = new TextArea("Information 3");
        
        setTextAreaSettings(leftTextArea, textAreaStyle, textAreaSizeX1, textAreaSizeY);
        setTextAreaSettings(middleTextArea, textAreaStyle, textAreaSizeX2, textAreaSizeY);
        setTextAreaSettings(rightTextArea, textAreaStyle, textAreaSizeX1, textAreaSizeY);
        
        //Create the image in the Center
        repImage = new ImageView();
        repImage.setFitWidth(400);
        repImage.setFitHeight(400);
        
        //Set button actions
        buttonYes.setOnAction(new buttonHandler());
        buttonNo.setOnAction(new buttonHandler());
        
        //Setup gridPane constraints / spacing
        buttonHolder1.getColumnConstraints().add(new ColumnConstraints(SceneController.WINSIZE_X/13));
        buttonHolder2.getColumnConstraints().add(new ColumnConstraints(SceneController.WINSIZE_X/13));
        buttonHolder1.getRowConstraints().add(new RowConstraints(50));
        buttonHolder2.getRowConstraints().add(new RowConstraints(50));
        GridPane.setConstraints(buttonNo, 1, 0);
        GridPane.setConstraints(buttonYes, 1, 0);
        buttonHolder1.setMinSize(200, 200);
        buttonHolder2.setMinSize(200, 200);
        
        //Main container background image setup
        bActionView = new ImageView();
        Image bImageAction;
        String bActionImgStr = "actionsBackground.jpg";
        bImageAction = ImageAdder.returnImage(bActionView, bActionImgStr);
        mainContainer.setBackground(new Background(ImageAdder.addBackgroundImage(bImageAction)));
        
        //Top container background image setup
        bTopView = new ImageView();
        Image bTopImage;
        String bTopImgStr = "bImage.png";
        bTopImage = ImageAdder.returnImage(bTopView, bTopImgStr);
        this.setBackground(new Background(ImageAdder.addBackgroundImage(bTopImage)));
        
        /////////////////////////////////////////
        
        //Generate representative image, randomly
        if(SceneDefault.getDayCount() == 0) {
        	RepGenerator.setDefaultRepEvent();
        }
        else {
        	RepGenerator.generateRepEvent(EventGenerator.getNumReps(), EventGenerator.getRepRNG());
        }
        
        ////////////////////////////////////////
        
        //Add elements to buttonContainers
        buttonHolder1.getChildren().addAll(buttonNo);
        buttonHolder2.getChildren().addAll(buttonYes);
        
        //Add Elements to panels
        leftPanel.getChildren().addAll(spacer1, buttonHolder1, leftTextArea);
        middlePanel.getChildren().addAll(nameLabel, repImage ,middleTextArea);
        rightPanel.getChildren().addAll(spacer2, buttonHolder2, rightTextArea);
        
        //Add panels to main Hbox
        mainContainer.getChildren().addAll(leftPanel, middlePanel, rightPanel);
        topContainer.getChildren().addAll(buttonExit, goldLabel, numRepsLabel, healthLabel, energyLabel);
        
        //Add HBox containers to VBox scene
        this.getChildren().addAll(topContainer, mainContainer);
        
        
        //When this scene is switched to, it will update local variables always
        updateLabels();
	}
	
	public static void updateLabels() {
		numRepsLabel.setText("Representatives remaining: " + EventGenerator.getNumReps());
		energyLabel.setText("Energy: " + SceneDefault.getEnergyCurrent() + "/" + SceneDefault.getEnergyTotal());
		goldLabel.setText("Gold: " + SceneDefault.getGoldCurrent() + "/" + SceneDefault.getGoldTotal());
		healthLabel.setText("Health: " + SceneDefault.getHealthCurrent() + "/" + SceneDefault.getHealthTotal());
	}
	
	public static ImageView getRepImage() {
		return repImage;
	}
	
	public static void setNumDecisions(int decisions) {
		numDecisions = decisions;
	}
	
	public static int getNumDecisions() {
		return numDecisions;
	}
	
	public static boolean getDecisionsMade() {
		return decisionsMade;
	}
	
	public static void setDecisionsMade(boolean decisions) {
		decisionsMade = decisions;
	}
	
	public static void setTextAreas(String left, String mid, String right) {
		leftTextArea.setText(left);
		middleTextArea.setText(mid);
		rightTextArea.setText(right);
	}
	
	public static void setRepNameDefault() {
		nameLabel.setText("\t    No Representatives");
	}
	
	public static void setRepName(String name) {
		nameLabel.setText(name);
	}
	
	public static String getAnswerCode1() {
		return answerCode1;
	}
	
	public static String getAnswerCode2() {
		return answerCode2;
	}
	
	public static void setAnswerCode1(String code) {
		answerCode1 = code;
	}
	
	public static void setAnswerCode2(String code) {
		answerCode2 = code;
	}
	
	public static void decisionPunisher() {	
		//Check if the user made decisions on that round
		if(getDecisionsMade() == true || SceneDefault.getEnergyCurrent() < 2) {
			
			int newHapRating = SceneDefault.getHappinessRating() + 10;
			
			//Overflow checker for happiness rating increasing with decisions made
			if(newHapRating >= 100) {
				SceneDefault.setHappinessRating(100);
			}
			else {
				SceneDefault.setHappinessRating(newHapRating);;
			}
		}
		//No decisions were made at all
		else {
			//Decrement happiness
			SceneDefault.setHappinessRating(SceneDefault.getHappinessRating()-10);
			
			//Overwrite lore text with people revolting
			//////....
		}
	}
	
	private void setTextAreaSettings(TextArea input, String style, int sizeX, int sizeY) {
		input.setEditable(false);
        input.setWrapText(true);
        input.setPrefHeight(sizeY);
        input.setPrefWidth(sizeX);
        input.setStyle(style);
	}
	
	private class buttonHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			//Handles for button exit
			if(event.getSource() == buttonExit) { 
				SceneController.getStage().setScene(SceneController.getSceneDefault());
				SceneController.mainStage.setFullScreen(true);
			}

			//Handles for actions tabs advisor buttons
			else if(event.getSource() == buttonYes || event.getSource() == buttonNo) {
				if(EventGenerator.getNumReps() >= 0) {
					if(SceneDefault.getEnergyCurrent() > 0) {
						if(event.getSource() == buttonYes) { 

							//Handle Decision effects based on decision
							handleAnswerCodes(getAnswerCode2());
							
							//Load next advisor after creating new RNG for it
							loadNextAdvisor();
						}
						else if(event.getSource() == buttonNo) { 
							
							//Handle Decision effects based on decision
							handleAnswerCodes(getAnswerCode1());
							
							//Load next advisor after creating new RNG for it
							loadNextAdvisor();
						}
						
						else {
							System.out.println("No source");
						}
					}
					else {
						//Create a label or something that says "I'm too tired to go on"
						setRepName("\tI'm too tired to go on...");
					}
				}
			}
			
			else {
				System.out.println("No source");
			}
		}
		
		private void handleAnswerCodes(String answerCode) {
			
			if(answerCode.equals("")) {
				//Day 0 nothing happens
			}
			else if(answerCode.equals("AA")) {
				//Peoples dissatisfaction grows
				//-25 but cancels out to -15 because decision made
				SceneDefault.changeHappinessRating(-25);
			}
			else if(answerCode.equals("AB")) {
				//-100 gold, + 20 temp health
				if(SceneDefault.getGoldCurrent() >= 200) {
					SceneDefault.changeGoldCurrent(-200);
					HealthManager.addHealthExtra(20);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AC")) {
				//Nothing happens
			}
			else if(answerCode.equals("AD")) {
				//Increase gold 100, decrease health 10
				if(SceneDefault.getGoldCurrent() >= 100) {
					SceneDefault.changeGoldCurrent(100);
					HealthManager.addHealthExtra(-10);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AE")) {
				//Decrease gold 100, increase health 5
				if(SceneDefault.getGoldCurrent() >= 100) {
					SceneDefault.changeGoldCurrent(-100);
					HealthManager.addHealthExtra(5);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AF")) {
				//Increase def by 5, costs 400 gold
				if(SceneDefault.getGoldCurrent() >= 400) {
					HealthManager.increaseDefenseTotal(5);
					SceneDefault.changeGoldCurrent(-400);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AG")) {
				//May do something with this eventually
				//easter egg round 50 he returns?
				if(SceneDefault.getGoldCurrent() >= 200) {
					SceneDefault.changeGoldCurrent(-200);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AH")) {
				//Decrease gold 100
				if(SceneDefault.getGoldCurrent() >= 100) {
					HealthManager.changeGoldCurrent(-100);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else if(answerCode.equals("AI")) {
				//Decrease defense total by 5
				HealthManager.increaseDefenseTotal(-5);
			}
			else if(answerCode.equals("AJ")) {
				//Lose 50 gold, gain 5 health
				if(SceneDefault.getGoldCurrent() >= 50) {
					SceneDefault.changeGoldCurrent(-50);
					HealthManager.addHealthExtra(5);
				}
				else {
					reverseAnswerCode(answerCode);
				}
			}
			else {
				System.out.println("AnswerCode not found");
			}
			
			HealthManager.checkGameOver();
		}
		
		private void reverseAnswerCode(String answerCode) {
			//Call the same method again with the opposite answer code
			//IE Yes but not enough gold, will redirect to trying the other answer code
			if(answerCode == getAnswerCode1()) {
				handleAnswerCodes(getAnswerCode2());
			}
			else if(answerCode == getAnswerCode2()) {
				handleAnswerCodes(getAnswerCode1());
			}
		}
		
		private void loadNextAdvisor() {
			//Handle decision-made information before moving on
			numDecisions++;
			decisionsMade = true;
			
			//Create new RNG to be used for the representative then event
			//Only if there are reps remaining, otherwise create empty event
			if(EventGenerator.getNumReps()-1 > 0) {
				EventGenerator.generateNewRepRNG();
				RepGenerator.generateRepEvent(EventGenerator.getNumReps(), EventGenerator.getRepRNG());
			}
			else if(EventGenerator.getNumReps()-1 == 0) {
				RepGenerator.setEmptyRepEvent();
			}
			
			//Decrease energy, update stats here AND in both upgrades/default scene
			EventGenerator.decrementNumReps();
			SceneDefault.decrementEnergy();
			
			UpdateAll.updateEverything();
			
		}
	}
}
