package mejust.frame.compiler.bean;

/**
 * 创建时间: 2018/04/12
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/12
 * 描述: <empty/>
 */
public class TitleBarInfo {
    /**
     * 标题内容
     */
    private String value;

    /**
     * 文字的颜色
     */
    private int color;

    /**
     * 文字大小
     */
    private float size;

    /**
     * 背景色
     */
    private int backgroundColor;

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
}
