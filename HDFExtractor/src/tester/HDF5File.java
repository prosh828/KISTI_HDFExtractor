package tester;

import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;
import ncsa.hdf.hdflib.HDFConstants;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h5.H5File;

/**
 * HDF5 File에 대한 전반적인 처리를 담당하는 Class. 주요 기능은 다음과 같다.
 * 	- HDF5 파일의 이름과 id값을 저장한다.
 * 	- 주어진 파일 이름으로 HDF5 파일을 생성한다.
 * 	- 주어진 파일 id에 해당하는 HDF5 파일을 닫는다.
 * 	- 주어진 파일 이름으로 해당하는 HDF5 파일이 있을 경우 파일을 연다.
 * @author sehokim
 *
 */
public class HDF5File {
	
	private String fileName;
	private int fileId;
	
	/**
	 * 생성자
	 * HDF5 File의 이름을 받아서 저장한다.
	 * @param fileName	HDF5 File의 이름
	 */
	public HDF5File(String fileName)
	{
		this.fileName = fileName;
		this.fileId = -1;
	}
	
	/**
	 * HDF5 File을 생성한다.
	 * @return	HDF5 File id
	 */
	public int createFile()
	{
		try {
			//flag option을 H5F_ACC_TRUNC로 하면 파일을 덮어쓰고, 
			//H5F_ACC_EXCL로 하면 덮어쓰지 않는다.
			this.fileId = H5.H5Fcreate(this.fileName, HDF5Constants.H5F_ACC_TRUNC,
					HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
			System.out.println("The file is created: "+this.fileName);
		} catch (HDF5LibraryException | NullPointerException e) {
			System.out.println("Failed to create hdf5 file: "+fileName);
			e.printStackTrace();
		}
		return this.fileId;
	}
	
	/**
	 * HDF5 File을 닫는다.
	 * @param fileId	HDF5 File id
	 */
	public void closeFile(int fileId)
	{
		try {
			if(fileId >= 0)
			{
				H5.H5Fclose(fileId);
				System.out.println("The hdf5 file is closed");
			}
		} catch (HDF5LibraryException e) {
			System.out.println("Failed to close hdf5 file: "+fileName);
		}
		
	}
	
	/**
	 * 저장된 file이름을 가진 HDF5 File을 open한다.
	 * @return	HDF5 File id
	 */
	public H5File openFile(String filename)
	{
//		FileFormat fileformat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF4);
//		if(fileformat == null)
//		{
//			System.err.println("Cannot find HDF5 FileFormat");
//			return null;
//		}
		
//		try {
//			
//			int faplist_id = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);  
//			H5.H5Pset_fapl_stdio(faplist_id);
//			
//			//If you use H5F_ACC_RDWR, read and write access to file are allowed
//			//If you use H5F_ACC_RDONLY, read-only access to file are allowed
//			this.fileId = H5.H5Fopen(this.fileName, HDF5Constants.H5F_ACC_RDONLY, faplist_id);
//		} catch (HDF5LibraryException | NullPointerException e) {
//			System.out.println("Failed to open hdf5 file: "+fileName);
//			e.printStackTrace();
//		}
		
		H4File h4file = new H4File(filename,HDFConstants.DFACC_RDONLY);
		
		try {
			h4file.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javax.swing.tree.MutableTreeNode root = (MutableTreeNode) h4file.getRootNode();
        if (root != null)
        {
            printNode(root, "    ");
        }
        
        
        
        try { h4file.close(); }
        catch (Exception ex ) {}
		
		return null;
	}
	
	// print out the data object recusively
    private static void printNode(javax.swing.tree.TreeNode node, String indent)
    {
        System.out.println(indent+node);

        int n = node.getChildCount();
        for (int i=0; i<n; i++)
        {
            printNode(node.getChildAt(i), indent+"    ");
        }
    }
	
	public int[] getObjIds()
	{
		int[] result = null;
		try {
			H5.H5Fget_obj_ids(this.fileId, HDF5Constants.H5F_OBJ_DATASET, 10, result);
		} catch (HDF5LibraryException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 객체가 포함하는 file id를 반환한다.
	 * @return	HDF5 File id 
	 */
	public int getFileId()
	{
		return this.fileId;
	}
	
	

}

