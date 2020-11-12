package problemB;

import java.util.ArrayList;
import java.util.Collections;

public class Alg{
	public static void main(String[] args) {
		Plant[] data = new FileReader("fileName").read();
	}
	
	public Plant[][] getGroups(Plant[] data){
		int groupSize = 5;
		Plant[][] out = new Plant[10][];
		
		
		out[0] = new Plant[3];
		out[0][0] = data[data.length-1];
		out[0][1] = data[0];
		out[0][2] = data[1];
		
		for(int i = 1; i<10; i++) {
			Plant[] currentGroup = new Plant[groupSize];
			currentGroup[4] = data[data.length-1-i];
			for(int j = 1; j<groupSize-1; i++) {
				currentGroup[j] = data[2 + 5*(i-1) + j*(j-1)];
			}
			out[i] = currentGroup;
		}
		
		return out;
	}
	
	public int[] optimalScheduleFromGroup(Plant[] group) {
		
		double highestN = 0.0;
		if(group.length == 3) {
			int[] bestStarts = new int[]{0,0,0};
			for(int a = 0; a<25-group[1].getCosts().size(); a++) {
				ArrayList<Double> costA = (ArrayList<Double>) Collections.nCopies(a, 0.0);
				costA.addAll(group[1].getCosts());
				costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
				for(int b = 0; b<25-group[2].getCosts().size(); b++) {
					ArrayList<Double> costB = (ArrayList<Double>) Collections.nCopies(a, 0.0);
					costA.addAll(group[1].getCosts());
					costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
					for(int c = 0; b<25-group[3].getCosts().size(); c++) {
						ArrayList<Double> costC = (ArrayList<Double>) Collections.nCopies(a, 0.0);
						costA.addAll(group[1].getCosts());
						costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
						
						int[] starts = new int[]{a,b,c};
						
						double sum = 0;
						double maxPrice = 0;
						double[] prices = new double[3];
						int totalYears = 0;
						for(int x = 0; x<3; x++) {
							totalYears += group[x].getCosts().size();
							double price = group[x].getCosts().stream().reduce(0.0, (subtotal, element) -> subtotal + element);
							prices[x] = price;
							if(price>maxPrice) {
								maxPrice = price;
							}
							sum += price;
						}
						
						double sumB = 0;
						double sumU = 0;
						double sumF = 0;
						double sumP = 0;
						for(int x = 0; x<3; x++) {
							sumB += group[x].getBen()*(26-starts[x])/25.0;
							sumU += group[x].getTax()*(26-starts[x])/25.0;
							sumF += group[x].getFeas()*(26-starts[x])/25.0;
							sumP += Math.pow(Math.E, (prices[x]/maxPrice)-1)*starts[x]/(25-group[x].getCosts().size());
						}
						
						double S = (1*sumB + 3*sumU + 3*sumF + 1* sumP)/(3*(1+3+3+1));
						
						double totalCost;
						double average = sum/totalYears;
						double sumOfValueMinusMeanSquared = 0;
						for(int y = 0; y<25; y++){
							totalCost = costA.get(0)+costB.get(0)+costC.get(0);
							sumOfValueMinusMeanSquared = Math.pow(totalCost-average, 2);
						}
						
						double standardDev = Math.sqrt(sumOfValueMinusMeanSquared/25);
						
						double N = 1 * standardDev + 1 * S;
						
						if (N>highestN) {
							highestN = N;
							bestStarts = starts;
						};
					}
				}
			}
			
			return bestStarts;
			
		} else {
			int[] bestStarts = new int[]{0,0,0,0,0};
			for(int a = 0; a<25-group[1].getCosts().size(); a++) {
				ArrayList<Double> costA = (ArrayList<Double>) Collections.nCopies(a, 0.0);
				costA.addAll(group[1].getCosts());
				costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
				for(int b = 0; b<25-group[2].getCosts().size(); b++) {
					ArrayList<Double> costB = (ArrayList<Double>) Collections.nCopies(a, 0.0);
					costA.addAll(group[1].getCosts());
					costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
					for(int c = 0; b<25-group[3].getCosts().size(); c++) {
						ArrayList<Double> costC = (ArrayList<Double>) Collections.nCopies(a, 0.0);
						costA.addAll(group[1].getCosts());
						costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
						for(int d = 0; b<25-group[4].getCosts().size(); d++) {
							ArrayList<Double> costD = (ArrayList<Double>) Collections.nCopies(a, 0.0);
							costA.addAll(group[1].getCosts());
							costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
							for(int e = 0; b<25-group[5].getCosts().size(); e++) {
								ArrayList<Double> costE = (ArrayList<Double>) Collections.nCopies(a, 0.0);
								costA.addAll(group[1].getCosts());
								costA.addAll((ArrayList<Double>) Collections.nCopies(25-costA.size(), 0.0));
								
								int[] starts = new int[]{a,b,c,d,e};
								
								double sum = 0;
								double maxPrice = 0;
								double[] prices = new double[5];
								int totalYears = 0;
								for(int x = 0; x<5; x++) {
									totalYears += group[x].getCosts().size();
									double price = group[x].getCosts().stream().reduce(0.0, (subtotal, element) -> subtotal + element);
									prices[x] = price;
									if(price>maxPrice) {
										maxPrice = price;
									}
									sum += price;
								}
								
								double sumB = 0;
								double sumU = 0;
								double sumF = 0;
								double sumP = 0;
								for(int x = 0; x<5; x++) {
									sumB += group[x].getBen()*(26-starts[x])/25.0;
									sumU += group[x].getTax()*(26-starts[x])/25.0;
									sumF += group[x].getFeas()*(26-starts[x])/25.0;
									sumP += Math.pow(Math.E, (prices[x]/maxPrice)-1)*starts[x]/(25-group[x].getCosts().size());
								}
								
								double S = (1*sumB + 3*sumU + 3*sumF + 1* sumP)/(5*(1+3+3+1));
								
								double totalCost;
								double average = sum/totalYears;
								double sumOfValueMinusMeanSquared = 0;
								for(int y = 0; y<25; y++){
									totalCost = costA.get(0)+costB.get(0)+costC.get(0)+costD.get(0)+costE.get(0);
									sumOfValueMinusMeanSquared = Math.pow(totalCost-average, 2);
								}
								
								double standardDev = Math.sqrt(sumOfValueMinusMeanSquared/25);
								
								double N = 1 * standardDev + 1 * S;
								
								if (N>highestN) {
									highestN = N;
									bestStarts = starts;
								};
							}
						}
					}
				}
			}
			return bestStarts;
		}
	}
}