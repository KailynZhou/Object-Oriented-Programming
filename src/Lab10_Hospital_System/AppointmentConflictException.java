package Lab10_Hospital_System;
// ============================================================================
// AppointmentConflictException.java - Custom Checked Exception for Scheduling Conflicts
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// This custom checked exception is thrown when attempting to schedule an appointment
// that conflicts with an existing appointment for the same patient at the same time.
// It stores the patient name, requested date/time, and conflicting appointment details.
// ============================================================================
// TASK 14: This class is an empty shell. Students must complete it.
//
// Requirements:
// - It should be a CHECKED exception (extends Exception)
// - It should store: patientName, requestedDateTime, conflictDescription
// - It should have a descriptive message
// - It should have getter methods for all fields
// ============================================================================

public class AppointmentConflictException extends Exception {

    // TODO (Task 14): Add private fields for:
    //   - patientName (String)
    //   - requestedDateTime (String)
    //   - conflictDescription (String)
    private final String patientName;
    private final String requestedDateTime;
    private final String conflictDescription;


    // TODO (Task 14): Create a constructor that:
    //   - Takes all three fields as parameters
    //   - Calls super() with a descriptive message like:
    //     "Scheduling conflict for [name] at [dateTime]: [description]"
    //   - Stores all fields
    public AppointmentConflictException(String patientName, String requestedDateTime, String conflictDescription) {
        super("Scheduling conflict for " + patientName + " at " + requestedDateTime + ": " + conflictDescription);
        this.patientName = patientName;
        this.requestedDateTime = requestedDateTime;
        this.conflictDescription = conflictDescription;
    }

    // TODO (Task 14): Add getter methods for all three fields
    public String getPatientName() {
        return patientName;
    }

    public String getRequestedDateTime() {
        return requestedDateTime;
    }

    public String getConflictDescription() {
        return conflictDescription;
    }

    // TEMPORARY: bare-minimum constructor so other files can reference this class
    // Replace this entirely with your implementation
    
  
}
