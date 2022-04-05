package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.lisapeillon.mareu.utils.DeleteViewAction;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

          private MainActivity mainActivity;
          private AddMeetingActivity addMeetingActivity;

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
}