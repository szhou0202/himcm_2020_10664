package problemB;


import java.util.*;
import java.io.*;

public class FileReader{
  File f;

  public FileReader(String fileName){
    f = new File(fileName);
	}

	public Plant[] read() throws FileNotFoundException{
    //reading file
    Scanner sc1 = new Scanner(f);
    Scanner sc2;
    String id;
    String line;
    double[] d = new double[28];
    Plant[] p = new Plant[48];
    int counter = 0;

    line = sc1.nextLine(); // to get the unique_id thing out

    while(sc1.hasNextLine()){
      line = sc1.nextLine();

      sc2 = new Scanner(line).useDelimiter(",");
      id = sc2.next();

      for(int i = 0; i < 28; i++){
        d[i] = sc2.nextDouble();
      }
      p[counter]=new Plant(d, id);
      counter++;
    }
    return p;
	}
}
