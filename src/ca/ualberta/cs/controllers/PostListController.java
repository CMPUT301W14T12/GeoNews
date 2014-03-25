package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.TopicModelList;
/**
 * control the post arrays (?!)
 * @author vincent
 *
 */
public class PostListController {

	/* sort values */
	public static final int SORT_PROXIMITY = 0;
	public static final int SORT_PICTURE = 1;
	public static final int SORT_DATE	 = 2;
	public static final int SORT_SCORE = 3;
	public static final int SORT_DEFAULT = 4;
	
	public static void setSort(final int theSortOrder){
		switch (theSortOrder) {
		case SORT_DATE:
			TopicModelList.getInstance().sortByDate();
			break;
		case SORT_SCORE:
			TopicModelList.getInstance().sortByScore();
			break;
		case SORT_PROXIMITY:
			TopicModelList.getInstance().sortByLocation();
		default:
			break;
		}
	}
}
