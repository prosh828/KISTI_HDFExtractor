
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//Constructor parameter: Input folder,Output folder, File type, start time, end time, row start index, row end index, column start index, column end index
		HDFExtractor hdfex = new HDFExtractor("/home/sehokim/KISTI/Sample/HDF/HDF_sample1","/home/sehokim/KISTI/extracted","L3","2003000004506667", "2003020004506667",300,400,300,400);
		
		hdfex.extractAndMerge();

		
		
		
		
		//HDF5File file1 = new HDF5File("A20030012003008.L3m_8D_CHL_chlor_a_4km");
		//file1.openFile("A20030012003008.L3m_8D_CHL_chlor_a_4km");
	}

}
