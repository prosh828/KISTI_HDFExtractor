
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.getProperty("sun.arch.data.model");
//		
//		System.out.println(System.getProperty("java.library.path"));
//		System.load("/usr/lib/jarhdf5-2.11.0.jar");
		
		
		Scanner scan = new Scanner(System.in);
		String inFolder, outFolder, fileType, starttime, endtime;
		int rStartIdx, rEndIdx, cStartIdx, cEndIdx;
		
		
		
		System.out.println("##########################################");
		System.out.println("################HDFExtractor##############");
		System.out.println("##########################################");
		System.out.println();
		
		//get input folder
		System.out.print("Input the input folder: ");
		inFolder = scan.nextLine();
		inFolder = "/home/sehokim/KISTI/Sample/HDF/HDF_sample2";
		
		//get output folder
		System.out.print("\nInput the output folder: ");
		outFolder = scan.nextLine();
		outFolder = "/home/sehokim/KISTI/extracted";
		
		//get file type
		System.out.print("\nInput the file type (L2/L3): ");
		fileType = scan.nextLine();
		fileType = "L2";
		
		//get start time
		System.out.print("\nInput the started time (16 digits): ");
		starttime = scan.nextLine();
		starttime = "2010000004506667";
		
		//get end time
		System.out.print("\nInput the ended time (16 digits): ");
		endtime = scan.nextLine();
		endtime = "2010020004506667";
		
		//get row index
		System.out.print("\nInput the start index of row: ");
		rStartIdx = scan.nextInt();
		rStartIdx = 300;
		System.out.print("\nInput the end index of row: ");
		rEndIdx = scan.nextInt();
		rEndIdx = 305;
		
		//get row index
		System.out.print("\nInput the start index of column: ");
		cStartIdx = scan.nextInt();
		cStartIdx = 300;
		System.out.print("\nInput the end index of column: ");
		cEndIdx = scan.nextInt();
		cEndIdx = 305;
		
		
		
		//Constructor parameter: Input folder,Output folder, File type, start time, end time, row start index, row end index, column start index, column end index
		HDFExtractor hdfex = new HDFExtractor(inFolder,outFolder,fileType,starttime, endtime,rStartIdx,rEndIdx,cStartIdx,cEndIdx);
		hdfex.extractAndMerge();
		
		
		//close the scanner
		scan.close();
	}

}
