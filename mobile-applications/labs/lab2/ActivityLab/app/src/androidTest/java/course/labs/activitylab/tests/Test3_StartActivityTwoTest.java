package course.labs.activitylab.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import course.labs.activitylab.ActivityOne;

public class Test3_StartActivityTwoTest extends ActivityInstrumentationTestCase2<ActivityOne> {
	
	private Solo solo;
	private int timeout = 20000;
	private int sleep = 1000;

	public Test3_StartActivityTwoTest() {
		super(ActivityOne.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	// Executes the StartActivityTwoTest
	public void testRun() {
		
		// ==================== Section One =====================
		// Wait for activity: 'course.labs.activitylab.ActivityOne'
		assertTrue("StartActivityTwoTest failed:" +
				   "Section One:" +
				   "ActivityOne did not load correctly",
				   solo.waitForActivity(course.labs.activitylab.ActivityOne.class, timeout));

		solo.sleep(sleep);
		
		// ==================== Section Two =====================
		// Click on Start Activity Two
		solo.clickOnView(solo
				.getView(course.labs.activitylab.R.id.bLaunchActivityTwo));
		
		// Wait for activity: 'course.labs.activitylab.ActivityTwo'
		assertTrue("StartActivityTwoTest failed:" +
				   "Section Two:" +
				   "ActivityTwo did not load correctly",
				   solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));
		
		solo.sleep(sleep);
		
		
		// Check for proper counts after ActivityTwo has been opened
		assertTrue("StartActivityTwoTest failed:" +
				"Section Two:" +
				"onCreate() count was off for ActivityTwo.",
				solo.searchText("onCreate\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed:" +
				"Section Two:" +
				"onStart() count was off for ActivityTwo.",
				solo.searchText("onStart\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed:" +
				"Section Two:" +
				"onResume() count was off for ActivityTwo.",
				solo.searchText("onResume\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed:" +
				"Section Two:" +
				"onRestart() count was off for ActivityTwo.",
				solo.searchText("onRestart\\(\\) calls: 0"));
	}

}
