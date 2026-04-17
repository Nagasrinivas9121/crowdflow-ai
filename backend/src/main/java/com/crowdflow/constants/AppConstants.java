package com.crowdflow.constants;

/**
 * Constants used throughout the application.
 */
public final class AppConstants {
    
    private AppConstants() {
        // Prevent instantiation
    }

    public static final int MIN_DENSITY = 0;
    public static final int MAX_DENSITY = 100;
    
    public static final int FAST_THRESHOLD = 10;
    public static final int MEDIUM_THRESHOLD = 20;

    public static final String RISK_LOW = "LOW";
    public static final String RISK_MEDIUM = "MEDIUM";
    public static final String RISK_HIGH = "HIGH";

    public static final String REC_FAST = "FAST";
    public static final String REC_MODERATE = "MODERATE";
    public static final String REC_SLOW = "SLOW";
}
