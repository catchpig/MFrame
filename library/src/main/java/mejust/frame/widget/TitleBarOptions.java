package mejust.frame.widget;

import java.io.Serializable;

/**
 * 创建时间:2017-12-21 11:28<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 11:28<br/>
 * 描述: TitleBar的数据类
 */

public class TitleBarOptions implements Serializable {

    private static final long serialVersionUID = -1832714116452199912L;

    /**
     * 标题文字
     */
    private String titleString;

    /**
     * 标题文字颜色
     */
    private int titleStringColor;

    /**
     * 标题文字大小
     */
    private int titleStringSize;

    /**
     * 左边文字
     */
    private String textLeft;

    /**
     * 左边文字颜色
     */
    private int textLeftColor;

    /**
     * 左边文字大小
     */
    private int textLeftSize;

    /**
     * 右边文字
     */
    private String textRight;

    /**
     * 右边文字颜色
     */
    private int textRightColor;

    /**
     * 右边文字大小
     */
    private int textRightSize;

    /**
     * 左边主图标，左起第一个
     */
    private int imgLeftMainId;

    /**
     * 左边次要图标，左起第二个
     */
    private int imgLeftMinorId;

    /**
     * 右边主图标，右起第一个
     */
    private int imgRightMainId;

    /**
     * 右边次要图标，右起第二个
     */
    private int imgRightMinorId;

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public int getTitleStringColor() {
        return titleStringColor;
    }

    public void setTitleStringColor(int titleStringColor) {
        this.titleStringColor = titleStringColor;
    }

    public int getTitleStringSize() {
        return titleStringSize;
    }

    public void setTitleStringSize(int titleStringSize) {
        this.titleStringSize = titleStringSize;
    }

    public String getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(String textLeft) {
        this.textLeft = textLeft;
    }

    public int getTextLeftColor() {
        return textLeftColor;
    }

    public void setTextLeftColor(int textLeftColor) {
        this.textLeftColor = textLeftColor;
    }

    public int getTextLeftSize() {
        return textLeftSize;
    }

    public void setTextLeftSize(int textLeftSize) {
        this.textLeftSize = textLeftSize;
    }

    public String getTextRight() {
        return textRight;
    }

    public void setTextRight(String textRight) {
        this.textRight = textRight;
    }

    public int getTextRightColor() {
        return textRightColor;
    }

    public void setTextRightColor(int textRightColor) {
        this.textRightColor = textRightColor;
    }

    public int getTextRightSize() {
        return textRightSize;
    }

    public void setTextRightSize(int textRightSize) {
        this.textRightSize = textRightSize;
    }

    public int getImgLeftMainId() {
        return imgLeftMainId;
    }

    public void setImgLeftMainId(int imgLeftMainId) {
        this.imgLeftMainId = imgLeftMainId;
    }

    public int getImgLeftMinorId() {
        return imgLeftMinorId;
    }

    public void setImgLeftMinorId(int imgLeftMinorId) {
        this.imgLeftMinorId = imgLeftMinorId;
    }

    public int getImgRightMainId() {
        return imgRightMainId;
    }

    public void setImgRightMainId(int imgRightMainId) {
        this.imgRightMainId = imgRightMainId;
    }

    public int getImgRightMinorId() {
        return imgRightMinorId;
    }

    public void setImgRightMinorId(int imgRightMinorId) {
        this.imgRightMinorId = imgRightMinorId;
    }
}
