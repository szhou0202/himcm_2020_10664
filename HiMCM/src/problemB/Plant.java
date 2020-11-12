package problemB;

import java.util.ArrayList;

public class Plant {
	private double ben;
	private double tax;
	private double feas;
	private String id;

	private ArrayList<Double> costs;

	public Plant(double[] data, String s) {
		ben = data[0];
		ben = data[1];
		ben = data[2];
		id = s;

		//add all 48 data cells into costs
		for (double d : data){
			costs.add(d);
		}

		//remove trailing zeros in costs
		for(int i = data.length - 1; i >= 0; i--){
			while (costs.get(i) == 0){
				costs.remove(i);
			}
		}

		//debugging code
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

	public ArrayList<Double> getCosts(){
		return costs;
	}

	public String getId(){
		return id;
	}
  /**
  public static void main(String[] args){
		Plant p = new Plant();
	}
	**/

}
