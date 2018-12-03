package com.cmcc.iot.common;

public class MyLoginException extends Exception{
    public MyLoginException(){
        super();
    }
    public MyLoginException(String message){
        super(message);

    }

    public MyLoginException(String message, Throwable cause){

        super(message,cause);
    }
    public MyLoginException(Throwable cause) {

        super(cause);
    }

}
