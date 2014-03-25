package ca.ualberta.cs.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import ca.ualberta.cs.providers.GeoChanGson;

import com.google.gson.Gson;

/*
 * A followed post model is a post model that has been marked as favorite or read later. These
 * models will not be networked, and only saved to the device.
 */
abstract public class FollowingPostModelList<T extends PostModel> extends
		PostModelList<T> implements UpdateableListInterface {
	
	private Context applicationContext;
	
	protected FollowingPostModelList(Context applicationContext) {
		this.applicationContext = applicationContext;
		
		load();
	}
	
	/**
	 * @return 
	 */
	protected abstract String getFilenameString();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.UpdateableListInterface#updateFromNetwork()
	 */
	@Override
	public void updateFromNetwork() {
		// TODO Auto-generated method stub

	}

	private void save() {
		Gson gson = GeoChanGson.getGson();
		
		T[] dataToSave = arrayListToArray();

		try {
			String FILENAME = getFilenameString();
			FileOutputStream fos = applicationContext.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			gson.toJson(dataToSave, osw);

			osw.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract protected T[] arrayListToArray();
	abstract protected T[] inputStreaReaderToArray(InputStreamReader isr);

	private void load() {
		ArrayList<T> dataThatLoaded = new ArrayList<T>();
						
		try {
			String FILENAME = getFilenameString();
			FileInputStream fis = applicationContext.openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);

			@SuppressWarnings("unchecked")
			T[] thePrimative = inputStreaReaderToArray(isr);

			isr.close();
			fis.close();
			
			for(int i = 0; i < thePrimative.length; i++) {
				dataThatLoaded.add(thePrimative[i]);
			}


		} catch (FileNotFoundException e) {
			// File was not found! Create it!
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.models.PostModelList#add(ca.ualberta.cs.models.PostModel)
	 */
	@Override
	public void add(T theModel) {
		// TODO Auto-generated method stub
		super.add(theModel);
		
		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.PostModelList#remove(int)
	 */
	@Override
	public void remove(int position) {
		// TODO Auto-generated method stub
		super.remove(position);
		
		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.PostModelList#update(int,
	 * ca.ualberta.cs.models.PostModel)
	 */
	@Override
	public void update(int position, T theModel) {
		// TODO Auto-generated method stub
		super.update(position, theModel);
		
		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.models.PostModelList#setArrayList(java.util.ArrayList)
	 */
	@Override
	public void setArrayList(ArrayList<T> postModelArrayList) {
		// TODO Auto-generated method stub
		super.setArrayList(postModelArrayList);
		
		save();
	}

}
