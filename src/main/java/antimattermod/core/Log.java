package antimattermod.core;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

/**
 * ログクラス
 * <p>
 * さらば！{@link java.io.PrintStream#println() System.out.println()}
 *
 * @author Kamesuta
 */
public class Log {
    private static boolean isDebugEnabled = true;
    public static
    @Nonnull
    Logger log = LogManager.getLogger(AntiMatterModCore.MOD_ID);
    public static
    @Nonnull
    Logger dev = new DevLogger(log);

    private static class DevLogger extends AbstractLogger {
        private Logger logger;

        DevLogger(@Nonnull final Logger logger) {
            this.logger = logger;
        }

        private boolean isEnabled() {
            return isDebugEnabled;
        }

        @Override
        protected boolean isEnabled(final @Nullable Level level, final @Nullable Marker marker, final @Nullable Message data, final @Nullable Throwable t) {
            return isEnabled();
        }

        @Override
        protected boolean isEnabled(final @Nullable Level level, final @Nullable Marker marker, final @Nullable Object data, final @Nullable Throwable t) {
            return isEnabled();
        }

        @Override
        protected boolean isEnabled(final @Nullable Level level, final @Nullable Marker marker, final @Nullable String data) {
            return isEnabled();
        }

        @Override
        protected boolean isEnabled(final @Nullable Level level, final @Nullable Marker marker, final @Nullable String data, final @Nullable Object... p1) {
            return isEnabled();
        }

        @Override
        protected boolean isEnabled(final @Nullable Level level, final @Nullable Marker marker, final @Nullable String data, final @Nullable Throwable t) {
            return isEnabled();
        }

        @Override
        public void log(final @Nullable Marker marker, final @Nullable String fqcn, final @Nullable Level level, final @Nullable Message data, final @Nullable Throwable t) {
            if (isEnabled() && data != null)
                this.logger.log(level, marker, "[DEBUG] " + data.getFormattedMessage(), t);
        }
    }
}
