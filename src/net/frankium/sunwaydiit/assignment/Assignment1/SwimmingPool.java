package net.frankium.sunwaydiit.assignment.Assignment1;

public class SwimmingPool{
	private double length, width, depth, fillingRate, drainingRate, waterAmount;//All unit in cubic foot or foot
	private static final double conversionRate = 7.48052;
	
	//Constructor
	public SwimmingPool(double[] dimensions, double fillingRate, double drainingRate, double waterAmount){
		this.setDimensions(dimensions);
		this.setFillingRate(fillingRate);
		this.setDrainingRate(drainingRate);
		this.setWaterAmount(waterAmount);
	}
	
	//operations
	//Add water
	public void addWaterByCubicFoot(double time, double cubicFootPerTime){
		double amount = time*cubicFootPerTime;
		if (amount + this.waterAmount > getEmptySpaceInCubicFoot()) this.waterAmount = getVolumeInCubicFoot();
		else this.waterAmount += amount;
	}
	public void addWaterByGallon(double time, double gallonPerTime){
		this.addWaterByCubicFoot(time,gallonToCubicFoot(gallonPerTime));
	}
	//drain water
	public void drainWaterByCubicFoot(double time, double cubicFootPerTime){
		double amount = time * cubicFootPerTime;
		if (this.waterAmount - amount < 0) this.waterAmount = 0;
		else this.waterAmount -= amount;
	}
	public void drainWaterByGallon(double time, double gallonPerTime){
		this.drainWaterByCubicFoot(time,gallonToCubicFoot(gallonPerTime));
	}
	
	//timeCalculation
	public double timeToDrain(){
		if (this.drainingRate == 0) return 0;
		return this.getWaterAmount()/this.drainingRate;
	}
	public double timeToFill(){
		if (this.fillingRate == 0) return 0;
		return this.getEmptySpaceInCubicFoot()/this.fillingRate;
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
	//other getters (derived attributes)
	public double getVolumeInCubicFoot(){return this.length * this.width * this.depth;}
	public double getVolumeInGallon(){return cubicFootToGallon(this.getVolumeInCubicFoot());}
	public double getEmptySpaceInCubicFoot(){return this.getVolumeInCubicFoot() - waterAmount;}
	public double getEmptySpaceInGallon(){return cubicFootToGallon(getEmptySpaceInCubicFoot());}
	public double getWaterAmountInGallon(){return cubicFootToGallon(this.waterAmount);}
	//convertors
	private static double cubicFootToGallon(double cubicFoot){ return cubicFoot * conversionRate; }
	private static double gallonToCubicFoot(double gallon){ return gallon/conversionRate; }
}
