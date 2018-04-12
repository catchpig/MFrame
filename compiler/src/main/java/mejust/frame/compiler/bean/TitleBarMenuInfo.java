package mejust.frame.compiler.bean;

/**
 * 创建时间: 2018/04/12
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/12
 * 描述: <empty/>
 */
public class TitleBarMenuInfo {
    /**
     * 显示位置
     */
    private int location;

    /**
     * 菜单图片
     */
    private int iconRes;

    /**
     * 菜单文本内容
     */
    private String text;

    /**
     * 文字的颜色
     */
    private int textColor;

    /**
     * 文字大小
     */
    private float textSize;

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
}
