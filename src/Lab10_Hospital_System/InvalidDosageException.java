package Lab10_Hospital_System;
// ============================================================================
// InvalidDosageException.java - Custom Unchecked Exception for Invalid Dosage
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This custom unchecked exception (extends RuntimeException) is thrown when an invalid
// dosage value is specified for a prescription. It is unchecked because providing
// negative or dangerously high dosage values is a programming error, not a recoverable
// user error. Stores dosage amount and medication name for detailed error reporting.
// ============================================================================

/**
 * InvalidDosageException - thrown when an invalid dosage is specified.
 * This is an UNCHECKED exception (extends RuntimeException) because
 * specifying a negative or dangerously high dosage is a programming error,
 * not a recoverable condition.
 */
public class InvalidDosageException extends RuntimeException {

    private final double dosage;
    private final String medication;

    public InvalidDosageException(double dosage, String medication, String message) {
        super(message);
        this.dosage = dosage;
        this.medication = medication;
    }

    public double getDosage() {
        return dosage;
    }

    public String getMedication() {
        return medication;
    }
}
