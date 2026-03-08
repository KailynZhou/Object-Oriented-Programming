package Lab10_Hospital_System;
// ============================================================================
// HospitalSystem.java - Core Hospital Management Business Logic
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This class contains the core business logic for the hospital management system.
// Manages patient records, appointments, prescriptions, and medical logging.
// Demonstrates proper exception handling, including throwing custom checked and
// unchecked exceptions, proper catch block ordering, and resource management.
// Pre-loads test data (Alice, Bob, Charlie) for demonstration purposes.
// ============================================================================
// BUGS IN THIS FILE:
//   Bug 2:  findPatient() missing throws clause
//   Bug 3:  prescribeMedication() catches wrong exception type
//   Bug 6:  removePatient() catch blocks in wrong order
//   Bug 7:  registerPatient() silently swallows exception
//   Bug 8:  updatePatientAge() uses exceptions for control flow
//   Bug 10: prescribeMedication() catches broad Exception hiding NPE
//   Task 16: scheduleAppointment() missing conflict detection
// ============================================================================

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class HospitalSystem {

    private final Map<Integer, Patient> patients;
    private final Map<Integer, List<String>> appointments;
    private final List<Prescription> prescriptions;
    private final MedicalLogger logger;
    private int nextPatientId;

    public HospitalSystem() {
        patients = new HashMap<>();
        appointments = new HashMap<>();
        prescriptions = new ArrayList<>();
        logger = new MedicalLogger("medical_log.txt");
        nextPatientId = 1001;

        // Pre-load some test patients
        Patient alice = new Patient(1001, "Alice Johnson", 34);
        alice.addAllergy("Penicillin");
        alice.addAllergy("Ibuprofen");
        patients.put(1001, alice);
        appointments.put(1001, new ArrayList<>());
        appointments.get(1001).add("2025-03-15 09:00 - Annual checkup");

        Patient bob = new Patient(1002, "Bob Smith", 58);
        bob.addAllergy("Aspirin");
        patients.put(1002, bob);
        appointments.put(1002, new ArrayList<>());

        Patient charlie = new Patient(1003, "Charlie Brown", 8);
        patients.put(1003, charlie);
        appointments.put(1003, new ArrayList<>());

        nextPatientId = 1004;
    }

    // ==================================================================
    // findPatient() - BUG 2
    // ==================================================================
    // BUG 2: This method throws PatientNotFoundException (which after
    // fixing Bug 1 will be a CHECKED exception), but the method
    // signature does NOT declare 'throws PatientNotFoundException'.
    // This will cause a compiler error once Bug 1 is fixed.
    // FIX: Add 'throws PatientNotFoundException' to the signature.
    // ==================================================================
    public Patient findPatient(int patientId) throws PatientNotFoundException {
        // BUG 2: Missing 'throws PatientNotFoundException' ^^^^
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new PatientNotFoundException(patientId);
        }
        return patient;
    }

    // ==================================================================
    // registerPatient() - BUG 7
    // ==================================================================
    // BUG 7: The catch block is EMPTY - it silently swallows the
    // IllegalArgumentException. If a user enters invalid data
    // (empty name, bad age), they get zero feedback. The patient
    // just doesn't get created and nobody knows why.
    // FIX: Add error reporting in the catch block.
    // ==================================================================
    public Patient registerPatient(String name, int age) {
        try {
            int id = nextPatientId++;
            Patient patient = new Patient(id, name, age);

            // Validate name (Patient.setName throws IllegalArgumentException)
            patient.setName(name);

            patients.put(id, patient);
            appointments.put(id, new ArrayList<>());
            logger.logPatientEvent(id, "REGISTERED: " + name);
            System.out.println("  Registered: " + patient);
            return patient;

        } catch (IllegalArgumentException e) {
            // BUG 7: Empty catch block! User gets NO feedback!
            // The exception is silently swallowed.
            // FIX: Print an error message so the user knows what went wrong
            System.out.println("  Registration error: " + e.getMessage());
            return null;
        }
        
    }

    // ==================================================================
    // updatePatientAge() - BUG 8
    // ==================================================================
    // BUG 8: This method uses exceptions for NORMAL CONTROL FLOW.
    // It throws an exception to check if the age is negative, then
    // immediately catches it in the same method. This is pointless
    // overhead. It should use a simple if-else instead.
    // FIX: Replace the throw-and-catch with an if-else check.
    // ==================================================================
    public void updatePatientAge(int patientId, int newAge)
            throws PatientNotFoundException {
        Patient patient = findPatient(patientId);

        // BUG 8: Using exceptions for control flow - just use if-else!
        if (newAge < 0) {
            System.out.println("  Error: Age cannot be negative: " + newAge);
        } else if (newAge > 150) {
            System.out.println("  Error: Age unrealistic: " + newAge);
        } else {
            patient.setAge(newAge);
            System.out.println("  Updated age for " + patient.getName() + " to " + newAge);
            logger.logPatientEvent(patientId, "AGE UPDATED to " + newAge);  
        }
    }

    // ==================================================================
    // removePatient() - BUG 6
    // ==================================================================
    // BUG 6: The catch blocks are in the WRONG ORDER. The broad
    // 'catch (Exception e)' appears BEFORE the specific
    // 'catch (PatientNotFoundException e)'. Since Exception is the
    // parent of PatientNotFoundException, the first catch grabs
    // everything, making the second catch block UNREACHABLE dead code.
    // The compiler may produce a warning or error about this.
    // FIX: Swap the order - specific exceptions must come FIRST.
    // ==================================================================
    public void removePatient(int patientId) throws PatientNotFoundException {
        try {
            Patient patient = findPatient(patientId);
            patients.remove(patientId);
            appointments.remove(patientId);
            System.out.println("  Removed: " + patient.getName());
            logger.logPatientEvent(patientId, "REMOVED from system");
        // BUG 6: Exception (broad) comes BEFORE PatientNotFoundException (specific)!
        } 
         catch (PatientNotFoundException e) {
            // BUG 6: This block is UNREACHABLE because Exception above catches everything!
            System.out.println("  Patient #" + e.getPatientId() + " not found.");
            throw e;
        }catch (Exception e) {
            System.out.println("  An error occurred: " + e.getMessage());
        }
    }

    // ==================================================================
    // prescribeMedication() - BUGS 3 and 10
    // ==================================================================
    // BUG 3: The first catch block catches NumberFormatException, but
    // nothing in this method throws NumberFormatException. The code
    // that CAN fail throws PatientNotFoundException and potentially
    // triggers allergy-related IllegalArgumentExceptions. The wrong
    // exception type is being caught.
    // FIX: Catch the correct exception types.
    //
    // BUG 10: The second catch block catches the broad 'Exception' type.
    // This accidentally hides a NullPointerException that could occur
    // if a patient's allergy list were null (defensive check in
    // isAllergicTo). Catching Exception masks real bugs.
    // FIX: Catch only the specific types you expect and can handle.
    //      Let unexpected exceptions (like NPE) propagate so you
    //      find and fix the real bug.
    // ==================================================================
    public void prescribeMedication(int patientId, String medication,
                                    double dosageMg, String doctor)
            throws PatientNotFoundException {

        try {
            Patient patient = findPatient(patientId);

            // Check for allergies
            if (patient.isAllergicTo(medication)) {
                throw new IllegalArgumentException(
                    "ALLERGY ALERT: " + patient.getName() +
                    " is allergic to " + medication + "!");
            }

            // Create prescription
            Prescription rx = new Prescription(medication, dosageMg,
                patientId, doctor);
            prescriptions.add(rx);
            patient.addMedicalRecord("Prescribed: " + rx);
            logger.logPatientEvent(patientId,
                "PRESCRIBED: " + medication + " " + dosageMg + "mg by Dr. " + doctor);
            System.out.println("  Prescribed: " + rx);

        // BUG 3: Catching NumberFormatException - nothing here throws that!
        // Should catch IllegalArgumentException for allergy alerts
        } catch (IllegalArgumentException e) {
            System.out.println("  Prescription error: " + e.getMessage());

        // BUG 10: Catching broad 'Exception' hides real bugs like NPE
        // Should only catch specific expected exceptions
        } 
    }

    // ==================================================================
    // scheduleAppointment() - TASK 16
    // ==================================================================
    // TASK 16: Implement conflict detection. The method should:
    //   1. Find the patient (may throw PatientNotFoundException)
    //   2. Check if the patient already has an appointment at the
    //      same dateTime (iterate through their appointment list)
    //   3. If a conflict exists, throw AppointmentConflictException
    //      with the patient name, requested dateTime, and the
    //      conflicting appointment description
    //   4. If no conflict, add the appointment and log it
    // ==================================================================
    public void scheduleAppointment(int patientId, String dateTime,
                                    String description)
            throws PatientNotFoundException, AppointmentConflictException {

        Patient patient = findPatient(patientId);

        // Get or create the patient's appointment list
        List<String> patientAppointments = appointments.get(patientId);
        if (patientAppointments == null) {
            patientAppointments = new ArrayList<>();
            appointments.put(patientId, patientAppointments);
        }

        // TODO (Task 16): Check for scheduling conflicts
        // Loop through patientAppointments. Each entry is a String like:
        //   "2025-03-15 09:00 - Annual checkup"
        // If any existing appointment STARTS WITH the given dateTime,
        // that is a conflict. Throw AppointmentConflictException with:
        //   - patient.getName()
        //   - dateTime
        //   - the conflicting appointment string
        //
        // YOUR CODE HERE:
        for (String existingAppt : patientAppointments) {
            if (existingAppt.startsWith(dateTime)) {
                throw new AppointmentConflictException(
                    patient.getName(),
                    dateTime,
                    existingAppt
                );
            }
        }


        // Add the appointment (this part is correct)
        String appointmentEntry = dateTime + " - " + description;
        patientAppointments.add(appointmentEntry);
        logger.logPatientEvent(patientId,
            "APPOINTMENT SCHEDULED: " + appointmentEntry);
        System.out.println("  Appointment scheduled: " + appointmentEntry +
            " for " + patient.getName());
    }

    // ==================================================================
    // viewPatientRecord() - This method is correct
    // ==================================================================
    public void viewPatientRecord(int patientId)
            throws PatientNotFoundException {
        Patient patient = findPatient(patientId);
        System.out.println("\n  === Patient Record ===");
        System.out.println("  " + patient);
        System.out.println("  Medical History:");
        List<String> history = patient.getMedicalHistory();
        if (history.isEmpty()) {
            System.out.println("    (no records)");
        } else {
            for (String record : history) {
                System.out.println("    - " + record);
            }
        }
        System.out.println("  Appointments:");
        List<String> appts = appointments.getOrDefault(
            patientId, new ArrayList<>());
        if (appts.isEmpty()) {
            System.out.println("    (no appointments)");
        } else {
            for (String appt : appts) {
                System.out.println("    - " + appt);
            }
        }

        // Show prescriptions for this patient
        System.out.println("  Prescriptions:");
        boolean hasPrescriptions = false;
        for (Prescription rx : prescriptions) {
            if (rx.getPatientId() == patientId) {
                System.out.println("    - " + rx);
                hasPrescriptions = true;
            }
        }
        if (!hasPrescriptions) {
            System.out.println("    (no prescriptions)");
        }
    }

    // ==================================================================
    // listAllPatients() - This method is correct
    // ==================================================================
    public void listAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("  No patients in the system.");
            return;
        }
        System.out.println("\n  === All Patients ===");
        for (Patient p : patients.values()) {
            System.out.println("  " + p);
        }
    }
}
