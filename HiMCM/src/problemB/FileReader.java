package problemB;

public class FileReader{
  File f;

  public FileReader(String fileName){
    f = new File(fileName);
	}

	public Plant[] read(){
    Scanner sc = new Scanner(f).useDelimiter(",");

    while(sc.hasNext()){
      
    }


	}
}
