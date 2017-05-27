package com.example.jgeorgiev.thesispicker.interfaces;

/**
 * Logger interface
 * Created by ygeorgiev on 21-May-17.
 */
public interface LogWrapper {

    void i(String tag, String msg);

    void d(String tag, String msg);

    void w(String tag, String msg);

    void w(String tag, String msg, Throwable t);

    void e(String tag, String msg);

    void e(String tag, String msg, Throwable t);
}

