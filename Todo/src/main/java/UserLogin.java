import java.sql.*;

public class UserLogin {

    private static final String URL = "jdbc:sqlite:Users.db";

    public static boolean authenticate(String identifier, String pin) {

        // ปรับ SQL ให้เช็คว่า identifier ตรงกับ name หรือ email อย่างใดอย่างหนึ่ง
        String sql = "SELECT name FROM users WHERE (name = ? OR email = ?) AND pin = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // กำหนดค่าให้ Parameter (ต้องใส่ identifier 2 รอบ เพราะมี ? 2 ตัวแรก)
            pstmt.setString(1, identifier); // แทนค่า ? ตัวแรก (name)
            pstmt.setString(2, identifier); // แทนค่า ? ตัวที่สอง (email)
            pstmt.setString(3, pin);        // แทนค่า ? ตัวที่สาม (pin)

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("name");
                System.out.println("✅ Log in success: Welcome: " + userName);
                return true;
            } else {
                System.out.println("❌ Log in failed: ชื่อผู้ใช้/อีเมล หรือ PIN ไม่ถูกต้อง");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ DB Error: " + e.getMessage());
            return false;
        }
    }


}