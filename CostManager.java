import java.util.ArrayList;

public class CostManager {
	private static int newGoldTotal;
	private static int goldPerRoundDefault = 100;
	private static int goldPerRound;
	private ArrayList<Integer> costList;
	
	public CostManager() {
		costList = new ArrayList<Integer>();
		costList.add(0);
		costList.add(400);
		costList.add(400);
		costList.add(750);
		costList.add(500);
		costList.add(800);
		costList.add(1000);
		
		if(SceneDefault.getDayCount() == 0) {
			goldPerRound = goldPerRoundDefault;
		}
	}
	
	//TBD later if I decide to add this
	public static void costMultiplier(int timesPurchased) {
		timesPurchased = 0;
	}
	
	//Increment your gold every round
	public static void incrementGold() {
		//Increment gold based on revenue, but using an overflow checker on gold total
		int goldIncrement = getGoldIncrease(goldPerRound);
		SceneDefault.setGoldCurrent(goldIncrement);
	}
	
	//Returns new gold count from increase, depending on if it overflows total or not
	public static int getGoldIncrease(int goldIncrease) {
		//Local variable for keeping track of Gold change
		newGoldTotal = SceneDefault.getGoldCurrent() + goldIncrease;
				
		//Check if its over their cap, and if so set current to total gold
		if(newGoldTotal >= SceneDefault.getGoldTotal()) {
			return SceneDefault.getGoldTotal();
		}
		//Increment like normal otherwise
		else {
			return newGoldTotal;
		}
	}
	
	public static void changeRevenue(int change) {
		goldPerRound += change;
	}
	
	public int getCostAt(int index) {
		return costList.get(index);
	}
}
