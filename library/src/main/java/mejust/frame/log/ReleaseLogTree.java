package mejust.frame.log;

import android.support.annotation.NonNull;
import android.util.Log;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-20 14:40
 */

public class ReleaseLogTree extends Timber.DebugTree {

    @Override
    protected boolean isLoggable(String tag, int priority) {
        return priority == Log.WARN || priority == Log.ERROR || priority == Log.ASSERT;
    }

    @Override
    protected String createStackElementTag(@NonNull StackTraceElement element) {
        return super.createStackElementTag(element) + ":" + element.getLineNumber();
    }
}
