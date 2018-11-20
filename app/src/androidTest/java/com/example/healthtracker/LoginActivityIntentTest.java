package com.example.healthtracker;


import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityIntentTest {
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
    public void testLogin() throws Exception {
        //solo.clickOnView(solo.getView("create_account"));
        solo.clickOnButton(0);
        boolean result_2 = solo.waitForActivity(CreateAccountActivity.class, 1000);
        Assert.assertEquals(true, result_2);
        solo.goBack();
        EditText name = (EditText) solo.getView("userID");
        EditText pwd = (EditText) solo.getView("login_password");
        solo.enterText(name, "");
        solo.enterText(pwd, "");
        solo.enterText(name, "foreverznb");
        solo.enterText(pwd, "ZHUNINGBO1234");
        solo.clickOnView(solo.getView("login_button"));
        boolean result = solo.waitForActivity(PatientHomeView.class, 2000);
        Assert.assertEquals(true, result);
        solo.goBack();
        solo.clickOnView(solo.getView("CareGiverLogin"));
        solo.clickOnView(solo.getView("login_button"));
        boolean result_1 = solo.waitForActivity(CareProviderHomeView.class, 2000);
        Assert.assertEquals(true, result_1);


    }

}
