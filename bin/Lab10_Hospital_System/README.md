# Hospital Management System

Name: Jieping Zhou 24250243
Name: Dong Li 25255673

## 📁 Project Structure

### Core Files

1. **HospitalApp.java**
   - Main application entry point
   - Interactive command-line menu interface
   - Handles user input and delegates to HospitalSystem
   - Demonstrates comprehensive exception handling

2. **HospitalSystem.java**
   - Core business logic layer
   - Manages patients, appointments, and prescriptions
   - Implements all CRUD operations
   - Pre-loaded with test data (Alice, Bob, Charlie)

### Data Model Classes

3. **Patient.java**
   - Patient data model with ID, name, age, allergies, and medical history
   - Input validation for name and age
   - Allergy checking functionality

4. **Prescription.java**
   - Prescription data with medication, dosage, patient ID, and doctor
   - Dosage validation (must be positive and ≤ 5000mg)
   - Throws InvalidDosageException for invalid values

### Custom Exception Classes

5. **PatientNotFoundException.java** (Checked Exception)
   - Thrown when a patient ID is not found
   - Extends `Exception` - caller must handle
   - Stores patient ID for error reporting

6. **AppointmentConflictException.java** (Checked Exception)
   - Thrown when scheduling conflicts with existing appointment
   - Stores patient name, requested time, and conflicting appointment
   - Extends `Exception` - must be handled explicitly

7. **InvalidDosageException.java** (Unchecked Exception)
   - Thrown for invalid prescription dosages
   - Extends `RuntimeException` - programming error
   - Stores dosage amount and medication name

### Utility Classes

8. **MedicalLogger.java**
   - Logs medical events to file with timestamps
   - Demonstrates three exception handling approaches:
     - `logEvent()` - traditional try-catch-finally
     - `logEventUnsafe()` - demonstrates common mistakes
     - `logEventModern()` - try-with-resources (recommended)

#### Schedule an Appointment

```
Choose option: 3
Patient ID: 1001
Date & Time (e.g., 2025-03-20 14:00): 2025-03-20 14:00
Description: Follow-up checkup
```

**Testing Conflict Detection:**
Try scheduling at "2025-03-15 09:00" for Alice (Patient #1001) - it will detect the conflict!

#### Prescribe Medication

```
Choose option: 4
Patient ID: 1002
Medication name: Aspirin
Dosage (mg): 100
Prescribing doctor: Dr. Smith
```

**Testing Allergy Detection:**
Try prescribing "Aspirin" to Bob (Patient #1002) - it will show an allergy alert!

---

## 🐛 Bug Fixes and Tasks Completed

### Bug Fixes

| Bug # | File | Issue ｜ Fix
| BUG 1 | PatientNotFoundException | Extended RuntimeException (unchecked) | Changed to extend Exception (checked) |
| BUG 2 | HospitalSystem | findPatient() missing throws clause | Added `throws PatientNotFoundException` |
| BUG 3 | HospitalSystem | prescribeMedication() catches wrong exception | Catches IllegalArgumentException correctly |
| BUG 4 | MedicalLogger | logEvent() missing catch block | Added `catch (IOException e)` |
| BUG 6 | HospitalSystem | removePatient() catch blocks wrong order | Reordered: specific exceptions first |
| BUG 7 | HospitalSystem | registerPatient() empty catch block | Added error message output |
| BUG 8 | HospitalSystem | updatePatientAge() exceptions for control flow | Replaced with if-else logic |
| BUG 9 | MedicalLogger | logEventUnsafe() writer out of scope | Declared writer before try block |
| BUG 10 | HospitalSystem | prescribeMedication() catches broad Exception | Removed broad catch, specific only |
| BUG 11 | Prescription | setDosage() throws checked Exception | Changed to unchecked InvalidDosageException |
| BUG 12 | HospitalApp | Exception caught before specific exceptions | Reordered catch blocks correctly |

### Tasks Completed

| Task # | Description | Implementation
| TASK 13 | Create InvalidDosageException | Implemented as unchecked exception with dosage and medication fields |
| TASK 14 | Create AppointmentConflictException| Implemented as checked exception with patient, time, and conflict details |
| TASK 15 | Implement logEventModern() | Used try-with-resources for automatic resource management |
| TASK 16 | Implement appointment conflict detection | Added loop to check for existing appointments at same time |

## 📊 Exception Hierarchy

```
Throwable
├── Exception (Checked - must handle)
│   ├── IOException
│   ├── PatientNotFoundException (Custom)
│   └── AppointmentConflictException (Custom)
│
└── RuntimeException (Unchecked - optional handling)
    ├── IllegalArgumentException
    ├── NullPointerException
    ├── NumberFormatException
    └── InvalidDosageException (Custom)
```

---

## 🔍 Testing Scenarios

### Test Exception Handling

1. **PatientNotFoundException**
   - Try operation with non-existent patient ID (e.g., 9999)

2. **AppointmentConflictException**
   - Schedule appointment for Alice at "2025-03-15 09:00"

3. **InvalidDosageException**
   - Modify code to test negative dosage or > 5000mg

4. **IllegalArgumentException (Allergy)**
   - Prescribe Aspirin to Bob Smith

5. **NumberFormatException**
   - Enter non-numeric input when prompted for ID or age

---
