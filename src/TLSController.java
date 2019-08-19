import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class TLSController {
	Queue<String> car = new LinkedList<String>();
	static String curDir = "EWL";
	static int count = 0;
	static volatile boolean interrupt = false;
	
	public void addCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public void manageCar() {
		
		String nextCar = car.peek();
		
		if (!nextCar.equals(curDir)) {
			interrupt = true;
		}
	}

	public void addCar(String input) {
		car.offer(input);
	}
	
	public boolean hasNoCar() {
		return car.isEmpty();
	}
	
	public boolean removeCar(String road) {
		if(car.contains(road)) {
			car.remove(road);
			count-=1;
			return true;
		}
		return false;
	}
	
	public void changeRoad() {
		if (!car.isEmpty()) {
			curDir = car.peek();
			System.out.println("\nNext traffic turn:  " + car.toString());
		} else {
			curDir = "EWL";
			System.out.println("\nNext traffic turn: " + car.toString());
		}
	}
	
	public String getcurDir() {
		return curDir;
	}
	
	public boolean isInterrupt() {
		return interrupt;
	}
	
	public void setNoInterrupt() {
		interrupt = false;
	}
}
