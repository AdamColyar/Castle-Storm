import javafx.scene.control.Button;

public class Upgrade extends UpgradeManager{

	private Button btn;
	private int cost;
	private int upgradeNum;
	private String buttonAddress;
	private boolean upgraded;
	
	public Upgrade(Button btn, int cost, int upgradeNum) {
		this.btn = btn;
		this.cost = cost;
		this.upgradeNum = upgradeNum;
		buttonAddressToString(btn);
	}
	
	public Button getUpgradeButton() {
		return btn;
	}
	
	public int getUpgradeCost() {
		return cost;
	}
	
	public String getButtonAddress() {
		return buttonAddress;
	}
	
	public void buttonAddressToString(Button btn) {
		this.buttonAddress = btn.toString();
	}
	
	public void setUpgraded(boolean upgrade) {
		this.upgraded = upgrade;
	}
	
	public boolean getUpgraded() {
		return upgraded;
	}
	
	public void handleUpgrade() {
		
		//Check for already upgraded
		if(getUpgraded() == false) {
			//Handle upgrade case #
			if(upgradeNum == 1) {
				SceneDefault.setGoldTotal(1500);
			}
			else if(upgradeNum == 2) {
				HealthManager.increaseDefenseTotal(10);
			}
			else if(upgradeNum == 3) {
				//50 for now, might need to balance
				CostManager.changeRevenue(50);
			}
			else if(upgradeNum == 4) {
				SceneDefault.setHealthTotal(SceneDefault.getHealthTotal() + 50);
				SceneDefault.setHealthCurrent(SceneDefault.getHealthCurrent() + 50);
			}
			else if(upgradeNum == 5) {
				SceneDefault.setEnergyTotal(SceneDefault.getEnergyTotal()+1);
			}
			else if(upgradeNum == 6) {
				SceneDefault.changeHealthPerRound(2);
			}
			else if(upgradeNum == 7) {
				HealthManager.increaseDefenseTotal(10);
			}
			else {
				System.out.println("UpgradeNum not found");
			}
		}
		
		//Set the local upgrade object to upgraded true (avoid upgrading more than once)
		setUpgraded(true);
	}
}
