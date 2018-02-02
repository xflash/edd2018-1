package org.xflash.edd;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.xflash.edd.model.Pill;

public class PillMatchers {
    static Matcher<? super Pill> isAPill(final Pill.Orientation orientation, final int x, final int y) {
        return new TypeSafeMatcher<Pill>() {
            public void describeTo(Description description) {
                description.appendText("a Pill : ")
                        .appendValue(orientation)
                        .appendText(" at ")
                        .appendValue(x)
                        .appendText(",")
                        .appendValue(y);
            }

            protected boolean matchesSafely(Pill item) {
                return item != null
                        && orientation.equals(item.o)
                        && x == item.x
                        && y == item.y
                        ;
            }
        };
    }
}
