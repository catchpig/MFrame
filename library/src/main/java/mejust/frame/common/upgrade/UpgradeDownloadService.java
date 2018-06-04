package mejust.frame.common.upgrade;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import mejust.frame.common.log.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class UpgradeDownloadService extends IntentService {

    private static final String DOWNLOAD_ACTION = "download_action";
    private static final String DOWNLOAD_PARAM_URL = "download_param_url";
    private static final String DOWNLOAD_PARAM_PATH = "download_param_path";
    private static final String DOWNLOAD_PARAM_RESULT = "download_param_result";

    public static final int DOWNLOAD_RESULT_SUCCESS = 0x111;
    public static final int DOWNLOAD_RESULT_FAIL = 0x112;
    public static final int DOWNLOAD_RESULT_LOADING = 0x113;
    public static final String DOWNLOAD_RESULT_PROGRESS = "download_progress";
    public static final String DOWNLOAD_RESULT_PATH = "download_path";

    public static void start(Context context, String url, String path,
            ResultReceiver resultReceiver) {
        Intent intent = new Intent(context, UpgradeDownloadService.class);
        intent.setAction(DOWNLOAD_ACTION);
        intent.putExtra(DOWNLOAD_PARAM_URL, url);
        intent.putExtra(DOWNLOAD_PARAM_PATH, path);
        intent.putExtra(DOWNLOAD_PARAM_RESULT, resultReceiver);
        context.startService(intent);
    }

    private String url;
    private String path;
    private ResultReceiver resultReceiver;

    public UpgradeDownloadService() {
        super("UpgradeDownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        if (DOWNLOAD_ACTION.equals(intent.getAction())) {
            url = intent.getStringExtra(DOWNLOAD_PARAM_URL);
            path = intent.getStringExtra(DOWNLOAD_PARAM_PATH);
            resultReceiver = intent.getParcelableExtra(DOWNLOAD_PARAM_RESULT);
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path) || resultReceiver == null) {
                return;
            }
            downloadFile();
        }
    }

    private void downloadFile() {
        Request request = new Request.Builder().url(url).get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                resultReceiver.send(DOWNLOAD_RESULT_FAIL, null);
                Logger.e("下载失败", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (!response.isSuccessful()) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return;
                }
                long contentLength = responseBody.contentLength();
                try (BufferedSource bufferedSource = responseBody.source();
                     BufferedSink bufferedSink = Okio.buffer(Okio.sink(new File(path)))) {
                    byte[] bytes = new byte[2048 * 10];
                    long progress = 0;
                    int len;
                    while ((len = bufferedSource.read(bytes)) != -1) {
                        bufferedSink.write(bytes, 0, len);
                        progress += len;
                        if (progress - contentLength >= 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString(DOWNLOAD_RESULT_PATH, path);
                            resultReceiver.send(DOWNLOAD_RESULT_SUCCESS, bundle);
                        } else {
                            int pro = (int) ((double) progress / (double) contentLength * 100);
                            Bundle bundle = new Bundle();
                            bundle.putInt(DOWNLOAD_RESULT_PROGRESS, pro);
                            resultReceiver.send(DOWNLOAD_RESULT_LOADING, bundle);
                        }
                    }
                    bufferedSink.flush();
                    bufferedSink.close();
                    bufferedSource.close();
                } catch (IOException e) {
                    resultReceiver.send(DOWNLOAD_RESULT_FAIL, null);
                    e.printStackTrace();
                }
            }
        });
    }
}
