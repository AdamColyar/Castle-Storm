import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;

public class SceneUpgrade extends BorderPane {

	private BorderPane sceneUpgrade;
	private ScrollPane scrollUpgrades;
	private HBox tabContainer;
	private HBox returnContainer;
	private VBox buttonTab;
	private VBox costTab;
	private VBox descriptionTab;
	private static Label goldLabel;
	private Label spacerLabel;
	private Button buttonExit2;
	private final static int verticalSpacing = 50;
	private ImageView bImageView;
	private static String upgradeStyle = "-fx-text-fill: black; -fx-font-size:22; -fx-background-color: ivory; -fx-border-color: chocolate; -fx-border-insets: 3; -fx-border-width: 3";
	public final static String customBlue = "#2C3D4F";
	public final String bImageString = "bImage.png";
	public static Image bImage;
	
	public SceneUpgrade() {
		
		//Scene panes setup
		sceneUpgrade = new BorderPane();
	
		returnContainer = new HBox(390);
		tabContainer = new HBox();
		
		buttonTab = new VBox();
		costTab = new VBox();
		descriptionTab = new VBox();
        
        //Creating exit button for top HBox
        buttonExit2 = new Button("Back");
        buttonExit2.setMinSize(130, 40);
        buttonExit2.setOnAction(new buttonExitHandler2());
        buttonExit2.setStyle(SceneDefault.labelStyleExit);
        
        //Create labels for gold and add them to the top HBox container
        goldLabel = new Label();
        goldLabel = new Label("   Gold: " + SceneDefault.getGoldTotal());
        goldLabel.setStyle(SceneDefault.labelStyle1);
        goldLabel.setMinSize(140, 53.5);
        returnContainer.getChildren().addAll(buttonExit2, goldLabel);
        
        //Create Upgrade Object with button and cost, and CostManager object
        CostManager cm = new CostManager();
        
        //Creating buttons for buttonTab///////////
        buttonTab.setSpacing(verticalSpacing);
        
        Button upgrade1Button = new Button("Purchase Coffers Upgrade");
        setUpgradeButton(upgrade1Button); //SetStyle and action
        Upgrade upgrade1 = new Upgrade(upgrade1Button, cm.getCostAt(1), 1);
        UpgradeManager.addUpgrade(upgrade1);
        
        Button upgrade2Button = new Button("Purchase Walls Upgrade");
        setUpgradeButton(upgrade2Button); //SetStyle and action
        Upgrade upgrade2 = new Upgrade(upgrade2Button, cm.getCostAt(2), 2);
        UpgradeManager.addUpgrade(upgrade2);
        
        Button upgrade3Button = new Button("Purchase Events Upgrade");
        setUpgradeButton(upgrade3Button); //SetStyle and action
        Upgrade upgrade3 = new Upgrade(upgrade3Button, cm.getCostAt(3), 3);
        UpgradeManager.addUpgrade(upgrade3);
        
        Button upgrade4Button = new Button("Purchase Housing Upgrade");
        setUpgradeButton(upgrade4Button); //SetStyle and action
        Upgrade upgrade4 = new Upgrade(upgrade4Button, cm.getCostAt(4), 4);
        UpgradeManager.addUpgrade(upgrade4);
        
        Button upgrade5Button = new Button("Purchase Castle Upgrade");
        setUpgradeButton(upgrade5Button); //SetStyle and action
        Upgrade upgrade5 = new Upgrade(upgrade5Button, cm.getCostAt(5), 5);
        UpgradeManager.addUpgrade(upgrade5);
        
        Button upgrade6Button = new Button("Purchase Reconstruction Upgrade");
        setUpgradeButton(upgrade6Button); //SetStyle and action
        Upgrade upgrade6 = new Upgrade(upgrade6Button, cm.getCostAt(6), 6);
        UpgradeManager.addUpgrade(upgrade6);
        
        Button upgrade7Button = new Button("Purchase Blacksmith Upgrade");
        setUpgradeButton(upgrade7Button); //SetStyle and action
        Upgrade upgrade7 = new Upgrade(upgrade7Button, cm.getCostAt(6), 7);
        UpgradeManager.addUpgrade(upgrade7);
        
        /////////////////////////////////////////////
        //Creating cost labels
        costTab.setSpacing(verticalSpacing);
        
        Label costLabel1 = new Label(cm.getCostAt(1) + " Gold");
        setCostLabel(costLabel1);
        
        Label costLabel2 = new Label(cm.getCostAt(2) + " Gold");
        setCostLabel(costLabel2);
        
        Label costLabel3 = new Label(cm.getCostAt(3) + " Gold");
        setCostLabel(costLabel3);
        
        Label costLabel4 = new Label(cm.getCostAt(4) + " Gold");
        setCostLabel(costLabel4);
        
        Label costLabel5 = new Label(cm.getCostAt(5) + " Gold");
        setCostLabel(costLabel5);
        
        Label costLabel6 = new Label(cm.getCostAt(6) + " Gold");
        setCostLabel(costLabel6);
        
        Label costLabel7 = new Label(cm.getCostAt(6) + " Gold");
        setCostLabel(costLabel7);
        
        /////////////////////////////////////////////
        //descriptionTab text areas
        descriptionTab.setSpacing(verticalSpacing);
        
        TextArea desc1 = new TextArea("Upgrading our coffers will alows us to store more gold for a rainy day. This builders will expand it, increasing our total to 1500 gold.");
        setTextAreaSettings(desc1);
        
        TextArea desc2 = new TextArea("Upgrading our current walls from wooden to stone will help keep us alive during raids. Enemies will have a harder time breaking in. (Defense + 10)");
        setTextAreaSettings(desc2);
        
        TextArea desc3 = new TextArea("Upgrading our budget for public events will allow us to host more festivals and competitions, which will encourage trade. Our revenue will benefit greatly. (Revenue + 50)");
        setTextAreaSettings(desc3);
        
        TextArea desc4 = new TextArea("Upgrading our people's houses from wooden to stone will allow the people to protect themselves even in the event that our defenses fall. (Health + 50)");
        setTextAreaSettings(desc4);
        
        TextArea desc5 = new TextArea("Upgrading our castle's internal space will alow us to move more efficiently through the day, meaning you will have more energy to make decisions. (Energy + 1)");
        setTextAreaSettings(desc5);
        
        TextArea desc6 = new TextArea("Upgrading our budget for reconstruction of the kingdom will help us to sustain damage long term, even when we suffer some losses. (Health every other round + 2)");
        setTextAreaSettings(desc6);     
        
        TextArea desc7 = new TextArea("Upgrading our kingdom's blacksmith will allow us to attain higher qualtiy smithed armor and weapons, keeping the city safer. (Defense + 10)");
        setTextAreaSettings(desc7);
        
        spacerLabel = new Label();
        
        /////////////////////////////////////////////
        //Adding labels to costTab
        costTab.getChildren().addAll(costLabel1, costLabel2, costLabel3, costLabel4, costLabel5, costLabel6, costLabel7);
        
        //Adding buttons to buttonTab
        buttonTab.getChildren().addAll(upgrade1Button, upgrade2Button, upgrade3Button, upgrade4Button, upgrade5Button, upgrade6Button, upgrade7Button, spacerLabel);
        
        //Adding descriptions to descriptionTab
        descriptionTab.getChildren().addAll(desc1, desc2, desc3, desc4, desc5, desc6, desc7);
        
        //Adding to tab container and set spacing
        tabContainer.getChildren().addAll(buttonTab, costTab, descriptionTab);
        tabContainer.setSpacing(75);
        
        //Create scrollpane and settings
        scrollUpgrades = new ScrollPane();
        scrollUpgrades.setStyle("-fx-background:" + customBlue);
        scrollUpgrades.setMinSize(SceneController.WINSIZE_X, SceneController.WINSIZE_Y);
        scrollUpgrades.setContent(tabContainer);
        
        //Set up borderpane
        sceneUpgrade.setTop(returnContainer);
        sceneUpgrade.setCenter(scrollUpgrades);
        
        //Add to borderPane of class
        this.getChildren().addAll(sceneUpgrade);
        
        //Sets the background image of the top bar
        bImageView = new ImageView();
        bImage = ImageAdder.returnImage(bImageView, bImageString);
        this.setBackground(new Background(ImageAdder.addBackgroundImage(bImage)));
        
        
        //When this scene is switched to, it will update local variables always
        updateAllUpgradeStats();
	}
	
	public static void updateAllUpgradeStats() {
		goldLabel.setText("Gold: " + SceneDefault.getGoldCurrent() + "/" + SceneDefault.getGoldTotal());
	}
	
	private void setTextAreaSettings(TextArea input) {
		input.setEditable(false);
        input.setWrapText(true);
        input.setPrefHeight(115);
        input.setPrefWidth(500);
        input.setStyle("-fx-text-fill: black; -fx-font-size:18; -fx-background-color: chocolate; -fx-control-inner-background: burlywood");
	}
	
	private void setCostLabel(Label input) {
		input.setMinSize(50, 115);
        input.setStyle("-fx-text-fill: gold; -fx-font-size:35");
	}
	
	private void setUpgradeButton(Button input) {
		input.setOnMousePressed(new upgradeButtonHandler());
		input.setOnMouseReleased(new upgradeButtonHandler());
		input.setMinSize(450, 115);
        input.setStyle(upgradeStyle);
        addHoverGlow(input);
	}
	
	public static void addHoverGlow(final Node inputNode) {
		Glow glow = new Glow();
		inputNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                inputNode.setEffect(glow);
            }
        });
        inputNode.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                inputNode.setEffect(null);
            }
        });
	}
	
	public class upgradeButtonHandler implements EventHandler<MouseEvent> {
		
		String purchasedStyle = "-fx-text-fill: black; -fx-font-size:22; -fx-background-color: lightblue; -fx-border-color: darkgreen; -fx-border-insets: 3; -fx-border-width: 3";
		String sourceStr;
		
		@Override
		public void handle(MouseEvent event) {
			
			sourceStr = String.valueOf(event.getSource()); 
			
			for(Upgrade upg: UpgradeManager.getUpgradeList()) {
				if(sourceStr.equals(upg.getButtonAddress()) && event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					if(SceneDefault.getGoldCurrent() >= upg.getUpgradeCost()) {
						upg.handleUpgrade();
						SceneDefault.setGoldCurrent(SceneDefault.getGoldCurrent()-upg.getUpgradeCost());
						UpdateAll.updateEverything();
						((Node) event.getSource()).setStyle(purchasedStyle);
					}
				}
			}
		}
		
		//Return all buttons to their default styling
		public static void resetButtons() {
			for(Upgrade upg: UpgradeManager.getUpgradeList()) {
				upg.getUpgradeButton().setStyle(upgradeStyle);
			}
		}
	}
	
	private class buttonExitHandler2 implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			if(event.getSource() == buttonExit2) { 
				SceneController.getStage().setScene(SceneController.getSceneDefault());
				SceneController.mainStage.setFullScreen(true);
			}

			else {
				System.out.println("No source");
			}
		}
	}
}