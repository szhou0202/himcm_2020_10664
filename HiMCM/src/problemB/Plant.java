package problemB;

import java.util.ArrayList;

public class Plant {
	private double ben;
	private double tax;
	private double feas;
	private ArrayList<Double> costs;

	public Plant(double[] data) {
		ben = data[0];
		ben = data[1];
		ben = data[2];

		for (double d : data){
			costs.add(d);
		}

		for(int i = data.length - 1; i >= 0; i--){
			while (costs.get(i) == 0){
				costs.remove(i);
			}
		}

		for(double d : costs){
			System.out.println("cost " +d);
		}
	}

	public double getBen(){
		return ben;
	}

	public double getTax(){
		return tax;
	}

	public double getFeas(){
		return feas;
	}

	public ArrayList<Double> getBen(){
		return costs;
	}
  /**
  public static void main(String[] args){
		Plant p = new Plant();
	}
	**/

}
