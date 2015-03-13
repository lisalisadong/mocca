package course.labs.activitylab.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import course.labs.activitylab.ActivityOne;

public class Test5_CloseActivityTwoTest extends ActivityInstrumentationTestCase2<ActivityOne> {
	
	private Solo solo;
	private int timeout = 20000;
	private int sleep = 1000;
	
	public Test5_CloseActivityTwoTest() {
		super(ActivityOne.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	// Executes the CloseActivityTwoTest
	public void testRun() {
		
		// ==================== Section One =====================
		// Wait for activity: 'course.labs.activitylab.ActivityOne'
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section One:" +
				   "ActivityOne did not load correctly",
				   solo.waitForActivity(course.labs.activitylab.ActivityOne.class, timeout));
		
		solo.sleep(sleep);
		
		// ==================== Section Two =====================
		// Click on Start Activity Two
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bLaunchActivityTwo));
		
		// Wait for activity: 'course.labs.activitylab.ActivityTwo'
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Two:" +
				   "ActivityTwo did not load correctly",
				   solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));
		
		solo.sleep(sleep);
		
		// ==================== Section Three =====================
		// Click on Close Activity
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bClose));

		
		// Wait for activity: 'course.labs.activitylab.ActivityOne'
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Three:" +
				   "ActivityTwo did not close correctly",
				   solo.waitForActivity(course.labs.activitylab.ActivityOne.class, timeout));
		
	
		solo.sleep(sleep);
		
		// Check for proper counts
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Three:" +
				   "onCreate() count was off for ActivityOne after ActivityTwo closed.",
				   solo.searchText("onCreate\\(\\) calls: 1"));
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Three:" +
				   "onStart() count was off for ActivityOne after ActivityTwo closed.",
				   solo.searchText("onStart\\(\\) calls: 2"));
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Three:" +
				   "onResume() count was off for ActivityOne after ActivityTwo closed.",
				   solo.searchText("onResume\\(\\) calls: 2"));
		assertTrue("CloseActivityTwoTest failed:" +
				   "Section Three:" +
				   "onRestart() count was off for ActivityOne after ActivityTwo closed.",
				   solo.searchText("onRestart\\(\\) calls: 1"));
		
	}

}
