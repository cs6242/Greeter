package edu.westga.greeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by wlloyd on 2/4/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testReverseButtonDisabledOnCreate() {
        MainActivity activity = getActivity();
        Button reverseButton = (Button) activity.findViewById(R.id.reverseButton);
        assertFalse(reverseButton.isEnabled());
    }

    public void testGreet() {
        MainActivity activity = getActivity();

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

        TextView greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);

        Button reverseButton = (Button) activity.findViewById(R.id.reverseButton);
        assertTrue(reverseButton.isEnabled());
    }

    public void testReverse() {
        MainActivity activity = getActivity();

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

        Button reverseButton =
                (Button) activity.findViewById(R.id.reverseButton);

        TouchUtils.clickView(this, reverseButton);

        TextView reversed =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = reversed.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);
    }


}
