package Lab10_Hospital_System;
// ============================================================================
// Patient.java — Patient Data Model Class
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This class represents a patient in the hospital management system.
// It stores patient information including ID, name, age, allergies, and medical history.
// Provides getter/setter methods with validation, and methods to manage allergies
// and medical records.
// ============================================================================
// This file is MOSTLY correct. Students should read it to understand the
// data model but focus their debugging efforts on other files.
// ============================================================================

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private final int patientId;
    private String name;
    private int age;
    private final List<String> allergies;
    private final List<String> medicalHistory;

    /**
     * Creates a new Patient.
     *
     * @param patientId   unique patient identifier
     * @param name        patient's full name
     * @param age         patient's age (must be 0-150)
     */
    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.allergies = new ArrayList<>();
        this.medicalHistory = new ArrayList<>();
    }

    // ── Getters ──────────────────────────────────────────────

    public int getPatientId() { return patientId; }
    public String getName()   { return name; }
    public int getAge()       { return age; }

    /**
     * Returns the patient's allergy list.
     * NOTE: This can return an EMPTY list but should never return null.
     */
    public List<String> getAllergies() { return allergies; }

    public List<String> getMedicalHistory() { return medicalHistory; }

    // ── Setters / Mutators ───────────────────────────────────

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException(
                "Invalid age: " + age + ". Must be between 0 and 150.");
        }
        this.age = age;
    }

    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.trim().isEmpty()) {
            allergies.add(allergy.trim());
        }
    }

    public void addMedicalRecord(String record) {
        if (record != null && !record.trim().isEmpty()) {
            medicalHistory.add(record.trim());
        }
    }

    /**
     * Checks if the patient is allergic to a specific medication.
     *
     * @param medication the medication to check
     * @return true if the patient has a listed allergy to this medication
     */
    public boolean isAllergicTo(String medication) {
        for (String allergy : allergies) {
            if (allergy.equalsIgnoreCase(medication)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Patient #%d: %s (Age: %d, Allergies: %s)",
            patientId, name, age,
            allergies.isEmpty() ? "None" : String.join(", ", allergies));
    }
}
