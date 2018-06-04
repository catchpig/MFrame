package mejust.frame.common.upgrade;

import java.io.Serializable;

/**
 * 创建时间: 2018/03/09 17:45<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 17:45<br>
 * 描述:
 */
public class ProgressMessageBuilder implements Serializable {

    private static final long serialVersionUID = -7863198910142138929L;

    /**
     * 通知栏图标
     */
    private int smallIcon;
    /**
     * 通知栏标题
     */
    private String contentTitle;

    public ProgressMessageBuilder(int smallIcon, String contentTitle) {
        this.smallIcon = smallIcon;
        this.contentTitle = contentTitle;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public String getContentTitle() {
        return contentTitle;
    }
}
