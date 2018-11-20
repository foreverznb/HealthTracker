package com.example.healthtracker;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddOrEditRecordView {

    private EditText title;
    private EditText description;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());

        // login
        EditText name = (EditText) solo.getView("userID");
        EditText pwd = (EditText) solo.getView("login_password");
        solo.enterText(name, "George12");
        solo.enterText(pwd, "password");
        solo.clickOnView(solo.getView("login_button"));
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testAddAndEditRecordWhileAddingProblem() throws Exception {

        solo.clickOnView(solo.getView("add_problem"));

        solo.waitForActivity(AddProblemView.class, 2000);
        solo.assertCurrentActivity("Problem activity", AddProblemView.class);

        solo.clickOnView(solo.getView("add_record_from_add_problem"));

        solo.waitForActivity(AddorEditRecordView.class, 2000);
        solo.assertCurrentActivity("Record activity", AddorEditRecordView.class);

        title = solo.getEditText(0);
        description = solo.getEditText(1);

        solo.enterText(title, "Day after injury.");
        solo.enterText(description, "Ankle has become more swollen.");

        String text = title.getText().toString();
        Assert.assertTrue(text.equals("Day after injury."));
        Assert.assertEquals("Ankle has become more swollen.", description.getText().toString());

        solo.clickOnView(solo.getView("save_record_button"));

        solo.waitForActivity(AddProblemView.class, 2000);
        solo.assertCurrentActivity("Problem", AddProblemView.class);

        solo.clickInList(0);
        solo.clickOnButton("Edit/View");

        solo.waitForActivity(AddorEditRecordView.class, 2000);
        solo.assertCurrentActivity("Record activity", AddorEditRecordView.class);

    }

    @Test
    public void testAddAndEditRecordWhileEditingProblem(){

        solo.clickOnView(solo.getView("view_problems"));

        solo.waitForActivity(ViewMyProblems.class, 2000);
        solo.assertCurrentActivity("Should be problem list.", ViewMyProblems.class);

        solo.clickInList(0);
        solo.clickOnButton("Edit/View");

        solo.waitForActivity(EditProblem.class, 2000);
        solo.assertCurrentActivity("Shold be edit problem activity.", EditProblem.class);

        solo.clickOnView(solo.getView("add_record_from_edit_problem"));

        solo.waitForActivity(AddorEditRecordView.class, 2000);
        solo.assertCurrentActivity("Should be Add record activity.", AddorEditRecordView.class);

        title = solo.getEditText(0);
        description = solo.getEditText(1);

        solo.enterText(title, "Day after injury.");
        solo.enterText(description, "Ankle has become more swollen.");

        String text = title.getText().toString();
        Assert.assertTrue(text.equals("Day after injury."));
        Assert.assertEquals("Ankle has become more swollen.", description.getText().toString());

        solo.clickOnView(solo.getView("save_record_button"));

        solo.waitForActivity(EditProblem.class, 2000);
        solo.assertCurrentActivity("Problem", EditProblem.class);

        solo.clickInList(0);
        solo.clickOnButton("Edit/View");

        solo.waitForActivity(AddorEditRecordView.class, 2000);
        solo.assertCurrentActivity("Record activity", AddorEditRecordView.class);

    }
}
