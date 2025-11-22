public class Funcionario {
    private int codigo;
    private String nome;
    private String cargo;
    private double salario;
    private List<Dependente> dependentes;

    public Funcionario(int codigo, String nome, String cargo, double salario) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.dependentes = new ArrayList<>();
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public List<Dependente> getDependentes() { return dependentes; }

    public void adicionarDependente(Dependente dependente) { dependentes.add(dependente); }
    public void removerDependente(Dependente dependente) { dependentes.remove(dependente); }
    public double calcularBonus() { return salario * 0.02 * dependentes.size(); }
}
