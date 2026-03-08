// ============================================================================
// Prescription.java - Prescription Data Class
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This class represents a medical prescription with medication name, dosage,
// patient ID, and prescribing doctor. Includes validation logic to ensure
// dosage values are within safe ranges (positive and not exceeding 5000mg).
// ============================================================================
// BUG 11 is in this file: setDosage() throws a CHECKED exception (Exception)
// when it should throw an UNCHECKED InvalidDosageException.
// Students fix this AFTER creating InvalidDosageException in Tier 3.
// ============================================================================

package Lab10_Hospital_System;

public class Prescription {

    private final String medication;
    private double dosageMg;
    private final int patientId;
    private final String prescribedBy;

    public Prescription(String medication, double dosageMg, int patientId,
                        String prescribedBy) {
        this.medication = medication;
        this.dosageMg = dosageMg;
        this.patientId = patientId;
        this.prescribedBy = prescribedBy;
    }

    // Getters

    public String getMedication()  { return medication; }
    public double getDosageMg()    { return dosageMg; }
    public int getPatientId()      { return patientId; }
    public String getPrescribedBy() { return prescribedBy; }

    // Setters

    /**
     * Updates the dosage for this prescription.
     *
     * BUG 11: This method throws 'Exception' (checked) for an invalid
     * dosage. But a negative dosage is a PROGRAMMING ERROR - the calling
     * code should never pass a bad value. This should be an UNCHECKED
     * exception (InvalidDosageException), which students will create
     * in Tier 3 Task 13.
     */
    public void setDosage(double newDosage) {
        if (newDosage <= 0) {
            // BUG 11: Should throw InvalidDosageException (unchecked)
            throw new InvalidDosageException(
                newDosage,
                medication,
                "Invalid dosage: " + newDosage + "mg of " + medication +
                ". Dosage must be positive."
            );
        }
        if (newDosage > 5000) {
            // BUG 11: Same issue here
            throw new InvalidDosageException(
                newDosage,
                medication,
                "Dangerously high dosage: " + newDosage + "mg of " + medication +
                ". Maximum allowed is 5000mg."
            );
        }
        this.dosageMg = newDosage;
    }

    @Override
    public String toString() {
        return String.format("%s %.1fmg (Patient #%d, Dr. %s)",
            medication, dosageMg, patientId, prescribedBy);
    }
}
