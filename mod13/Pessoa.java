public abstract class Pessoa {
    String name;
    Integer CEP;
    String cidade;
    String estado;

    public Pessoa(String name, Integer CEP, String cidade, String estado) {
        this.name = name;
        this.CEP = CEP;
        this.cidade = cidade;
        this.estado = estado;
    }
}
