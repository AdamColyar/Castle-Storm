import java.util.ArrayList;

public class EventGenerator {
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	private static int randomNumber;
	private static int rng1, rng2, rng3, rng4, numReps, repRNG;
	private static String fortuneStr, weatherStr, loreStr, incomingStr, representatives;
	private static String incomingAlabasta = "The Kingdom of Alabsta is approaching, perhaps with gifts.";
	private static String incomingFeliora = "A messenger tells you that Feliora Kingdom is arriving soon.";
	private static String incomingBaronata = "Baronata Kingdom is near! They are not known for being very friendly.";
	
	private static String weatherStormy = "Todays weather is shifting every which way. Lightning strikes and rain pours.";
	private static String weatherWindy = "The weather today is cold, not because of the temperature but because of the whistling wind.";
	private static String weatherSunny = "The sun beams brightly above you. Todays weather will be warm and sunny.";
	
	private static String fortuneGood = "An astrologer flips a card: its a 10 of Pentacles. You will have exceptional luck today.";
	private static String fortuneAverage = "A calico cat stares as you look out the window. Fate feels like its in your hands.";
	private static String fortuneBad = "Magical bananas are tripping you today. The last thing you should do is take chances.";
	
	private static String loreOne = "The whole kingdom is dancing and cheering today. Its a noisy morning, but a good one.";
	private static String loreTwo = "A potion maker is traveling through your kingdom, but turned some people to frogs by accident...";
	private static String loreThree = "You hear rumors that Alabasta Kingdom is actually a crime syndicate. Surely just a rumor.";
	
	public EventGenerator() {
		randomNumber = 0;
	}
	
	public static void addEventAt(Event e, int eventIndex) {
		eventList.add(eventIndex, e);
	}
	
	public static void generateEvent() {
		
		//Other Event string rngs
		rng1 = RNG(3);
		rng2 = RNG(3);	
		rng3 = RNG(3);		
		rng4 = RNG(3);
		//numReps keeps track of how many rep events can be made per round
		numReps = RNG(3);

		//repRNG dictates what rep will be created
		generateNewRepRNG();
		
		//RNG for this rounds incoming String
		if(rng1 == 1) {
			incomingStr = incomingAlabasta;
			//Add a thing here that sets next round to be an arrival of this kingdom
		}
		else if(rng1 == 2) {
			incomingStr = incomingFeliora;
		}
		else if(rng1 == 3) {
			incomingStr = incomingBaronata;
			
			//Check if an attack event is triggered before doing anything
			if(!HealthManager.getAttackTriggered()) {
				
				//Trigger it, and set attackTriggered true
				HealthManager.triggerEventAttack(incomingBaronata);
				HealthManager.setAttackTriggered(true);
			}
		}
		
		//RNG for this rounds weather String
		if(rng2 == 1) {
			weatherStr = weatherStormy;
			//unsure what to do with weather effects right now
		}
		else if(rng2 == 2) {
			weatherStr = weatherWindy;
		}
		else if(rng2 == 3) {
			weatherStr = weatherSunny;
		}
		
		//RNG for this rounds fortune String
		if(rng3 == 1) {
			fortuneStr = fortuneGood;
			//Set luck here
		}
		else if(rng3 == 2) {
			fortuneStr = fortuneAverage;
		}
		else if(rng3 == 3) {
			fortuneStr = fortuneBad;
		}
		
		//RNG for this rounds lore String
		if(rng4 == 1) {
			loreStr = loreOne;
		}
		else if(rng4 == 2) {
			loreStr = loreTwo;
		}
		else if(rng4 == 3) {
			loreStr = loreThree;
		}
		
		representatives = "There are currently " + numReps + " representatives waiting inside your chambers for an audience.";
		
		Event.setEventBString(representatives, fortuneStr, weatherStr, incomingStr, loreStr);
	}
	
	public static void generateNewRepRNG() {
		repRNG = RNG(4);
	}
	
	public static void decrementNumReps() {
		numReps--;
	}
	
	public static int RNG(int choices) {
		
		//Generator randomNumber based on number of choices
		randomNumber = (int) (Math.random()*(choices) + 1); 
		return randomNumber; 
	}
	
	public static int getNumReps() {
		//Prevent -1 reps return
		if(numReps <= 0) { 
			return 0;
		}
		return numReps;
	}
	
	public static int getRepRNG() {
		return repRNG;
	}
	
	public static String getBaronata() {
		return incomingBaronata;
	}
	
	public static String getAlabasta() {
		return incomingAlabasta;
	}
	
	public static String getFeliora() {
		return incomingFeliora;
	}
}
