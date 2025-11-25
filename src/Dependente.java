/**
 * Classe que representa um dependente de um funcionário.
 * Mantém referência ao funcionário responsável.
 */
public class Dependente {
    private Funcionario funcionario;
    private String nome;

    /**
     * Construtor para criar um novo dependente.
     * 
     * @param funcionario Funcionário responsável pelo dependente
     * @param nome        Nome do dependente
     */
    public Dependente(Funcionario funcionario, String nome) {
        this.funcionario = funcionario;
        this.nome = nome;
    }

    /**
     * Obtém o funcionário responsável pelo dependente.
     * 
     * @return Funcionário responsável
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável pelo dependente.
     * 
     * @param funcionario Novo funcionário responsável
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Obtém o nome do dependente.
     * 
     * @return Nome do dependente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do dependente.
     * 
     * @param nome Novo nome do dependente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
