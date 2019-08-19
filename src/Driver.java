import java.util.concurrent.*;

public class Driver {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
        String nameFile = "input.txt";
        String[] direction = {"EWL", "N", "S", "EWR"};
        
        System.out.println("The 4-way traffic light is live. EWL is green by default");
        
        TLSController control = new TLSController();
        
        inputSensor IS = new inputSensor(nameFile, direction, control);
        readSensor RS = new readSensor(start, nameFile, control);
		TrafficLight TL = new TrafficLight(start, control);
      
		Thread t1 = new Thread(IS);
		Thread t2 = new Thread(RS);
		Thread t3 = new Thread(TL);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {};
		
		System.out.println("The end");
    }
}
