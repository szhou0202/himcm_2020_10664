package problemB;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Alg{
	public static void main(String[] args) {
		try {
			Plant[] data = new FileReader("C:\\Users\\chris\\Documents\\himcm_2020_10664\\HiMCM_data_ordered.csv").read();
			Plant[][] groups = getGroups(data);
			
			for(int i = 0; i<10; i++) {
				Plant[] currentGroup = groups[i];
				int[] startYears = optimalScheduleFromGroup(currentGroup);
				for(int j = 0; j<groups[i].length; j++) {
					System.out.println(currentGroup[j].getId()+" start date: "+ startYears[j]);
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Plant[][] getGroups(Plant[] data){
		int groupSize = 5;
		Plant[][] out = new Plant[10][];
		
		
		out[0] = new Plant[3];
		out[0][0] = data[data.length-1];
		out[0][1] = data[0];
		out[0][2] = data[1];
		
		for(int i = 1; i<10; i++) {
			//System.out.println(i);
			Plant[] currentGroup = new Plant[groupSize];
			currentGroup[4] = data[data.length-1-i];
			for(int j = 0; j<groupSize-1; j++) {
				//System.out.println(data[2 + 4*(i-1) + 1*j]);
				currentGroup[j] = data[2 + 4*(i-1) + 1*j];
			}
			out[i] = currentGroup;
		}
		
		return out;
	}
	
	public static int[] optimalScheduleFromGroup(Plant[] group) {
		
		double highestN = 0.0;
		if(group.length == 3) {
			int[] bestStarts = new int[]{0,0,0};
			for(int a = 0; a<=25-group[0].getCosts().size(); a++) {
				ArrayList<Double> costA = new ArrayList<Double>(Collections.nCopies(a, 0.0));
				costA.addAll(group[0].getCosts());
				costA.addAll(new ArrayList<Double>(Collections.nCopies(25-costA.size(), 0.0)));
				for(int b = 0; b<=25-group[1].getCosts().size(); b++) {
					ArrayList<Double> costB = new ArrayList<Double>(Collections.nCopies(b, 0.0));
					costB.addAll(group[1].getCosts());
					costB.addAll(new ArrayList<Double>(Collections.nCopies(25-costB.size(), 0.0)));
					for(int c = 0; c<=25-group[2].getCosts().size(); c++) {
						ArrayList<Double> costC = new ArrayList<Double>(Collections.nCopies(c, 0.0));
						costC.addAll(group[2].getCosts());
						costC.addAll(new ArrayList<Double>(Collections.nCopies(25-costC.size(), 0.0)));
						
						
						
						int[] starts = new int[]{a,b,c};
						
						double sum = 0;
						double maxPrice = 0;
						double[] prices = new double[3];
						for(int x = 0; x<3; x++) {
							double price = group[x].getCosts().stream().reduce(0.0, (subtotal, element) -> subtotal + element);
							prices[x] = price;
							if(price>maxPrice) {
								maxPrice = price;
							}
							//System.out.println(price);
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
						double average = sum/25;
						//System.out.println("sum: "+sum);
						//System.out.println(totalYears);
						//System.out.println("a: "+a+"   b: "+b+"   c:"+c);
						double sumOfValueMinusMeanSquared = 0;
						for(int y = 0; y<25; y++){
							totalCost = costA.get(y)+costB.get(y)+costC.get(y);
							//System.out.println(totalCost);
							sumOfValueMinusMeanSquared += Math.pow(totalCost-average, 2);
						}
						
						double standardDev = Math.sqrt(sumOfValueMinusMeanSquared/25);
						//System.out.println(standardDev);
						//System.out.println(Math.sqrt(sumOfValueMinusMeanSquared/25));
						
						double N = 58021 / standardDev  + 0.5 * S;
						
						if (N>=highestN) {
							highestN = N;
							bestStarts = starts;
							
						};
					}
				}
			}
			
			return bestStarts;
			
		} else {
			int[] bestStarts = new int[]{0,0,0,0,0};
			for(int a = 0; a<=25-group[0].getCosts().size(); a++) {
				ArrayList<Double> costA = new ArrayList<Double>(Collections.nCopies(a, 0.0));
				costA.addAll(group[0].getCosts());
				costA.addAll(new ArrayList<Double>(Collections.nCopies(25-costA.size(), 0.0)));
				for(int b = 0; b<=25-group[1].getCosts().size(); b++) {
					ArrayList<Double> costB = new ArrayList<Double>(Collections.nCopies(b, 0.0));
					costB.addAll(group[1].getCosts());
					costB.addAll(new ArrayList<Double>(Collections.nCopies(25-costB.size(), 0.0)));
					for(int c = 0; c<=25-group[2].getCosts().size(); c++) {
						ArrayList<Double> costC = new ArrayList<Double>(Collections.nCopies(c, 0.0));
						costC.addAll(group[2].getCosts());
						costC.addAll(new ArrayList<Double>(Collections.nCopies(25-costC.size(), 0.0)));
						for(int d = 0; d<=25-group[3].getCosts().size(); d++) {
							ArrayList<Double> costD = new ArrayList<Double>(Collections.nCopies(d, 0.0));
							costD.addAll(group[3].getCosts());
							costD.addAll(new ArrayList<Double>(Collections.nCopies(25-costD.size(), 0.0)));
							for(int e = 0; e<=25-group[4].getCosts().size(); e++) {
								ArrayList<Double> costE = new ArrayList<Double>(Collections.nCopies(e, 0.0));
								costE.addAll(group[4].getCosts());
								costE.addAll(new ArrayList<Double>(Collections.nCopies(25-costE.size(), 0.0)));
								
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
									totalCost = costA.get(y)+costB.get(y)+costC.get(y)+costD.get(y)+costE.get(y);
									sumOfValueMinusMeanSquared += Math.pow(totalCost-average, 2);
								}
								
								double standardDev = Math.sqrt(sumOfValueMinusMeanSquared/25);
								//System.out.println(standardDev);
								
								double N = 58021 / standardDev  + 0.5 * S;
								
								if (N>highestN) {
									highestN = N;
									bestStarts = starts;
									//System.out.println(standardDev);
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