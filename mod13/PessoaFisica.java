public final class PessoaFisica extends Pessoa {
    long CPF;
    Double remuneracao;

    public PessoaFisica(String name, Integer CEP, String cidade, String estado, long CPF, Double remuneracao) {
        super(name, CEP, cidade, estado);
        this.CPF = CPF;
        this.remuneracao = remuneracao;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "CPF=" + CPF +
                ", name='" + name + '\'' +
                '}';
    }
}
