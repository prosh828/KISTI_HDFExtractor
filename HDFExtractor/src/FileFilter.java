import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.Group;

public class FileFilter {
	
	public FileFilter()
	{
		
	}
	
	public ArrayList<HDFFile> filterFileOut(String filepath, String format, String starttime, String endtime)
	{
		ArrayList<HDFFile> result = new ArrayList<HDFFile>();
		
		File folder = new File(filepath);
		File[] flist = folder.listFiles();
		
		for(int i=0;i<flist.length;i++)
		{
			if(flist[i].isDirectory())
			{
				
			}
			else
			{
				HDFFile hFile = new HDFFile(flist[i].getAbsolutePath());
				if(this.checkProperFile(hFile, format, starttime, endtime))
				{
					result.add(hFile);
				}
			}
		}
		
		
		return result;
	}
	
	
	public boolean checkProperFile(HDFFile hFile, String format, String starttime,String endtime)
	{
		if(hFile.openFile())
		{
			Group root = (Group)((javax.swing.tree.DefaultMutableTreeNode)hFile.getRoot()).getUserObject();
			List attrList = null;
			Attribute title = null;
			Attribute stime = null;
			try {
				attrList = root.getMetadata();
				for(int i=0;i<attrList.size();i++)
				{
					Attribute temp = (Attribute)attrList.get(i);
					if(temp.getName().equalsIgnoreCase("Title"))
					{
						title = temp;
					}
					else if(temp.getName().equalsIgnoreCase("Start Time"))
					{
						stime = temp;
					}
					else
					{
						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(title == null || stime == null)
			{
				return false;
			}
			
			if(format.equalsIgnoreCase("L2") && title.toString("").contains("Level-2") 
					&& stime.toString("").compareTo(starttime)>0 
					&& stime.toString("").compareTo(endtime)<0)
			{
				return true;
			}
			else if(format.equalsIgnoreCase("L3") && title.toString("").contains("Level-3")
					&& stime.toString("").compareTo(starttime)>0 
					&& stime.toString("").compareTo(endtime)<0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
		return false;
	}

}
