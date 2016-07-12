import java.util.ArrayList;
import java.util.Scanner;

import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Group;

public class HDFExtractor {
	
	//Input and Output directory
	private String inDir;
	private String outDir;

	//File type(L2 or L3)
	private String filetype;
	
	//Time interval
	private String starttime;
	private String endtime;
	
	//Region index
	private int rStartIdx;
	private int rEndIdx;
	private int cStartIdx;
	private int cEndIdx;
	
	private int dim_x;
	private int dim_y;
	
	private ArrayList<HDFFile> fileList;
	private FileFilter fileFilter;
	
	//Output
	private String outFileName;
	private HDFFile outfile;
	
	//HDF Tools
	private HDFDataset hdfDataset;
	
	//Scanner
	private Scanner scan;
	
	public HDFExtractor(String inDir, String outDir,String filetype, String starttime, String endtime, int rStartIdx, int rEndIdx,int cStartIdx, int cEndIdx)
	{
		this.inDir = inDir;
		this.outDir = outDir;
		
		this.filetype = filetype;
		
		this.starttime = starttime;
		this.endtime = endtime;
		
		this.rStartIdx = rStartIdx;
		this.rEndIdx = rEndIdx;
		this.cStartIdx = cStartIdx;
		this.cEndIdx = cEndIdx;
		
		this.dim_x = this.rEndIdx-this.rStartIdx;
		this.dim_y = this.cEndIdx-this.cStartIdx;
		
		this.fileList = new ArrayList<HDFFile>();
		this.fileFilter = new FileFilter();
		
		//create the output file
		this.outFileName = "output.h5";
		this.outfile = new HDFFile(this.outDir+"/"+this.outFileName);
//		if(outfile.createFile())
//		{
//			System.out.println("Out file is created.");
//		}
//		else
//		{
//			System.out.println("Out file is not created.");
//		}
		
		this.hdfDataset = new HDFDataset();
		
		this.scan = new Scanner(System.in);
	}
	
	public float[] extractL2(HDFFile file)
	{
		float[] result = null;
		Group root = (Group)((javax.swing.tree.DefaultMutableTreeNode)file.getRoot()).getUserObject();
		
		//extract whole data of 'Geophysical data' group and select specific data
		Group gpData;
		
		for(int i=0;i<root.getMemberList().size();i++)
		{
			if(root.getMemberList().get(i).getFullName().equalsIgnoreCase("/Geophysical Data"))
			{
				gpData = (Group)root.getMemberList().get(i);
			}
		}
		//Dataset dataset = (Dataset)root.getMemberList().get(0);
		
		//extract data by using hyperslab
		
		return result;
	}
	
	public float[] extractL3(HDFFile file)
	{
		float[] result = null;
		Group root = (Group)((javax.swing.tree.DefaultMutableTreeNode)file.getRoot()).getUserObject();
		
		//extract whole data and select specific data
		Dataset dataset = (Dataset)root.getMemberList().get(0);
		
		
		//extract data by using hyperslab
		
		return result;
	}
	
	public void extractAndMerge()
	{
		//get the list of files that satisfy conditions
		this.fileList = this.fileFilter.filterFileOut(this.inDir, this.filetype, this.starttime, this.endtime);
		
		//The data that will be written to 3-dimensional dataset.
		float result[][] = new float[this.fileList.size()][this.dim_x*this.dim_y];
		
		//extract data from dataset according to the filetype
		if(this.filetype.equalsIgnoreCase("L2"))
		{
			for(int i=0;i<this.fileList.size();i++)
			{
				result[i] = this.extractL2(this.fileList.get(i));
			}
		}
		else if(this.filetype.equalsIgnoreCase("L3"))
		{
			for(int i=0;i<this.fileList.size();i++)
			{
				result[i] = this.extractL3(this.fileList.get(i));
			}
		}
		else
		{
			
		}
		
		//create 3-dimensional dataset
		
		
		
		for(int i=0;i<this.fileList.size();i++)
		{
			System.out.println(this.fileList.get(i).getName());
			
			
			
			Group root = (Group)((javax.swing.tree.DefaultMutableTreeNode)this.fileList.get(i).getRoot()).getUserObject();
			for(int j=0;j<root.getMemberList().size();j++)
			{
				System.out.println(root.getMemberList().get(j).getFullName());
			}
//			Dataset dataset = (Dataset)root.getMemberList().get(0);
//			dataset.hasAttribute();
//			try {
//				float[] data = (float[]) dataset.read();
//				long[] dims = dataset.getDims();
//				System.out.println(dims.length);
//				float[] data2 = (float[])dataset.getData();
//				System.out.println(data2.length);
//				System.out.println(data[20692804]);
//			} catch (OutOfMemoryError | Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println();
		}
	}

}
