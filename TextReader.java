import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextReader {
	private static int pastRNG = -1;
	
	public static void readText(String txtFile, int methodHandle, int rngHandle) {
		try {
			//Variables for general readText area
			@SuppressWarnings("unused")
			String line;
			int fileSize = 0;
			
			//Create input reader from file
			FileInputStream fileIn = new FileInputStream(txtFile);
			BufferedReader bfr = new BufferedReader(new InputStreamReader(fileIn));
			
			//Determine size of the file in lines
			while((line = bfr.readLine()) != null) {
				fileSize++;
			}
			
			//Return file reader to the start
			fileIn.getChannel().position(0);
			bfr = new BufferedReader(new InputStreamReader(fileIn));
			
			///
			//Handle slightly differently depending on method
			if(methodHandle == 0) {
				String nameLineRead;
				
				//Create a loop size based off a rolled number
				rngHandle = EventGenerator.RNG(fileSize);
				
				//Move through the file until the line we want
				for(int x = 0; x < rngHandle - 1; x++){
				    bfr.readLine();
				}
				
				//Save the next line as the name
				nameLineRead = bfr.readLine();
				
				//Set the file as the rep's name and close reader
				RepGenerator.setRepName("\t\t" + nameLineRead);
			}
			else if(methodHandle == 1) {
				int totalPrompts, textRNG, textLineReadStart;
				String textAnswer1, textQuestion, textAnswer2;
				
				//Determine how many prompts are in the file assuming 1 line spacer between prompts
				totalPrompts = (int) Math.round(fileSize/4.0);
				
				//Create an RNG # based off the # of prompts in file
				textRNG = EventGenerator.RNG(totalPrompts);
				
				//If we rolled the same as previous roll
				if(textRNG == pastRNG) {
					//While previous roll is equal to the new roll
					//Roll until we get a fresh number
					
					while(pastRNG == textRNG) {
						//Create an RNG # based off the # of prompts in file
						textRNG = EventGenerator.RNG(totalPrompts);
					}
				}
				
				//Determine start line from prompt # rolled
				textLineReadStart = textRNG*4 - 3;

				//Move through the file until the line we want
				for(int x = 0; x < textLineReadStart - 1; x++){
				    bfr.readLine();
				}
						
				//Save the next line as the name
				textAnswer1 = bfr.readLine();
				textQuestion = bfr.readLine();
				textAnswer2 = bfr.readLine();
				
				//Text lines will have a two letter code to know how to handle the answer
				SceneAction.setAnswerCode1(textAnswer1.substring(0, 2));
				SceneAction.setAnswerCode2(textAnswer2.substring(0, 2));
				
				//Remove answerCode
				textAnswer1 = textAnswer1.substring(2, textAnswer1.length());
				textAnswer2 = textAnswer2.substring(2, textAnswer2.length());
				
				//Set the Strings to the textAreas and close reader
				SceneAction.setTextAreas(textAnswer1, textQuestion, textAnswer2);
				
				pastRNG = textRNG;
			}
			
			//Close the reader
			bfr.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("Couldnt find name list");
		}
		catch(IOException e) {
			System.out.println("IO Exception");
		}
	}
}
