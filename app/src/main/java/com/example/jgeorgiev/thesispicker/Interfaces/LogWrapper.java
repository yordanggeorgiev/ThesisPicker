package com.example.jgeorgiev.thesispicker.Interfaces;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public interface LogWrapper {

    void i(String tag, String msg);

    void d(String tag, String msg);

    void w(String tag, String msg);

    void w(String tag, String msg, Throwable t);

    void e(String tag, String msg);

    void e(String tag, String msg, Throwable t);
}

