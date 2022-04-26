package com.lisapeillon.mareu;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.lisapeillon.mareu.Data.DummyMeetingsGenerator;
import com.lisapeillon.mareu.Repositories.MeetingRepository;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityInstrumentedTest {
          
          private MeetingRepository meetingRepository;
          
          @Rule
          public ActivityTestRule<AddMeetingActivity> activityTestRule = new ActivityTestRule<>(AddMeetingActivity.class);
          
          @Before
          public void setup(){
                    AddMeetingActivity addMeetingActivity = activityTestRule.getActivity();
                    assertThat(addMeetingActivity, notNullValue());
                    meetingRepository = new MeetingRepository();
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting1());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting2());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting3());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting4());
                    meetingRepository.createMeeting(DummyMeetingsGenerator.getMeeting5());
          }
          
          @Test
          public void useAppContext() {
                    // Context of the app under test.
                    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    assertEquals("com.lisapeillon.mareu", appContext.getPackageName());
          }
          
          @Test
          public void addMeetingActivity_onClickOnCreate_shouldAddMeetingOnList(){
                    //Check if AddMeetingActivity is displayed
                    onView(withId(R.id.activity_addmeeting_rootview)).check(matches(isDisplayed()));
                    //Subject
                    onView(withId(R.id.activity_addmeeting_edittext_subject)).perform(click());
                    onView(withId(R.id.activity_addmeeting_edittext_subject)).perform(replaceText("Sujet de la réunion"));
                    //Date
                    onView(withId(R.id.activity_addmeeting_edittext_datepicker)).perform(click());
                    onView(withText("30")).perform(click());
                    //onView(withClassName(Matchers.equalTo(MaterialDatePicker.class.getName()))).perform(PickerActions.setDate(2022,4,30));
                    onView(withText("OK")).perform(click());
                    //Time
                    onView(withId(R.id.activity_addmeeting_textinputlayout_timepicker)).perform(click());
                    onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10,30));
                    onView(withText("OK")).perform(click());
                    //Room
                    onView(withId(R.id.activity_addmeeting_spinner_room)).perform(click());
                    onData(anything()).atPosition(1).perform(click());
                    //Guests
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(click());
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(replaceText("email1@mail.com"));
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(pressImeActionButton());
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(replaceText("email2@mail.com"));
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(pressImeActionButton());
                    onView(withId(R.id.activity_addmeeting_edittext_email)).perform(closeSoftKeyboard());
                    //Validate
                    onView(withId(R.menu.menu_add_meeting)).perform(click());
          }
}
