package problemB;

import java.util.ArrayList;

public class Plant {
	private double ben;
	private double tax;
	private double feas;
	private String id;

	private ArrayList<Double> costs;


	public Plant(double[] data, String s) {
		costs = new ArrayList<Double>();
		ben = data[0];
		tax = data[1];
		feas = data[2];
		id = s;

		//add all 48 data cells into costs
		for (int i = 3; i<data.length; i++){
			costs.add(data[i]);
		}

		//remove trailing zeros in costs
		int i = costs.size()-1;
		while (costs.get(i) == 0){
			costs.remove(i);
			i--;
		}

		//debugging code
		/*for(double d : costs){
			System.out.println("cost " +d);
		}*/
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
