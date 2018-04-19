package mejust.frame.compiler.bean;

import java.util.HashMap;

/**
 * @author wangpeifeng
 * @date 2018/04/17 10:57
 */
public class TitleBarInfo {

    private String value;

    private int color;

    private float size;

    private int backgroundColor;

    private boolean isVisible;

    private HashMap<Integer, TitleBarMenuInfo> menuInfoHashMap = new HashMap<>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public HashMap<Integer, TitleBarMenuInfo> getMenuInfoHashMap() {
        return menuInfoHashMap;
    }

    public void addMenuInfo(TitleBarMenuInfo titleBarMenuInfo) {
        this.menuInfoHashMap.put(titleBarMenuInfo.getLocation(), titleBarMenuInfo);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString() {
        return "TitleBarInfo{"
                + "textValue='"
                + value
                + '\''
                + ", color="
                + color
                + ", size="
                + size
                + ", backgroundColor="
                + backgroundColor
                + ", isVisible="
                + isVisible
                + ", menuInfoHashMap="
                + menuInfoHashMap
                + '}';
    }

    public static class TitleBarMenuInfo {

        private String methodName;

        private int location;

        private int iconRes;

        private String text;

        private int textColor;

        private float textSize;

        public TitleBarMenuInfo(String methodName) {
            this.methodName = methodName;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getIconRes() {
            return iconRes;
        }

        public void setIconRes(int iconRes) {
            this.iconRes = iconRes;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public String getMethodName() {
            return methodName;
        }
    }
}
