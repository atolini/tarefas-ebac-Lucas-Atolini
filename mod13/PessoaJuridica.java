public final class PessoaJuridica extends Pessoa {
    long CNPJ;

    public PessoaJuridica(String name, Integer CEP, String cidade, String estado, long CNPJ) {
        super(name, CEP, cidade, estado);
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "CNPJ=" + CNPJ +
                ", name='" + name + '\'' +
                '}';
    }
}
