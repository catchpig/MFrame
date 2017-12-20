package mejust.frame.utils.log;

import android.support.annotation.NonNull;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-20 14:41
 */

public class DebugLogTree extends Timber.DebugTree {

    private static final int CALL_STACK_INDEX = 7;

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        //tag was originally come from Timber.tag(),so we just ignore.
        String mConstTag = tag.equals("Logger") ? null : tag;
        StackTraceElement element = callingElement();
        String newTag = mConstTag != null ? mConstTag : getCallingClassName(element);
        if (mConstTag == null) {
            newTag += ":" + element.getLineNumber();
        }
        super.log(priority, newTag, message, t);
    }

    private StackTraceElement callingElement() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        return stackTrace[CALL_STACK_INDEX];
    }

    private String getCallingClassName(StackTraceElement element) {
        return createStackElementTag(element);
    }
}
