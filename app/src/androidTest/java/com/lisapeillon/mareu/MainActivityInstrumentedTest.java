package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.lisapeillon.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.lisapeillon.mareu.utils.RecyclerViewMatcher.atPositionOnView;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lisapeillon.mareu.Data.DummyMeetingsGenerator;
import com.lisapeillon.mareu.Injections.DI;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
          
          private MeetingRepository meetingRepository;
          
          private static int ITEMS_COUNT = 5;

          @Rule
          public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

          @Before
          public void setup() {
                    MainActivity mainActivity = activityTestRule.getActivity();
                    assertThat(mainActivity, notNullValue());
                    meetingRepository = DI.getMeetingRepository();
          }

          @Test
          public void useAppContext() {
                    // Context of the app under test.
                    Context appContext = getInstrumentation().getTargetContext();
                    assertEquals("com.lisapeillon.mareu", appContext.getPackageName());
          }
          
          @Test
          public void meetingList_shouldNotBeEmpty(){
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting1());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting2());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting3());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting4());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting5());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(hasMinimumChildCount(1)));
          }

          @Test
          public void meetingList_deleteAction_shouldRemoveItem(){
                    onView(withId(R.id.activity_main_recyclerview)).check(withItemCount(ITEMS_COUNT));
                    onView(withId(R.id.activity_main_recyclerview))
                              .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
                    onView(withId(R.id.activity_main_recyclerview)).check(withItemCount(ITEMS_COUNT-1));
          }
          
          @Test
          public void meetingList_onClickFab_shouldOpenAddMeetingActivity(){
                    onView(withId(R.id.activity_main_fab)).perform(click());
                    onView(withId(R.id.activity_addmeeting_rootview)).check(matches(isDisplayed()));
          }

          @Test
          public void meetingList_sortByRoomAction_shouldSortMeetingList(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText(String.valueOf('E')), R.id.recyclerview_row_textview_roomletter)));
                    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
                    onView(withText(R.string.sortbyroom)).perform(click());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText(String.valueOf('B')), R.id.recyclerview_row_textview_roomletter)));
          }

          @Test
          public void meetingList_sortByDateAction_shouldSortMeetingList(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText("23/02/2022 - 14:00"), R.id.recyclerview_row_textview_datehour)));
                    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
                    onView(withText(R.string.sortbydate)).perform(click());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText("24/01/2022 - 08:15"), R.id.recyclerview_row_textview_datehour)));
          }
}