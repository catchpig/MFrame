package mejust.frame.widget.title;

import android.view.View;
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
     * 左边文字点击事件
     */
    private View.OnClickListener leftTextListener;

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
     * 右边文字点击事件
     */
    private View.OnClickListener rightTextListener;

    /**
     * 左边主图标，左起第一个
     */
    private int imgLeftMainId;

    /**
     * 左边主图标点击事件
     */
    private View.OnClickListener leftMainListener;

    /**
     * 左边次要图标，左起第二个
     */
    private int imgLeftMinorId;

    /**
     * 左边次要图标点击事件
     */
    private View.OnClickListener leftMinorListener;

    /**
     * 右边主图标，右起第一个
     */
    private int imgRightMainId;

    /**
     * 右边主图标点击事件
     */
    private View.OnClickListener RightMainListener;

    /**
     * 右边次要图标，右起第二个
     */
    private int imgRightMinorId;

    /**
     * 右边次要图标点击事件
     */
    private View.OnClickListener RightMinorListener;

    /**
     * 背景颜色
     */
    private int backgroundColor;

    /**
     * 点击背景
     */
    private int clickBackground;

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

    public View.OnClickListener getLeftMainListener() {
        return leftMainListener;
    }

    public void setLeftMainListener(View.OnClickListener leftMainListener) {
        this.leftMainListener = leftMainListener;
    }

    public View.OnClickListener getLeftMinorListener() {
        return leftMinorListener;
    }

    public void setLeftMinorListener(View.OnClickListener leftMinorListener) {
        this.leftMinorListener = leftMinorListener;
    }

    public View.OnClickListener getRightMainListener() {
        return RightMainListener;
    }

    public void setRightMainListener(View.OnClickListener rightMainListener) {
        RightMainListener = rightMainListener;
    }

    public View.OnClickListener getRightMinorListener() {
        return RightMinorListener;
    }

    public void setRightMinorListener(View.OnClickListener rightMinorListener) {
        RightMinorListener = rightMinorListener;
    }

    public View.OnClickListener getLeftTextListener() {
        return leftTextListener;
    }

    public void setLeftTextListener(View.OnClickListener leftTextListener) {
        this.leftTextListener = leftTextListener;
    }

    public View.OnClickListener getRightTextListener() {
        return rightTextListener;
    }

    public void setRightTextListener(View.OnClickListener rightTextListener) {
        this.rightTextListener = rightTextListener;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getClickBackground() {
        return clickBackground;
    }

    public void setClickBackground(int clickBackground) {
        this.clickBackground = clickBackground;
    }
}
