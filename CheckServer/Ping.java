import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ping {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Server> servidores = new ArrayList<>();
        Server server1 = new Server("172.16.3.18", "servidor 1", "victor@gmail.com");
        servidores.add(server1);
        System.out.print("Tempo entre testes: ");
        int tempoEntreTestes = entrada.nextInt();
        int cont = 0;
        while (true) {
            try {
                Thread.sleep(tempoEntreTestes);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Server servidor : servidores) {
                try {
                    if (InetAddress.getByName(servidor.getIp()).isReachable(5000)){

                        if (servidor.getFirstSuccessfultVerification() == null) {
                            servidor.setFirstSuccessfultVerification(LocalTime.now());
                            servidor.setLastSuccessfulVerification(LocalTime.now());
                            servidor.setFirstInactiveVerification(null);
                            servidor.setLastInactiveVerification(null);
                        } else {
                            servidor.setLastSuccessfulVerification(LocalTime.now());
                            servidor.setFirstInactiveVerification(null);
                            servidor.setLastInactiveVerification(null);
                        }
                        System.out.println("ok");
                    } else {
                        cont++;
                        if (servidor.getFirstInactiveVerification() == null) {
                            servidor.setFirstInactiveVerification(LocalTime.now());
                            servidor.setLastInactiveVerification(LocalTime.now());
                        } else {
                            servidor.setLastInactiveVerification(LocalTime.now());
                        }
                        System.out.println(servidor.showLog(cont));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}