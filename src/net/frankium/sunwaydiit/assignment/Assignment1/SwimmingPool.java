package net.frankium.sunwaydiit.assignment.Assignment1;

public class SwimmingPool {
	private double length, width, depth, fillingRate, drainingRate;
	private static double conversionRate = 7.48052;
	public SwimmingPool(double[] dimensions, double fillingRate, double drainingRate){
		this.setDimensions(dimensions);
		this.setFillingRate(fillingRate);
		this.setDrainingRate(drainingRate);
	}
	
	//estimate amount of water in gallons
	public double estimateWaterAmountToFill(){ return cubicFootToGallon(this.getVolume()); }
	public double estimateWaterAmountToFill(double preFilledInGallon){ return cubicFootToGallon(this.getVolume()) - preFilledInGallon; }
	
	//estimate fill time
	public double estimateFillTime(double preFilledInGallon){ return (this.estimateWaterAmountToFill(preFilledInGallon)/this.getFillingRate()); };
	public double estimateFillTime(){ return this.estimateWaterAmountToFill()/this.getFillingRate(); };
	
	//addWater >:)
	
	//convertors
	private static double cubicFootToGallon(double cubicFoot){ return cubicFoot * conversionRate; }
	private static double gallonToCubicFoot(double gallon){ return gallon/conversionRate; }
	//setters
	private void setFillingRate(double rate) {this.fillingRate = rate;};
	private void setDrainingRate(double rate) {this.drainingRate = rate;};
	private void setDimensions(double[] dimensions) {this.length = dimensions[0];this.width = dimensions[1];this.depth = dimensions[2];};
	//getters
	private double[] getDimensions(){ return new double[]{this.length,this.width,this.depth};}
	private double getFillingRate(){return this.fillingRate; }
	private double getDrainingRate(){return this.drainingRate; }
	private double getVolume(){ return length * width * depth; }
}
