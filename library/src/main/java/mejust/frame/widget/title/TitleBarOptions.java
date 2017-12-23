package mejust.frame.widget.title;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * 创建时间:2017-12-21 11:28<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017-12-21 11:28<br/>
 * 描述: TitleBar的数据类
 */

public class TitleBarOptions implements Serializable {
    /**
     * 背景颜色
     */
    private int backgroundColor;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 返回按钮的图片
     */
    private int backImage;
    /**
     * 返回按钮后面的文字
     */
    private String backText;
    /**
     * 返回按钮后面文字的大小
     */
    private int bactTextSize;
    /**
     * 标题文字
     */
    private String titleText;
    /**
     * 标题文字的大小
     */
    private int titleTextSize;
    /**
     * 右边位子
     */
    private String rightText;
    /**
     * 右边文字的大小
     */
    private int rightTextSize;
    /**
     * 右边第一张图片
     */
    private int rightImage1;
    /**
     * 右边第二张图片
     */
    private int rightImage2;

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
    }

    public int getBackImage() {
        return backImage;
    }

    public void setBackImage(@DrawableRes int backImage) {
        this.backImage = backImage;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public int getBactTextSize() {
        return bactTextSize;
    }

    public void setBactTextSize(int bactTextSize) {
        this.bactTextSize = bactTextSize;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public int getRightImage1() {
        return rightImage1;
    }

    public void setRightImage1(@DrawableRes int rightImage1) {
        this.rightImage1 = rightImage1;
    }

    public int getRightImage2() {
        return rightImage2;
    }

    public void setRightImage2(@DrawableRes int rightImage2) {
        this.rightImage2 = rightImage2;
    }
}
