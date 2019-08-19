import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class readSensor implements Runnable{
	long start;
	String nameFile;
	String[] direction;
	TLSController control;
	
	readSensor(long start, String nameFile, TLSController control){
		this.start = start;
		this.nameFile = nameFile;
		this.control = control;
	}
	
	@Override
	public void run() {

		
		
		try {
			Thread.sleep(100); //give time the file to be written successfully
			File file = new File(nameFile);
			Scanner read = new Scanner(file);
			
			while (read.hasNext()) {
				Random random = new Random();
				int randomTimeNewSensor = random.nextInt(10000) + 1000; //time interval between sensor will be randomly 1-10s
				Thread.sleep(randomTimeNewSensor);
				String input = read.nextLine();
				System.out.println(((System.currentTimeMillis() - start)/ 100 * 100) + " S " + input);
				control.addCar(input);
				control.manageCar();				
				}read.close();
			} catch (Exception e) {};
			
		}
}
