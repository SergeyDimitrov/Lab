package draw;

import com.sun.glass.ui.Size;

import java.awt.*;

public class ScreenUtils {
    public static Size getScreenSize() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        return new Size(dm.getWidth(), dm.getHeight());
    }
}
