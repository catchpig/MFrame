package mejust.frame.net;

import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 创建时间:2017-12-20 15:57<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-20 15:57<br/>
 * 描述: 请求参数拦截器，处理请求参数的添加、修改
 */

public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
