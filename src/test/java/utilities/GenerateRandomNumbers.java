package utilities;

import java.util.Random;

public class GenerateRandomNumbers {

	private int setNum;
	
	public int generateRandomNumbers() {
		Random randomNumber = new Random();
		setNum = 500000;
		int newRandomNumber = randomNumber.nextInt(setNum);
		return newRandomNumber;
	}
	
	public int generateRandomMathNumbers() {
		int min = 100000;  
		int max = 500000;  
		
		int newRandomMathNumber = (int)(Math.random()*(max-min+1)+min);  
		return newRandomMathNumber;
	}
}
