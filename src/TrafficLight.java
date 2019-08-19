
public class TrafficLight implements Runnable{
	private long start;
    TLSController control;
    
    public TrafficLight(long start, TLSController control) {
        this.start = start;
        this.control = control;
    }
    
	public void toGreen(){
		long start2 = (System.currentTimeMillis()-start) / 100 * 100; 
		System.out.println(start2 + " L " + control.getcurDir() + " G ");
		long now = System.currentTimeMillis() - start;
		while ((now-start2) <= 12000){
			now = System.currentTimeMillis() - start;
			if (((now - start2) % 1000) == 0) {
				control.removeCar(control.getcurDir());
			}
			
			if ((now-start) >= 6000 && control.isInterrupt()){
				try {
					Thread.sleep(500);
				} catch (Exception e) {} break;
			}
		}
	}
	
	public void toYellow() {
		long start2 = (System.currentTimeMillis() - start) / 100 * 100;
		long now = System.currentTimeMillis() - start;
		System.out.println(start2 + " L " + control.getcurDir() + " Y ");
		while((now - start2)<=6000) {
			now = System.currentTimeMillis() - start;
		}
	}
	
	public void toRed() {
		long start2 = (System.currentTimeMillis() - start) / 100 * 100;
		System.out.println(start2 + " L " + control.getcurDir() + " R ");
		control.setNoInterrupt();
	}
	
	@Override
	public void run() {
		try {
			toGreen();
		while (control.getCount() > 0) {
			toGreen();
			toYellow();
			toRed();
			control.changeRoad();
		}
			} catch (Exception e) {};
	}
    
}
