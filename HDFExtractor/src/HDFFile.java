import javax.swing.tree.TreeNode;

import ncsa.hdf.hdflib.HDFConstants;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h5.H5File;

public class HDFFile {
	
	private String fileName;
	private boolean isH4;
	private boolean isH5;
	private FileFormat file;
	private int fileId;
	
	public HDFFile(String filename)
	{
		this.fileName = filename;
		
		String format = filename.substring(filename.lastIndexOf("."));
		if(format.equalsIgnoreCase(".hdf"))
		{
			this.isH4 = true;
			this.isH5 = false;
		}
		else if(format.equalsIgnoreCase(".h5"))
		{
			this.isH4 = false;
			this.isH5 = true;
		}
		else
		{
			this.isH4 = true;
			this.isH5 = false;
			//System.out.println("The format of file is not applicable");
		}
		
		this.fileId = -1;
		
	}
	
	public boolean openFile()
	{
		if(this.isH4)
		{
			this.file = new H4File(this.fileName,HDFConstants.DFACC_RDONLY);
		}
		else
		{
			this.file = new H5File(this.fileName,HDFConstants.DFACC_RDONLY);
		}
		
		try {
			this.fileId = file.open();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean createFile()
	{
		if(this.isH4)
		{
			this.file = new H4File(this.fileName,HDFConstants.DFACC_CREATE);
		}
		else
		{
			this.file = new H5File(this.fileName,HDFConstants.DFACC_CREATE);
		}
		
		try {
			this.fileId = this.file.open();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public TreeNode getRoot()
	{
		TreeNode root = null;
		root = file.getRootNode();
		
		return root;
	}
	
	public FileFormat getFile()
	{
		return this.file;
	}
	
	public void closeFile()
	{
		try {
			this.file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName()
	{
		return this.fileName;
	}

}
