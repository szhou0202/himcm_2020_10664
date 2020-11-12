package problemB;

import java.util.ArrayList;

public class FileReader{
  File f;

  public FileReader(String fileName){
    f = new File(fileName);
	}

	public Plant[] read(){
    //reading file
    Scanner sc1 = new Scanner(f);
    Scanner sc2 = new Scanner(f).useDelimiter(",");

    while(sc1.hasNextLine()){
      String line = sc1.
    }
	}
}
