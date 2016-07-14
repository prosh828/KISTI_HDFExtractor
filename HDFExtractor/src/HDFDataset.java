import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.exceptions.HDF5Exception;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;
import ncsa.hdf.object.Dataset;

public class HDFDataset {
	
	public HDFDataset()
	{
		
	}
	
	public void writeDataset()
	{
		
	}
	
	public float[] readFloatData(Dataset dset, int rStartIdx, int rEndIdx, int cStartIdx, int cEndIdx)
	{
		float[] result = null;
		int dset_id = dset.open();
		int filespace_id = -1;
		float[] dset_data = new float[(rEndIdx-rStartIdx)*(cEndIdx-cStartIdx)];
		long[] dims = {rEndIdx-rStartIdx,cEndIdx-cStartIdx};
		
		
		try {
			if (dset_id >= 0) {
				
				filespace_id = H5.H5Screate_simple(2, dims, null);
	
				long[] start = { rStartIdx, cStartIdx };
				long[] stride = { 1, 1 };
				long[] count = { 1, 1 };
				long[] block = { rEndIdx-rStartIdx, cEndIdx-cStartIdx };
	
				if (filespace_id >= 0) {
					H5.H5Sselect_hyperslab(filespace_id, HDF5Constants.H5S_SELECT_SET,
							start, stride, count, block);
	
					// Read the data using the previously defined hyperslab.
					if ((dset_id >= 0) && (filespace_id >= 0))
						H5.H5Dread(dset_id, HDF5Constants.H5T_NATIVE_FLOAT,
								HDF5Constants.H5S_ALL, filespace_id, HDF5Constants.H5P_DEFAULT,
								dset_data);
				}
			}
			
			result = dset_data;
			
		} catch (NullPointerException | HDF5Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		return result;
	}
	
	public float[] concatFloatArray(float[] a,float[] b)
	{
		if(a == null)
		{
			return b;
		}
		
		if(b == null)
		{
			return a;
		}
		
		float[] result = new float[a.length+b.length];
		
		int i=0;
		
		for(i=0;i<a.length;i++)
		{
			result[i] = a[i];
		}
		
		for(int j=i;j<result.length;j++)
		{
			result[j] = b[j-i];
		}
		
		return result;
	}

}
