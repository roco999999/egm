import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTransactions {
	public static void createTable(String col1, String col2, String col3) {
		String url = "jdbc:postgresql://localhost:5432/egm"; // Veritabanı bağlantı URL'si
		String username = "postgres"; // Veritabanı kullanıcı adı
		String password = "riften06"; // Veritabanı şifresi

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			if (connection != null) {
				System.out.println("Veritabanina baglandi!");

				Statement statement = connection.createStatement();
				String tableName = "egm";

				String columnNames = col1 + " VARCHAR(255), " + col2 + " VARCHAR(255), " + col3 + " VARCHAR(255)"; // Burada
																													// stringlerden
																													// oluşturulan
																													// kolon
																													// adlari mevcut

				String createTableQuery = "CREATE TABLE " + tableName + " (" + columnNames + ")";
				statement.executeUpdate(createTableQuery);

				System.out.println("Tablo oluşturuldu!");
			}
		} catch (SQLException e) {
			System.out.println("Hata olustu: " + e.getMessage());
		}
	}
}
