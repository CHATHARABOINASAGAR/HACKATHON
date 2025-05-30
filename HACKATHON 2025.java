import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Medicine {
    String name;
    LocalDate expiryDate;
    double currentTemperature; 
    String qualityGrade;      
    public Medicine(String name, LocalDate expiryDate, double currentTemperature, String qualityGrade) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.currentTemperature = currentTemperature;
        this.qualityGrade = qualityGrade;
    }
}

class QualityMonitor {

    private static final double MAX_ALLOWED_TEMPERATURE = 25.0;
    private static final List<String> ALLOWED_GRADES = List.of("A", "B");

    public static boolean isMedicineSafeToUse(Medicine medicine) {
        LocalDate today = LocalDate.now();

        boolean notExpired = today.isBefore(medicine.expiryDate);
        boolean tempOk = medicine.currentTemperature <= MAX_ALLOWED_TEMPERATURE;
        boolean gradeOk = ALLOWED_GRADES.contains(medicine.qualityGrade.toUpperCase());

        return notExpired && tempOk && gradeOk;
    }

    public static void checkMedicines(List<Medicine> medicineList) {
        System.out.println("\n📦 Starting automatic quality check...\n");

        for (Medicine med : medicineList) {
            boolean isValid = isMedicineSafeToUse(med);

            System.out.println("🧪 Medicine Name: " + med.name);
            System.out.println("   - Expiry Date   : " + med.expiryDate);
            System.out.println("   - Temperature   : " + med.currentTemperature + "°C");
            System.out.println("   - Quality Grade : " + med.qualityGrade);

            if (isValid) {
                System.out.println("   ✅ Status: ACCEPTED (Safe to use)\n");
            } else {
                System.out.println("   ❌ Status: REJECTED (Unsafe or expired)\n");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Medicine> receivedMedicines = new ArrayList<>();

        System.out.print("Enter number of medicines to check: ");
        int numberOfMedicines = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numberOfMedicines; i++) {
            System.out.println("\nEnter details for Medicine #" + (i + 1));

            System.out.print("Medicine Name: ");
            String name = scanner.nextLine();

            System.out.print("Expiry Year (e.g., 2025): ");
            int year = scanner.nextInt();

            System.out.print("Expiry Month (1-12): ");
            int month = scanner.nextInt();

            System.out.print("Expiry Day (1-31): ");
            int day = scanner.nextInt();
            scanner.nextLine();

            LocalDate expiryDate = LocalDate.of(year, month, day);

            System.out.print("Storage Temperature (°C): ");
            double temp = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Quality Grade (A/B/C...): ");
            String grade = scanner.nextLine();

           
            receivedMedicines.add(new Medicine(name, expiryDate, temp, grade));
        }
        QualityMonitor.checkMedicines(receivedMedicines);

        scanner.close();
    }
}
