package Todolist.Todo.src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @Yasitt
 */


public class UserRegistration {

    // กำหนด URL ของฐานข้อมูลไว้เป็นตัวแปร เพื่อให้เรียกใช้ซ้ำได้ง่าย
    private static final String URL = "jdbc:sqlite:Users.db";

    public static boolean registerUser(String name, String email, String pin) {
        name = name.toLowerCase();
        email = email.toLowerCase();
        // สเต็ปที่ 1: ตรวจสอบฝั่ง Java ก่อนส่งไปฐานข้อมูล (Validation)
        // ตามโจทย์: รหัส PIN ต้องมี 4 ตัวขึ้นไป
        if (pin == null || pin.length() < 4 || name == null || email == null ) {
            System.out.println("❌ Failed: PIN have to be at least 4 digit");
            return false; // หยุดการทำงานทันที ไม่ต้องคุยกับฐานข้อมูล
        }

        // สเต็ปที่ 2: เตรียมคำสั่ง SQL (ใช้ ? เป็น Placeholder เพื่อความปลอดภัย)
        String sql = "INSERT INTO users (name, email, pin) VALUES (?, ?, ?)";

        // สเต็ปที่ 3: เชื่อมต่อและเตรียมตัวนำสาร (PreparedStatement)
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // สเต็ปที่ 4: เติมข้อมูลลงใน ? ตามลำดับ (เริ่มนับที่ 1)
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, pin);

            // สเต็ปที่ 5: สั่งประมวลผลการบันทึกข้อมูล (executeUpdate)
            pstmt.executeUpdate();
            System.out.println("✅ สมัครสมาชิกสำเร็จ! ยินดีต้อนรับคุณ " + name);
            return  true ;
        } catch (SQLException e) {
            // สเต็ปที่ 6: จัดการ Error (เช่น กรณีคนใช้อีเมลซ้ำ)
            // ถ้าจำได้ เราตั้งค่าอีเมลเป็น UNIQUE ไว้ในโครงสร้างตาราง
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("❌ Error: This email (" + email + ") already in system");
            } else {
                System.out.println("❌ DB error: " + e.getMessage());
            }
            return  false;
        }
    }

}