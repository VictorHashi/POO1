import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Server {
    private String ip;
    private String nome;
    private String email;
    private LocalTime firstSuccessfultVerification;
    private LocalTime lastSuccessfulVerification;
    private LocalTime firstInactiveVerification;
    private LocalTime LastInactiveVerification;

    public Server(String ip, String nome, String email) {
        this.ip = ip;
        this.nome = nome;
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getFirstSuccessfultVerification() {
        return firstSuccessfultVerification;
    }

    public void setFirstSuccessfultVerification(LocalTime firsSuccessfultVerification) {
        this.firstSuccessfultVerification = firsSuccessfultVerification;
    }

    public LocalTime getLastSuccessfulVerification() {
        return lastSuccessfulVerification;
    }

    public void setLastSuccessfulVerification(LocalTime lastSuccessfulVerification) {
        this.lastSuccessfulVerification = lastSuccessfulVerification;
    }

    public LocalTime getFirstInactiveVerification() {
        return firstInactiveVerification;
    }

    public void setFirstInactiveVerification(LocalTime firstInactiveVerification) {
        this.firstInactiveVerification = firstInactiveVerification;
    }

    public LocalTime getLastInactiveVerification() {
        return LastInactiveVerification;
    }

    public void setLastInactiveVerification(LocalTime lastInactiveVerification) {
        LastInactiveVerification = lastInactiveVerification;
    }

    public LocalTime calculaDiferenca(LocalTime comeco, LocalTime fim) {
        long teste = comeco.until(fim, ChronoUnit.SECONDS);
        return LocalTime.ofSecondOfDay(teste);
    }

    public String showLog(int cont) {
        String log = "Erro " + cont + " - " + LocalDateTime.now() +
                " - Servidor: " + nome + " - Email responsável: " + email + " - IP: " + ip
                + "\nUltima verificação de atividade: " + lastSuccessfulVerification.minusNanos(lastSuccessfulVerification.getNano()) +
                "\nPrimeira verificação de Inatividade: " + firstInactiveVerification.minusNanos(firstInactiveVerification.getNano()) +
                "\nTempo em Atividade: " + calculaDiferenca(firstSuccessfultVerification, lastSuccessfulVerification) +
                "\nInativo à: " + calculaDiferenca(firstInactiveVerification, LocalTime.now()) + "\n";
        return log;
    }
}