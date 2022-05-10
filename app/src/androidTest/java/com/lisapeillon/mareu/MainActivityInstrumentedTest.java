package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.lisapeillon.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.lisapeillon.mareu.utils.RecyclerViewMatcher.atPositionOnView;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.lisapeillon.mareu.Injections.DI;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
          
          private MeetingRepository meetingRepository;
          
          private static int ITEMS_COUNT = 4;

          @Rule
          public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

          @Before
          public void setup() {
                    MainActivity mainActivity = activityTestRule.getActivity();
                    assertThat(mainActivity, notNullValue());
                    meetingRepository = DI.getNewInstanceMeetingRepository();
          }

          @Test
          public void useAppContext() {
                    // Context of the app under test.
                    Context appContext = getInstrumentation().getTargetContext();
                    assertEquals("com.lisapeillon.mareu", appContext.getPackageName());
          }
          
          @Test
          public void meetingList_shouldNotBeEmpty(){
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
                    onView(withId(R.id.menu_sort)).perform(click());
                    onView(withText(R.string.sortbyroom)).perform(click());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText(String.valueOf('B')), R.id.recyclerview_row_textview_roomletter)));
          }

          @Test
          public void meetingList_sortByDateAction_shouldSortMeetingList(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText("11/04/2022 - 16:34"), R.id.recyclerview_row_textview_datehour)));
                    onView(withId(R.id.menu_sort)).perform(click());
                    onView(withText(R.string.sortbydate)).perform(click());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText("24/01/2022 - 08:15"), R.id.recyclerview_row_textview_datehour)));
          }
          
          @Test
          public void meetingList_filterByRoomAction_shouldFilterMeetingList(){
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText(String.valueOf('E')), R.id.recyclerview_row_textview_roomletter)));
                    onView(withId(R.id.menu_filter)).perform(click());
                    onView(withText(R.string.filterbyroom)).perform(click());
                    onView(withText("Salle D")).perform(click());
                    onView(withId(R.id.activity_main_recyclerview)).check(matches(atPositionOnView(0, withText(String.valueOf('D')), R.id.recyclerview_row_textview_roomletter)));
          }
}