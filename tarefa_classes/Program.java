public class Program {
    public static void main(String[] args) {
        Client client = new Client(26, true, "Lucas", "Atolini");
        Integer birthYear = client.calculateBirthYear();
        System.out.println(birthYear);
    }
}
