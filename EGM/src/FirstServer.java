import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class FirstServer {
    public static void main(String[] args) {
        // Dosyadan yapilandirmayi okuma
        int fileCount = 10; // ornek olarak 10 dosya olusturulacak
        int fileSize = 100; // Her dosya için 100 satir
        String[] columns = {"col1", "col2", "col3"};

        DBTransactions.createTable(columns[0],columns[1],columns[2]);
        
        
        for (int i = 0; i < fileCount; i++) {
            String fileName = "file" + i + ".csv";
            File file = new File(fileName);

            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                
                System.out.println("CSV Dosyasi basliklari yaziliyor...");
                
                // CSV basliklarini yazma
                for (int j = 0; j < columns.length; j++) {
                    bw.write(columns[j]);
                    if (j != columns.length - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();

                
                System.out.println("CSV Dosyasinin icerigi rastgele olusturluyor...");
                
                // CSV icerigini rastgele olusturma
                Random random = new Random();
                
                for (int k = 0; k < fileSize; k++) {
                    for (int j = 0; j < columns.length; j++) {
                        bw.write("RandomData" + random.nextInt(100));
                        if (j != columns.length - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                bw.close();

                System.out.println("Islemler basariyla gercelesti ve ikinci servise database'e kayit etmesi icin gonderiliyor...");
                
                

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

