package mejust.frame.upgrade;

/**
 * 创建时间: 2018/03/09 17:37<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/09 17:37<br>
 * 描述:
 */
public interface ProgressHelper {

    void create();

    void updateProgress(int progress);

    void downloadSuccess();

    void downloadFail();
}
