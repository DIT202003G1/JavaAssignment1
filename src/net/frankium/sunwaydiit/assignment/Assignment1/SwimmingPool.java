package net.frankium.sunwaydiit.assignment.Assignment1;

public class SwimmingPool{
	private double length, width, depth, fillingRate, drainingRate, waterAmount;
	private static final double conversionRate = 7.48052;
	
	//Constructor
	public SwimmingPool(double[] dimensions, double fillingRate, double drainingRate, double waterAmount){
		this.setDimensions(dimensions);
		this.setFillingRate(fillingRate);
		this.setDrainingRate(drainingRate);
		this.setWaterAmount(waterAmount);
	}
	//convertors
	private static double cubicFootToGallon(double cubicFoot){ return cubicFoot * conversionRate; }
	private static double gallonToCubicFoot(double gallon){ return gallon/conversionRate; }
	
	//get volume
	public double getVolume(){ return length * width * depth; }
	public double getEmptyVolume(){ return (length * width * depth) - gallonToCubicFoot(waterAmount); }
	public double getFilledVolume(){ return gallonToCubicFoot(waterAmount); }
	
	// fill, drain water
	public void fillWaterAtRate(double time, double rate){
		waterAmount += ( time * rate);
		if (waterAmount > cubicFootToGallon(getVolume()) ){
			waterAmount = cubicFootToGallon(getVolume()) ;
			System.out.println("Pool Overflow");
		}
	};
	public void drainWaterAtRate(double time, double drainRate){
		waterAmount -= (time * drainRate);
		if (waterAmount < 0) {
			waterAmount = 0;
			return;
		}
	}
	
	public int timeToFillThePool(){return (int) (  ( cubicFootToGallon(getVolume()) - waterAmount) / fillingRate);}
	public int timeToDrainThePool(){return(int)(waterAmount/drainingRate + 0.5);}
	
	//calculate how much water is needed to fill in the pool completely
	public double waterNeededToFillPool(){
		if (waterAmount < cubicFootToGallon( getVolume())){
			return cubicFootToGallon( getVolume()) - waterAmount;
		}return 0;
	}
	
	//setters
	public void setWaterAmount(double waterAmount) { this.waterAmount = waterAmount; }
	public void setFillingRate(double rate) {this.fillingRate = rate;};
	public void setDrainingRate(double rate) {this.drainingRate = rate;};
	public void setDimensions(double[] dimensions) {this.length = dimensions[0];this.width = dimensions[1];this.depth = dimensions[2];};
	//getters
	public double getWaterAmount() { return waterAmount; }
	public double[] getDimensions(){ return new double[]{this.length,this.width,this.depth};}
	public double getFillingRate(){return this.fillingRate; }
	public double getDrainingRate(){return this.drainingRate; }
}
