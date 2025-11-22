import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um funcionário da empresa.
 * Contém informações básicas e gerencia seus dependentes.
 */
public class Funcionario {
    private int codigo;
    private String nome;
    private String cargo;
    private double salario;
    private List<Dependente> dependentes;

    /**
     * Construtor para criar um novo funcionário.
     * 
     * @param codigo  Código único do funcionário
     * @param nome    Nome completo do funcionário
     * @param cargo   Cargo que o funcionário ocupa
     * @param salario Salário base do funcionário
     */
    public Funcionario(int codigo, String nome, String cargo, double salario) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.dependentes = new ArrayList<>();
    }

    // Getters e Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    /**
     * Adiciona um dependente à lista do funcionário.
     * 
     * @param dependente Dependente a ser adicionado
     */
    public void adicionarDependente(Dependente dependente) {
        dependentes.add(dependente);
    }

    /**
     * Remove um dependente da lista do funcionário.
     * 
     * @param dependente Dependente a ser removido
     */
    public void removerDependente(Dependente dependente) {
        dependentes.remove(dependente);
    }

    /**
     * Calcula o bônus mensal do funcionário.
     * Funcionários têm 2% de aumento no salário para cada dependente.
     * 
     * @return Valor do bônus mensal (2% do salário multiplicado pelo número de
     *         dependentes)
     */
    public double calcularBonus() {
        return salario * 0.02 * dependentes.size();
    }
}
