package com.gigigo.kretrofitmanager;

/**
 * @author Juan Godínez Vera - 10/18/2017.
 */
public class ServiceTimeoutSettings {

    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    private int connectTimeout;
    private int writeTimeout;
    private int readTimeout;

    public ServiceTimeoutSettings(int connectTimeout, int writeTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.writeTimeout = writeTimeout;
        this.readTimeout = readTimeout;
    }

    public static ServiceTimeoutSettings DEFAULT() {
        return new ServiceTimeoutSettings(CONNECT_TIMEOUT, WRITE_TIMEOUT, READ_TIMEOUT);
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
