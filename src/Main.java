import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeOperations employeeOps = new EmployeeOperations();

        while (true) {
            System.out.println("\n=== MENU MANAJEMEN KARYAWAN ===");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Lihat Semua Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsum newline

            switch (choice) {
                case 1:
                    System.out.print("Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Departemen: ");
                    String department = scanner.nextLine();
                    System.out.print("Gaji: ");
                    double salary = scanner.nextDouble();
                    employeeOps.addEmployee(name, department, salary);
                    break;
                case 2:
                    List<Employee> employees = employeeOps.getAllEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("Belum ada karyawan.");
                    } else {
                        employees.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("ID Karyawan: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nama Baru: ");
                    String newName = scanner.nextLine();
                    System.out.print("Departemen Baru: ");
                    String newDept = scanner.nextLine();
                    System.out.print("Gaji Baru: ");
                    double newSalary = scanner.nextDouble();
                    employeeOps.updateEmployee(idUpdate, newName, newDept, newSalary);
                    break;
                case 4:
                    System.out.print("ID Karyawan yang akan dihapus: ");
                    int idDelete = scanner.nextInt();
                    employeeOps.deleteEmployee(idDelete);
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
