import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeOperations {
    private Connection connection;

    public EmployeeOperations() {
        this.connection = DatabaseConnection.getConnection();
        if (this.connection == null) {
            System.err.println("ERROR: Koneksi database masih null!");
        }
    }

    // 1. Menambahkan Karyawan Baru
    public void addEmployee(String name, String department, double salary) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Karyawan berhasil ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan karyawan: " + e.getMessage());
        }
    }

    // 2. Menampilkan Semua Karyawan
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data karyawan: " + e.getMessage());
        }
        return employees;
    }

    // 3. Mengupdate Data Karyawan
    public void updateEmployee(int id, String name, String department, double salary) {
        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data karyawan berhasil diperbarui!");
            } else {
                System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal memperbarui karyawan: " + e.getMessage());
        }
    }

    // 4. Menghapus Karyawan
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Karyawan berhasil dihapus!");
            } else {
                System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menghapus karyawan: " + e.getMessage());
        }
    }
}
