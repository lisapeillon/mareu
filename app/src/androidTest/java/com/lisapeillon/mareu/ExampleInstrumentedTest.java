package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.ViewModel.AddMeetingViewModel;
import com.lisapeillon.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

          private MainActivity mainActivity;
          private AddMeetingActivity addMeetingActivity;
          private AddMeetingViewModel addMeetingViewModel;

          @Rule
          public ActivityTestRule<AddMeetingActivity> activityTestRule = new ActivityTestRule<>(AddMeetingActivity.class);

          @Before
          public void setup(){
                    addMeetingActivity = activityTestRule.getActivity();
                    assertThat(addMeetingActivity, notNullValue());
                    addMeetingViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelFactory.getInstance(addMeetingActivity.getApplicationContext())).get(AddMeetingViewModel.class);

          }

          @Test
          public void useAppContext() {
                    // Context of the app under test.
                    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    assertEquals("com.lisapeillon.mareu", appContext.getPackageName());
          }

          @Test
          public void meetingList_deleteAction_shouldRemoveItem(){
                    onView(withId(R.id.activity_main_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
          }

          @Test
          public void addMeetingActivity_onClickOnCreate_shouldAddMeetingOnList(){
                    onView(withId(R.id.activity_main_fab)).perform(click());
                    onView(withId(R.id.activity_addmeeting_rootview)).check(matches(isDisplayed()));

          }

          @Test
          //tri
}