
public class HealthManager extends SceneDefault{
	private static int newHealth = 0;
	private static int healthChange = 0;
	private static int healthTotal = 0;
	private static int dayCountCurrent;
	private static int dayOfAttack = -1;
	private static int dayOfAttackTimer;
	private static boolean attackTriggered;
	private static String attackBaronSub = "The Kingdom of Baronata are launching a raid against us in: ";
	private static String attackBaronFinal = "";
	private static String attackBaronLaunched = "The Kingdom of Baronata have attacked! ";
	private static String damageDealt;
	private static String peacefulString = "Everything is going well for the Kingdom right now.";
	private static int defenseTotal = 0;
	private static int attackDamage;
	
	public HealthManager() {
		attackDamage = 0;
	}
	
	//Health checker for every rounds attacks and game over
	public static void healthUpdate() {
		//Add round health and check for attacks
		addHealthEveryOtherRound();
		checkDayOfAttack();
	}
	
	//If a person dies during the attack, timer + get broken..
	public static void resetAttackAndUpgrades() {
		dayOfAttackTimer = 0;
		setAttackTriggered(false);
		defenseTotal = 0;
	}
	
	//Used to add any amount of health to current health
	public static void addHealthExtra(int extraHealth) {
		//Local variables for health tracking
		newHealth = getHealthCurrent() + extraHealth;
		healthTotal = getHealthTotal();
		
		//Check for health overflowing total allowed
		if(newHealth < healthTotal) {
			//Increase current health by extra
			setHealthCurrent(newHealth);
		}
		else if(newHealth >= healthTotal) {
			//Set health to total health
			setHealthCurrent(healthTotal);
		}

	}
	
	//Determines health change from a given attack
	public static void calculateHealthChanges(int attackDamage) {
		healthChange = attackDamage - defenseTotal;
		newHealth = getHealthCurrent() - healthChange;
		setHealthCurrent(newHealth);
	}
	
	public static void increaseDefenseTotal(int increase) {
		int defenseInc = defenseTotal + increase;
		
		//Cap defense at 35
		if(defenseInc >= 35) {
			defenseTotal = 35;
		}
		else {
			defenseTotal += increase;
		}
	}
	
	public static void setAttackTriggered(boolean trigger) {
		attackTriggered = trigger;
	}
	
	public static int getDayOfAttack() {
		return dayOfAttack;
	}
	
	public static void addHealthEveryOtherRound() {
		if(SceneDefault.getDayCount() % 2 == 0) {
			addHealthExtra(getHealthPerRound());
		}
	}
	
	public static void decrementDayOfAttackTimer() {
		dayOfAttackTimer--;
	}
	
	public static boolean getAttackTriggered() {
		return attackTriggered;
	}
	
	public static void triggerEventAttack(String attackSource) {
		dayCountCurrent = SceneDefault.getDayCount();
		
		//If the attack event is coming from Baronata Kingdom
		if(attackSource == EventGenerator.getBaronata()) {
			//Setting dayOfAttack
			dayOfAttack = dayCountCurrent + 3;
			
			//Set timer for 3 days from now
			dayOfAttackTimer = dayOfAttack - dayCountCurrent;
			
			//Create final string with timer and display it
			attackBaronFinal = attackBaronSub + dayOfAttackTimer + " days!";
			Event.setEventAString(attackBaronFinal);
		}
	}
	
	//Allows the new day counter to check for expected attacks
	public static void checkDayOfAttack() { //Only works for the baron right now, needs to be edited for all attacks
		
		//If attack is triggered then check for day-of events
		if(getAttackTriggered() == true) {
			
			//If its the attack day then
			if(getDayCount() == dayOfAttack) {
				setAttackDamage();
				
				//Set the string and trigger the event
				Event.setEventAString(attackBaronLaunched + checkDamageString(attackDamage));
				calculateHealthChanges(attackDamage);
				
				//Check for death
				checkGameOver();

				//Event happened, set it back to false
				setAttackTriggered(false);
			}
			
			//Otherwise, attack is coming.
			else {
				attackBaronFinal = attackBaronSub + dayOfAttackTimer + " days!";
				Event.setEventAString(attackBaronFinal);
			}
		}
		
		//Attack isn't triggered, make sure timer gets reset to 0 and String is set peaceful
		else if(getAttackTriggered() == false) {
			dayOfAttackTimer = 0;
			Event.setEventAString(peacefulString);
		}
		
	}
	
	public static void setAttackDamage() {
		int currentDay = getDayCount();
		
		if(currentDay < 10) {
			attackDamage = EventGenerator.RNG(15) + 5;
		}
		else if(currentDay >= 10 && currentDay < 20) {
			attackDamage = EventGenerator.RNG(20) + 10;
		}
		else if(currentDay >= 20 && currentDay < 30) {
			attackDamage = EventGenerator.RNG(25) + 20;
		}
		else if(currentDay >= 30 && currentDay < 40) {
			attackDamage = EventGenerator.RNG(40) + 20;
		}
		else {
			attackDamage = EventGenerator.RNG(currentDay) + 40;
		}
	}
	
	public static String checkDamageString(int attackDamage) {
		if(attackDamage > 25) {
			damageDealt = "The Kingdom is in shambles and will need to recover";
		}
		else if(attackDamage > 10 && attackDamage <= 25) {
			damageDealt = "The Kingdom lost a little bit to the attack.";
		}
		else if(attackDamage >= 5 && attackDamage <= 10) {
			damageDealt = "Our losses were kept to a minimum";
		}
		else if(attackDamage > 0 && attackDamage < 5) {
			damageDealt = "We held them off! They barely touched us.";
		}
		else {
			damageDealt = "We're too strong! No damages were suffered.";
		}
		return damageDealt;
	}
	
	public static void checkGameOver() {
		//Local variable setting for health
		healthTotal = getHealthCurrent();
		
		//DISABLE FOR DEBUG
		
		if(healthTotal <= 0) {
			//End game
			SceneGameOver.setLossCause("Our Kingdom was sieged and you were dethroned!");
			SceneController.getStage().setScene(SceneController.getSceneOnGameOver());
			SceneController.mainStage.setFullScreen(true);
		}
		else if(getHappinessRating() <= 0) {
			//End game
			SceneGameOver.setLossCause("The people are dissatisfied with your rule and dethroned you!");
			SceneController.getStage().setScene(SceneController.getSceneOnGameOver());
			SceneController.mainStage.setFullScreen(true);
		}
	}
	
	public static String getAttackBaronString() {
		return attackBaronFinal;
	}
}
