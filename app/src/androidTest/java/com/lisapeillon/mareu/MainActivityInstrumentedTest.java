package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lisapeillon.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.lisapeillon.mareu.Data.DummyMeetingsGenerator;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.utils.DeleteViewAction;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
          
          private MeetingRepository meetingRepository;
          private MeetingsAdapter meetingsAdapter;
          
          private static int ITEMS_COUNT = 5;

          @Rule
          public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
          
          @Rule
          public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

          @Before
          public void setup() throws InterruptedException {
                    MainActivity mainActivity = activityTestRule.getActivity();
                    assertThat(mainActivity, notNullValue());
                    meetingRepository = new MeetingRepository();
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting1());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting2());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting3());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting4());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting5());
                    LiveData<List<Meeting>> meetingList = meetingRepository.getMeetingList();
                    List<Meeting> result = LiveDataTestUtils.getValue(meetingList);
                    meetingsAdapter.updateMeetingList(result);
          }

          @Test
          public void useAppContext() {
                    // Context of the app under test.
                    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    assertEquals("com.lisapeillon.mareu", appContext.getPackageName());
          }
          
          @Test
          public void meetingList_shouldNotBeEmpty(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(hasMinimumChildCount(1)));
          }

          @Test
          public void meetingList_deleteAction_shouldRemoveItem(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(hasChildCount(ITEMS_COUNT)));
                    onView(withId(R.id.activity_main_recyclerview))
                              .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(hasChildCount(ITEMS_COUNT-1)));
          }
          
          @Test
          public void meetingList_onClickFab_shouldOpenAddMeetingActivity(){
                    onView(withId(R.id.activity_main_fab)).perform(click());
                    onView(withId(R.id.activity_addmeeting_rootview)).check(matches(isDisplayed()));
          }

          @Test
          public void meetingList_sortByRoomAction_shouldSortMeetingList(){

          }

          @Test
          public void meetingList_sortByDateAction_shouldSortMeetingList(){

          }
}