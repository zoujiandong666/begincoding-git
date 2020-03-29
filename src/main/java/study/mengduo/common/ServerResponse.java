package study.mengduo.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @aothor mengDuo
 * @date 2020/3/13 20:52
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    private int status;
    private String msg;
    private T data;

    private  ServerResponse(int status){
        this.status=status;
    }

    private  ServerResponse(int status, String msg){
        this.status=status;
        this.msg=msg;
    }

    private  ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private  ServerResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public int getstatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == 0;
    }

    public static <T> ServerResponse<T> creatBySucess(){
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> creatByMessageSuccess(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> creatBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> creatBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> creatByError(){
        return new ServerResponse(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> creatByErrorMessage(String msg){
        return new ServerResponse(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> creatByError(int errorCode, String errorMessage){
        return new ServerResponse(errorCode,errorMessage);
    }
}
