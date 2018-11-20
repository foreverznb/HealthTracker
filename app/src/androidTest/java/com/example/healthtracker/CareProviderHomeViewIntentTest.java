package com.example.healthtracker;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CareProviderHomeViewIntentTest {


    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testButtons() throws Exception {
        EditText name = (EditText) solo.getView("userID");
        EditText pwd = (EditText) solo.getView("login_password");
        solo.enterText(name, "");
        solo.enterText(pwd, "");
        solo.enterText(name, "foreverznb");
        solo.enterText(pwd, "ZHUNINGBO1234");
        solo.clickOnView(solo.getView("CareGiverLogin"));
        solo.clickOnView(solo.getView("login_button"));
        //solo.clickOnButton("add_problem");
        solo.clickOnView(solo.getView(R.id.add_patient));
        boolean result_1 = solo.waitForActivity(AddPatientView.class, 2000);
        Assert.assertEquals(true, result_1);
        solo.goBack();
        solo.clickOnView(solo.getView(R.id.view_patients));
        boolean result_2 = solo.waitForActivity(ViewPatients.class, 2000);
        Assert.assertEquals(true, result_2);
        solo.goBack();
        solo.goBack();
        solo.clickOnView(solo.getView(R.id.setting));
        boolean result_4 = solo.waitForActivity(UserSettingsActivity.class, 2000);
        Assert.assertEquals(true, result_4);
        solo.goBack();
        solo.clickOnView(solo.getView(R.id.logout_button2));
        boolean result_3 = solo.waitForActivity(LoginActivity.class, 2000);
        Assert.assertEquals(true, result_3);





    }
}


