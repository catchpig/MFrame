package mejust.frame.utils.rx;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * 创建时间:2017-12-20 17:34<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-20 17:34<br/>
 * 描述: 简单的订阅操作
 */

public class RxBus {

    private static class RxBusHolder {
        private static final RxBus RX_BUS = new RxBus();
    }

    public static RxBus getInstance() {
        return RxBusHolder.RX_BUS;
    }

    private final FlowableProcessor<Object> busFlowable;

    private RxBus() {
        busFlowable = PublishProcessor.create().toSerialized();
    }

    /**
     * 发送事件
     *
     * @param event 事件类型
     */
    public void post(Object event) {
        busFlowable.onNext(event);
    }

    /**
     * 根据传递的class事件类型返回特定的观察者
     *
     * @param tClass 事件类
     * @param <T> 事件类型
     * @return 观察者
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return busFlowable.ofType(tClass).onBackpressureBuffer();
    }
}
