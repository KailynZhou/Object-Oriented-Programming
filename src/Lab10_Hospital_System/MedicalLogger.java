package Lab10_Hospital_System;
// ============================================================================
// MedicalLogger.java - Medical Event Logging Utility
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This utility class handles logging of medical events to a file with timestamps.
// Demonstrates three different exception handling approaches:
// - logEvent(): try-catch-finally with proper resource cleanup
// - logEventUnsafe(): demonstrates common mistakes with resource management
// - logEventModern(): uses try-with-resources for automatic cleanup
// ============================================================================
// BUGS: Bug 4, Bug 9, Task 15
// ============================================================================

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MedicalLogger {

    private final String logFilePath;
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MedicalLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    // ==================================================================
    // logEvent() - BUG 4
    // ==================================================================
    // BUG 4: Has try and finally but NO catch block for IOException.
    // FileWriter and printf can both throw IOException. Without catch,
    // the exception propagates uncaught and crashes the program.
    // FIX: Add catch (IOException e) between try and finally.
    // ==================================================================
    public void logEvent(String event) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(logFilePath, true));
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.printf("[%s] %s%n", timestamp, event);
            System.out.println("  [Log] Event recorded.");

        // BUG 4: No catch block here! IOException goes unhandled!

        } 
        catch (IOException e) {
            System.out.println("  [Log] Failed to record event: " + e.getMessage());
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // ==================================================================
    // logEventUnsafe() - BUG 9
    // ==================================================================
    // BUG 9: Multiple resource cleanup problems:
    //   A: writer declared INSIDE try block - finally cannot access it
    //   B: nested catch in finally silently ignores errors
    //   C: code won't compile because writer is out of scope in finally
    // FIX: Declare writer before try, handle cleanup properly.
    // ==================================================================
    public void logEventUnsafe(String event) {
        // BUG 9A: writer declared inside try - out of scope in finally
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                new FileWriter(logFilePath, true));
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.printf("[%s] %s%n", timestamp, event);
            System.out.println("  [Log] Event recorded (unsafe).");

        } catch (IOException e) {
            System.out.println("  [Log] Failed: " + e.getMessage());

        } finally {
            // BUG 9A: writer is out of scope here - compiler error!
            if (writer != null) {
                writer.close();
            }
        }
    }

    // ==================================================================
    // logEventModern() - TASK 15
    // ==================================================================
    // TASK 15: Implement using try-with-resources. Should:
    //   1. Open PrintWriter wrapping FileWriter in append mode
    //   2. Write timestamped log entry: [timestamp] event
    //   3. Print confirmation message
    //   4. Handle IOException with catch block
    //   5. Writer auto-closed by try-with-resources
    // ==================================================================
    public void logEventModern(String event) {
        // TODO (Task 15): Implement with try-with-resources
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.printf("[%s] %s%n", timestamp, event);
            System.out.println("  [Log] Event recorded (modern).");
        } catch (IOException e) {
            System.out.println("  [Log] Failed to record event: " + e.getMessage());
        }
    }

    // ==================================================================
    // Utility - delegates to logEvent (which has Bug 4)
    // ==================================================================
    public void logPatientEvent(int patientId, String action) {
        String event = String.format("Patient #%d | %s", patientId, action);
        logEvent(event);
    }
}
