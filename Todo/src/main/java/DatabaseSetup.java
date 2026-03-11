package Todolist.Todo.src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void DBSetup() {

        // 1. กำหนด URL (วางไฟล์ไว้ที่หน้าแรกของ Project เพื่อความชัวร์)
        // ไฟล์ Users.db จะถูกสร้างขึ้นมาเองอัตโนมัติในโฟลเดอร์ Todo
        String url = "jdbc:sqlite:Users.db";

        // 2. คำสั่ง SQL สำหรับสร้างตาราง
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "email TEXT NOT NULL UNIQUE,"
                + "pin TEXT NOT NULL,"
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ");";

        // 3. เริ่มการเชื่อมต่อ (Try-with-resources)
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // 4. สั่งรันคำสั่ง SQL
            stmt.execute(sql);

            System.out.println("===============================");
            System.out.println("✅ Setup DB success");
            System.out.println("📍 ไฟล์ถูกสร้างที่: " + System.getProperty("user.dir") + "/Users.db");
            System.out.println("===============================");

        } catch (SQLException e) {
            System.out.println("❌ เกิดข้อผิดพลาด: " + e.getMessage());
        }
    }
}