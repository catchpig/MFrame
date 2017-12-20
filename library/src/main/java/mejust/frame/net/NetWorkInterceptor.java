package mejust.frame.net;

import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 创建时间:2017-12-20 15:59<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-20 15:59<br/>
 * 描述: 网络拦截器，用于NetworkInterceptor添加
 */

public class NetWorkInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
