
public class Event extends EventGenerator{
	private static String eventAString;
	private static String eventBString;
	private String fortune;
	private String incomingWaves;
	private String lore;
	private String weather;
	private int luck;
	private int numRepresentatives;
	
	//rework
	public Event() {
		initalizeStrings();
		numRepresentatives = 0;
		eventBString = "";
	}
	
	//needs to be reworked
	public Event(String eventString, int numRepresentatives) {
		initalizeStrings();
	}
	
	private void initalizeStrings() {
		Event.eventAString = "Nothing important is happening today.";
		this.fortune = "You have good fortune today.\n";
		this.weather = "The weather is clear today.\n";
		this.incomingWaves = "The Kingdom of Alabasta is coming to visit in 5 days.\n";
		this.lore = "The people are having a festival to celebrate the spring harvest.\n";
	}
	
	public int getNumRepresentatives() {
		return numRepresentatives;
	}
	
	public void setNumRepresentatives(int num) {
		numRepresentatives = num;
	}
	
	public static void setEventBString(String representatives, String fortune, String weather, String incomingWaves, String lore) {
		eventBString = representatives + "\n" + fortune + "\n" + weather + "\n" + incomingWaves + "\n" + lore;
	}
	
	public static void setEventBString(String start) {
		eventBString = start;
	}
	public static void setEventAString(String event) {
		eventAString = event;
	}
	
	public void updateEventBString() {
		eventBString = fortune + weather + incomingWaves + lore;
	}
	
	public void setFortune(int luck, String fortune) {
		this.luck = luck;
		this.fortune = fortune;
	}
	
	public String getFortuneString() {
		return this.fortune;
	}
	
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	public String getWeather() {
		return this.weather;
	}
	
	public void setIncomingWaves(String incomingWave) {
		this.incomingWaves = incomingWave;
	}
	
	public String getIncomingWaves() {
		return this.incomingWaves;
	}
	
	public void setLore(String lore) {
		this.lore = lore;
	}
	
	public String getLore() {
		return this.lore;
	}
	
	public static String getEventAString() {
		return eventAString;
	}
	
	public static String getEventBString() {
		return eventBString;
	}
}
