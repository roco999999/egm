import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SecondServer {
    // Veritabanı bağlantısı için gerekli kodlar buraya eklenmelidir.

    public static void main(String[] args) {
        try {
            int port = 9876; // Herhangi bir port numarası

            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            // İlk sunucudan gelen verileri dinleme
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputData;
            while ((inputData = bufferedReader.readLine()) != null) {
                // Veritabanına veri işleme kodunu buraya ekleyebilirsiniz.
                System.out.println("Received data: " + inputData); // Sadece gelen veriyi konsola yazdırır, gerçek işlem burada yapılmalıdır.
            }

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
