package Lab10_Hospital_System;
// ============================================================================
// HospitalApp.java - Interactive Hospital Management Application
// ============================================================================
// Name: Jieping Zhou 24250243
// Name: Dong Li 25255673
// 
// Description:
// Main application class providing an interactive command-line interface for
// the Hospital Management System. Handles user input, menu navigation, and
// delegates operations to HospitalSystem. Demonstrates comprehensive exception
// handling including custom checked exceptions (PatientNotFoundException,
// AppointmentConflictException) and standard exceptions (NumberFormatException).
// ============================================================================
// BUGS IN THIS FILE:
//   Bug 5:  scheduleAppointment() call doesn't handle checked exception
//   Bug 12: Main loop catches Exception FIRST, making specific catches dead code
// ============================================================================

import java.util.Scanner;

public class HospitalApp {

    private final HospitalSystem system;
    private final Scanner scanner;

    public HospitalApp() {
        system = new HospitalSystem();
        scanner = new Scanner(System.in);
    }

    // ==================================================================
    // run() - THE MAIN LOOP - BUGS 5 and 12
    // ==================================================================
    // BUG 12: The catch blocks are in the WRONG ORDER. The broad
    // 'catch (Exception e)' appears FIRST, before all the specific
    // catches. This means the specific catches are ALL dead code -
    // they will never execute because Exception catches everything.
    // FIX: Move the Exception catch to the END, or better yet,
    // remove it and only catch specific types. Specific first,
    // broad last.
    // ==================================================================
    public void run() {
        boolean running = true;

        System.out.println("====================================");
        System.out.println("  HOSPITAL MANAGEMENT SYSTEM v1.0");
        System.out.println("====================================");
        System.out.println("  Pre-loaded patients:");
        system.listAllPatients();

        while (running) {
            try {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Register New Patient");
                System.out.println("2. View Patient Record");
                System.out.println("3. Schedule Appointment");
                System.out.println("4. Prescribe Medication");
                System.out.println("5. Update Patient Age");
                System.out.println("6. Remove Patient");
                System.out.println("7. List All Patients");
                System.out.println("8. Exit");
                System.out.print("Choose option: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        handleRegister();
                        break;
                    case 2:
                        handleViewRecord();
                        break;
                    case 3:
                        handleSchedule();
                        break;
                    case 4:
                        handlePrescribe();
                        break;
                    case 5:
                        handleUpdateAge();
                        break;
                    case 6:
                        handleRemove();
                        break;
                    case 7:
                        system.listAllPatients();
                        break;
                    case 8:
                        running = false;
                        System.out.println("System shutting down. Goodbye!");
                        break;
                    default:
                        System.out.println("  Invalid option. Choose 1-8.");
                }

            // BUG 12: Exception is caught FIRST - everything below is dead code!
            } catch (PatientNotFoundException e) {
                // BUG 12: DEAD CODE - Exception above catches this first!
                System.out.println("  Patient not found: " + e.getMessage());
                System.out.println("  Use option 7 to see all patients.");

            } catch (AppointmentConflictException e) {
                // BUG 12: DEAD CODE - Exception above catches this first!
                System.out.println("  Scheduling conflict: " + e.getMessage());

            } catch (NumberFormatException e) {
                // BUG 12: DEAD CODE - Exception above catches this first!
                System.out.println("  Invalid input. Please enter a number.");

            } catch (Exception e) {
                System.out.println("  ERROR: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // ==================================================================
    // handleRegister() - This method is correct
    // ==================================================================
    private void handleRegister() {
        System.out.print("  Patient name: ");
        String name = scanner.nextLine().trim();
        System.out.print("  Patient age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());

        Patient patient = system.registerPatient(name, age);

        if (patient != null) {
            System.out.print("  Add allergies? (comma-separated, or 'none'): ");
            String allergiesInput = scanner.nextLine().trim();
            if (!allergiesInput.equalsIgnoreCase("none") &&
                !allergiesInput.isEmpty()) {
                String[] allergies = allergiesInput.split(",");
                for (String allergy : allergies) {
                    patient.addAllergy(allergy.trim());
                }
                System.out.println("  Allergies recorded: " +
                    patient.getAllergies());
            }
        }
    }

    // ==================================================================
    // handleViewRecord() - This method is correct
    // ==================================================================
    private void handleViewRecord() throws PatientNotFoundException {
        System.out.print("  Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.viewPatientRecord(id);
    }

    // ==================================================================
    // handleSchedule() - BUG 5
    // ==================================================================
    // BUG 5: scheduleAppointment() throws AppointmentConflictException
    // (checked), but this method does not handle it. It declares
    // 'throws PatientNotFoundException' but NOT AppointmentConflictException.
    // FIX: Either add AppointmentConflictException to the throws clause,
    // or wrap the call in a try-catch that handles it here with a
    // helpful message.
    // ==================================================================
    private void handleSchedule() throws PatientNotFoundException, AppointmentConflictException {
        // BUG 5: Missing handling for AppointmentConflictException
        System.out.print("  Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("  Date/Time (e.g., 2025-03-20 14:00): ");
        String dateTime = scanner.nextLine().trim();
        System.out.print("  Description: ");
        String desc = scanner.nextLine().trim();

        // BUG 5: This call can throw AppointmentConflictException
        // but nothing here handles or declares it!
        system.scheduleAppointment(id, dateTime, desc);
    }

    // ==================================================================
    // handlePrescribe() - This method is correct
    // ==================================================================
    private void handlePrescribe() throws PatientNotFoundException {
        System.out.print("  Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("  Medication name: ");
        String med = scanner.nextLine().trim();
        System.out.print("  Dosage (mg): ");
        double dosage = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("  Prescribing doctor: ");
        String doctor = scanner.nextLine().trim();

        system.prescribeMedication(id, med, dosage, doctor);
    }

    // ==================================================================
    // handleUpdateAge() - This method is correct
    // ==================================================================
    private void handleUpdateAge() throws PatientNotFoundException {
        System.out.print("  Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("  New age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        system.updatePatientAge(id, age);
    }

    // ==================================================================
    // handleRemove() - This method is correct
    // ==================================================================
    private void handleRemove() throws PatientNotFoundException {
        System.out.print("  Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.removePatient(id);
    }

    // ==================================================================
    // main() - Entry point
    // ==================================================================
    public static void main(String[] args) {
        HospitalApp app = new HospitalApp();
        app.run();
    }
}
