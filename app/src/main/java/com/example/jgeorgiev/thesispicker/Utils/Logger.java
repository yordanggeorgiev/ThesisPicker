package com.example.jgeorgiev.thesispicker.Utils;

import android.util.Log;

import com.example.jgeorgiev.thesispicker.Interfaces.LogWrapper;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public class Logger implements LogWrapper {

    private static Logger logger;

    private Logger() {
        // nothing to do
    }

    public static LogWrapper getInstance() {
        if (logger != null) {
            return logger;
        }

        logger = new Logger();

        return logger;
    }

    @Override
    public void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    @Override
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    @Override
    public void w(String tag, String msg, Throwable t) {
        Log.e(tag, msg, t);
    }

    @Override
    public void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        Log.e(tag, msg, t);
    }
}

