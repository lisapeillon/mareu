package com.lisapeillon.mareu.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RecyclerViewMatcher {
          public static Matcher<View> atPositionOnView(final int position, @NonNull final Matcher<View> itemMatcher, final int targetViewId) {
                    return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
                              @Override
                              public void describeTo(Description description) {
                                        description.appendText("has view id " + itemMatcher + " at position " + position);
                              }
                              
                              @Override
                              protected boolean matchesSafely(final RecyclerView recyclerView) {
                                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                                        View targetView = viewHolder.itemView.findViewById(targetViewId);
                                        return itemMatcher.matches(targetView);
                              }
                    };
          }
          
}
