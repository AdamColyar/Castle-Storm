import java.util.ArrayList;

public class UpgradeManager {
	private static ArrayList<Upgrade> upgradeList = new ArrayList<Upgrade>();
	
	public static void addUpgrade(Upgrade upgrade) {
		upgradeList.add(upgrade);
	}
	
	public static ArrayList<Upgrade> getUpgradeList() {
		return upgradeList;
	}
	
	public static Upgrade getUpgrade(int index) {
		return upgradeList.get(index);
	}
	
}
