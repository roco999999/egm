import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVtoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/egm";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "riften06";
    private static final String TABLE_NAME = "egm";
    private static final String FOLDER_PATH = "C:\\campspring\\EGM";

    public static void main(String[] args) {
        while (true) {
            try {
                File folder = new File(FOLDER_PATH);
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().endsWith(".csv")) {
                            System.out.println("Dosya adi: " + file.getName());
                            readCSV(file);
                            file.delete(); // işlenen dosyayı sil
                        }
                    }
                } else {
                    System.out.println("Klasor bos veya bulunamadi.");
                }

                Thread.sleep(60000); // 1 dakika bekleme
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void readCSV(File file) {
        String line = "";
        String cvsSplitBy = ",";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (connection != null) {
                System.out.println("Veritabaina baglandi!");

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String[] columns = br.readLine().split(cvsSplitBy);
                    StringBuilder sql = new StringBuilder("INSERT INTO " + TABLE_NAME + " (");

                    for (int i = 0; i < columns.length; i++) {
                        sql.append(columns[i]);
                        if (i != columns.length - 1) {
                            sql.append(", ");
                        } else {
                            sql.append(") VALUES (");
                            for (int j = 0; j < columns.length; j++) {
                                sql.append("?");
                                if (j != columns.length - 1) {
                                    sql.append(", ");
                                } else {
                                    sql.append(")");
                                }
                            }
                        }
                    }

                    PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(cvsSplitBy);
                        for (int i = 0; i < data.length; i++) {
                            preparedStatement.setString(i + 1, data[i]);
                        }
                        preparedStatement.executeUpdate();
                    }

                    System.out.println("Veri basariyla eklendi!");
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Hata olustu: " + e.getMessage());
        }
    }
}

