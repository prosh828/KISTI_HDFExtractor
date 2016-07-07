import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ncsa.hdf.object.Attribute;
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
	
	private ArrayList<HDFFile> fileList;
	private FileFilter fileFilter;
	
	//Output
	private String outFileName;
	private HDFFile outfile;
	
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
		
		this.fileList = new ArrayList<HDFFile>();
		this.fileFilter = new FileFilter();
		
		this.outFileName = "output.h5";
		this.outfile = new HDFFile(this.outDir+"/"+this.outFileName);
		if(outfile.createFile())
		{
			
		}
		else
		{
			System.out.println("Out file is not created.");
		}
	}
	
	public void extractL2()
	{
		
	}
	
	public void extractL3()
	{
		
	}
	
	public void extractAndMerge()
	{
		//get the list of files that satisfy conditions
		this.fileList = this.fileFilter.filterFileOut(this.inDir, this.filetype, this.starttime, this.endtime);
		
		//create 3-dimensional dataset
		
		
		
		for(int i=0;i<this.fileList.size();i++)
		{
			System.out.println(this.fileList.get(i).getName());
			
			
			
//			System.out.println(this.fileList.get(i).getFile().getName());
//			Group root = (Group)((javax.swing.tree.DefaultMutableTreeNode)this.fileList.get(i).getRoot()).getUserObject();
////			for(int j=0;j<root.getMemberList().size();j++)
////			{
////				System.out.println(root.getMemberList().get(j).getFullName());
////			}
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
//			System.out.println();
		}
	}

}
