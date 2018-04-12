package mejust.frame.net;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间:2018-03-16 16:53<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018-03-16 16:53<br/>
 * 描述:
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({ NetWorkCode.SUCCESS, NetWorkCode.TOKEN_ERROR, NetWorkCode.SIGN_ERROR })
public @interface NetWorkCode {
    /**
     * 网络请求成功
     */
    int SUCCESS = 200;
    /**
     * 网络请求token失效
     */
    int TOKEN_ERROR = 405;
    /**
     * 网络请求sign验签失败
     */
    int SIGN_ERROR = 2001;
}
