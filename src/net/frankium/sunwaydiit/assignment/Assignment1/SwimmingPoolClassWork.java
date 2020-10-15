package net.frankium.sunwaydiit.assignment.Assignment1;

public class SwimmingPoolClassWork {
	private double length, width, depth, fillingRate, drainingRate, waterAmount;
	private static final double conversionRate = 7.48052;
	
	public SwimmingPoolClassWork(double length, double width, double depth, double fillingRate, double drainingRate, double waterAmount){
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.fillingRate = fillingRate;
		this.drainingRate = drainingRate;
		this.waterAmount = waterAmount;
	}
	public void set(double length, double width, double depth, double fillingRate, double drainingRate, double waterAmount){
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.fillingRate = fillingRate;
		this.drainingRate = drainingRate;
		this.waterAmount = waterAmount;
	}
	
	//calculate the swimming pool capacity
	public double poolTotalWaterCapacity(){
		return length * width * depth * conversionRate;
	}
	
	//add water
	public void addWater(double time, double fillRate){
		waterAmount += ( time * fillRate);
		if (waterAmount > poolTotalWaterCapacity()){
			waterAmount = poolTotalWaterCapacity();
			System.out.println("Pool Overflow");
		}
	}
	
	//drain water
	public void drainWater(double time, double drainRate){
		waterAmount -= (time * drainRate);
		if (waterAmount < 0) {
			waterAmount = 0;
			return;
		}
	}
	
	//time to fill and drain the pool
	public int timeToFillThePool(){return (int) (((poolTotalWaterCapacity()) - waterAmount) / fillingRate);}
	public int timeToDrainThePool(){return(int)(waterAmount/drainingRate + 0.5);}
	
	//calculate how much water is needed to fill in the pool completely
	public double waterNeededToFillPool(){
		if (waterAmount < poolTotalWaterCapacity()){
			return poolTotalWaterCapacity() - waterAmount;
		}return 0;
	}
	
}
