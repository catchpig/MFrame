package mejust.frame.net;

/**
 * 创建时间:2017/12/21 20:40<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 20:40<br/>
 * 描述:
 */

public class AjaxResult<T> {

    private String code;
    private String message;
    private T data;

    public int getCode() {
        try {
            return Integer.valueOf(code);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Optional<T> getData() {
        return Optional.ofNullable(data);
    }

    public void setData(T data) {
        this.data = data;
    }
}
