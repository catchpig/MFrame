package mejust.frame.utils.log;

import android.support.annotation.NonNull;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-20 14:36
 */

public class Logger {

    private Logger() {
    }

    /** Log a verbose message with optional format args. */
    public static void v(@NonNull String message, Object... args) {
        Timber.v(message, args);
    }

    /** Log a verbose exception and a message with optional format args. */
    public static void v(Throwable t, @NonNull String message, Object... args) {
        Timber.v(t, message, args);
    }

    /** Log a verbose message with optional format args and tag. */
    public static void v(String tag, @NonNull String message, Object... args) {
        Timber.tag(tag).v(message, args);
    }

    /** Log a verbose exception. */
    public static void v(Throwable t) {
        Timber.v(t);
    }

    /** Log a debug message with optional format args. */
    public static void d(@NonNull String message, Object... args) {
        Timber.d(message, args);
    }

    /** Log a debug exception and a message with optional format args. */
    public static void d(Throwable t, @NonNull String message, Object... args) {
        Timber.d(t, message, args);
    }

    /** Log a debug message with optional format args and tag. */
    public static void d(String tag, @NonNull String message, Object... args) {
        Timber.tag(tag).d(message, args);
    }

    /** Log a debug exception. */
    public static void d(Throwable t) {
        Timber.d(t);
    }

    /** Log an info message with optional format args. */
    public static void i(@NonNull String message, Object... args) {
        Timber.i(message, args);
    }

    /** Log an info exception and a message with optional format args. */
    public static void i(Throwable t, @NonNull String message, Object... args) {
        Timber.i(t, message, args);
    }

    /** Log a info message with optional format args and tag. */
    public static void i(String tag, @NonNull String message, Object... args) {
        Timber.tag(tag).i(message, args);
    }

    /** Log an info exception. */
    public static void i(Throwable t) {
        Timber.i(t);
    }

    /** Log a warning message with optional format args. */
    public static void w(@NonNull String message, Object... args) {
        Timber.w(message, args);
    }

    /** Log a warning exception and a message with optional format args. */
    public static void w(Throwable t, @NonNull String message, Object... args) {
        Timber.w(t, message, args);
    }

    /** Log a warning message with optional format args and tag. */
    public static void w(String tag, @NonNull String message, Object... args) {
        Timber.tag(tag).w(message, args);
    }

    /** Log a warning exception. */
    public static void w(Throwable t) {
        Timber.w(t);
    }

    /** Log an error message with optional format args. */
    public static void e(@NonNull String message, Object... args) {
        Timber.e(message, args);
    }

    /** Log an error exception and a message with optional format args. */
    public static void e(Throwable t, @NonNull String message, Object... args) {
        Timber.e(t, message, args);
    }

    /** Log a error message with optional format args and tag. */
    public static void e(String tag, @NonNull String message, Object... args) {
        Timber.tag(tag).e(message, args);
    }

    /** Log an error exception. */
    public static void e(Throwable t) {
        Timber.e(t);
    }
}
