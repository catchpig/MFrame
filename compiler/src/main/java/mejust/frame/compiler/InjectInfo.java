package mejust.frame.compiler;

import java.util.HashMap;
import mejust.frame.compiler.bean.TitleBarMenuInfo;

/**
 * 创建时间: 2018/04/16
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/16
 * 描述: <empty/>
 */
public class InjectInfo {

    private HashMap<Integer, TitleBarMenuInfo> menuInfoMap = new HashMap<>();

    public void putMenuInfo(TitleBarMenuInfo menuInfo) {
        menuInfoMap.put(menuInfo.getLocation(), menuInfo);
    }

}
