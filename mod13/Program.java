public class Program {
    public static void main(String[] main) {
        PessoaFisica pf = new PessoaFisica("Lucas",
                06445550, "Barueri", "SP", 43502542855L, 2000.0);
        System.out.println(pf);

        PessoaJuridica pj = new PessoaJuridica("Lucas LTDA.",
                06445550, "Barueri", "SP", 12233455644l);
        System.out.println(pj);
    }
}
