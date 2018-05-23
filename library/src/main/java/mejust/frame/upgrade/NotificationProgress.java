package mejust.frame.upgrade;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 创建时间: 2018/03/12 9:40<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/12 9:40<br>
 * 描述:
 */
public class NotificationProgress implements ProgressHelper {

    private static final int NOTIFICATION_ID = 0xfff;
    private static final String CHANNEL = Utils.getApp().getPackageName() + ".upgrade";

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManagerCompat notificationManager;

    public NotificationProgress(Context context, ProgressMessageBuilder progressMessageBuilder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "应用更新";
            String description = "Prompt to say that you received the offer";
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL).setContentTitle(
                progressMessageBuilder.getContentTitle())
                .setSmallIcon(progressMessageBuilder.getSmallIcon())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setAutoCancel(true)
                .setOngoing(true);
        notificationManager = NotificationManagerCompat.from(context);
    }

    @Override
    public void updateProgress(int progress) {
        notificationBuilder.setProgress(100, progress, false)
                .setContentText(Utils.getApp().getResources().getString(R.string.download_ing_frame));
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    @Override
    public void downloadSuccess() {
        notificationBuilder.setProgress(0, 0, false)
                .setContentText(Utils.getApp().getResources().getString(R.string.download_success_frame));
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        notificationManager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void downloadFail() {
        notificationBuilder.setProgress(0, 0, false)
                .setContentText(Utils.getApp().getResources().getString(R.string.download_fail_frame));
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
