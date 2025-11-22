public class Dependente {
    private Funcionario funcionario;
    private String nome;

    public Dependente(Funcionario funcionario, String nome) {
        this.funcionario = funcionario;
        this.nome = nome;
    }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
