import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class inputSensor implements Runnable {
	String nameFile;
	String[] direction;
	TLSController control;
	int count = 0;
	
	inputSensor(String nameFile, String[] direction, TLSController control){
		this.nameFile = nameFile;
		this.direction = direction;
		this.control = control;
	}
	
	@Override
	public void run() {

		File fileIn = new File("input.txt");
		try {
		Scanner read = new Scanner(fileIn);
		while(read.hasNext()) {
			read.nextLine();
			count++;
		} read.close();
		} catch (Exception e) {};
		
		control.addCount(count);
		
	}
}
