package mejust.frame.upgrade;

/**
 * 创建时间: 2018/03/09 17:45<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 17:45<br>
 * 描述:
 */
public class ProgressMessageBuilder {

    private int smallIcon;
    private String contentTitle;

    public ProgressMessageBuilder(int smallIcon, String contentTitle) {
        this.smallIcon = smallIcon;
        this.contentTitle = contentTitle;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }
}
