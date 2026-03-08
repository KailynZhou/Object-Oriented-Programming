package Lab10_Hospital_System;
// ============================================================================
// PatientNotFoundException.java - Custom Checked Exception
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// Description:
// This custom checked exception is thrown when a patient ID is not found in the system.
// It extends Exception (checked) because a missing patient is a recoverable user error,
// not a programming bug. The caller must handle this exception explicitly.
// ============================================================================
// BUG 1: This exception extends RuntimeException (UNCHECKED), but it
// should extend Exception (CHECKED). A patient not being found is a
// recoverable user error (they can try a different ID), NOT a
// programming bug. Fix: change RuntimeException to Exception.
// ============================================================================

public class PatientNotFoundException extends Exception {
    // BUG 1: ^^^^^^^^^^^^^^^^^^^^^^ should be Exception

    private final int patientId;

    public PatientNotFoundException(int patientId) {
        super("Patient #" + patientId + " was not found in the system.");
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }
}
