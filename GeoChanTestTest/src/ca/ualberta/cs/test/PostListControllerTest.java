package ca.ualberta.cs.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public class PostListControllerTest extends ActivityInstrumentationTestCase2<Activity> {

	public PostListControllerTest() {
		super(Activity.class);
	}
	
	
	public void testControllerCreateTopicList(){
		PostListController.createTopicList(new UserModel("testUser"));
		assertNotSame("make sure objects are not all the same", TopicModelList.getInstance().getTopicModelArrayList().get(0), TopicModelList.getInstance().getTopicModelArrayList().get(1));
		
	}
}
