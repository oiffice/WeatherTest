package york.test.weatherTest.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    private String message = "success";
    private int code = SUCCESS;
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();

        this.data = data;
    }

    public ResultBean(Throwable throwable) {
        super();
        this.message = throwable.getMessage();
        this.code = FAILED;
    }
}
