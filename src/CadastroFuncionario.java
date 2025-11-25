import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe responsável pelo gerenciamento do cadastro de funcionários.
 * Implementa operações de CRUD e cálculo de bônus.
 */
public class CadastroFuncionario {
    private Map<Integer, Funcionario> funcionarios;

    public CadastroFuncionario() {
        funcionarios = new HashMap<>();
    }

    /**
     * Cadastra um novo funcionário com seus dependentes.
     * 
     * @param codigo           Código único do funcionário
     * @param nome             Nome do funcionário
     * @param cargo            Cargo do funcionário
     * @param salario          Salário base do funcionário
     * @param qtdDependentes   Quantidade de dependentes
     * @param nomesDependentes Lista com nomes dos dependentes
     */
    public void cadastrarFuncionario(int codigo, String nome, String cargo, double salario, int qtdDependentes,
            List<String> nomesDependentes) {
        if (funcionarios.containsKey(codigo)) {
            JOptionPane.showMessageDialog(null,
                    "Funcionário já cadastrado com o código " + codigo,
                    "Erro - Código Duplicado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (salario <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Salário deve ser maior que zero.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (codigo <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Código deve ser maior que zero.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Funcionario novoFuncionario = new Funcionario(codigo, nome, cargo, salario);
        for (String nomeDependente : nomesDependentes) {
            Dependente dependente = new Dependente(novoFuncionario, nomeDependente);
            novoFuncionario.adicionarDependente(dependente);
        }
        funcionarios.put(codigo, novoFuncionario);
        JOptionPane.showMessageDialog(null,
                "Funcionário cadastrado com sucesso!\n\n" +
                        "Código: " + codigo + "\n" +
                        "Nome: " + nome + "\n" +
                        "Cargo: " + cargo + "\n" +
                        String.format("Salário: R$ %.2f\n", salario) +
                        "Dependentes: " + qtdDependentes,
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostra o bônus mensal de cada funcionário e gera arquivo de texto.
     * Funcionários têm 2% de aumento no salário para cada dependente.
     */
    public void mostrarBonusMensal() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum funcionário cadastrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder mensagem = new StringBuilder("===== BÔNUS MENSAL DOS FUNCIONÁRIOS =====\n\n");
        for (Funcionario f : funcionarios.values()) {
            double bonus = f.calcularBonus();
            mensagem.append(String.format("Funcionário: %s\n", f.getNome()));
            mensagem.append(String.format("Dependentes: %d\n", f.getDependentes().size()));
            mensagem.append(String.format("Bônus: R$ %.2f\n\n", bonus));
        }

        JOptionPane.showMessageDialog(null,
                mensagem.toString(),
                "Relatório de Bônus",
                JOptionPane.INFORMATION_MESSAGE);

        gerarArquivoBonus();
    }

    /**
     * Exclui um funcionário e todos os seus dependentes.
     * 
     * @param codigo Código do funcionário a ser excluído
     */
    public void excluirFuncionario(int codigo) {
        Funcionario funcionario = funcionarios.remove(codigo);
        if (funcionario != null) {
            JOptionPane.showMessageDialog(null,
                    "Funcionário e seus dependentes excluídos com sucesso!\n\n" +
                            "Código: " + codigo + "\n" +
                            "Nome: " + funcionario.getNome(),
                    "Exclusão Realizada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Funcionário Inexistente",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Altera o salário de um funcionário específico.
     * 
     * @param codigo      Código do funcionário
     * @param novoSalario Novo valor do salário
     */
    public void alterarSalarioFuncionario(int codigo, double novoSalario) {
        Funcionario funcionario = funcionarios.get(codigo);
        if (funcionario != null) {
            if (novoSalario < 0) {
                JOptionPane.showMessageDialog(null,
                        "Erro: Salário não pode ser negativo.",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double salarioAnterior = funcionario.getSalario();
            funcionario.setSalario(novoSalario);
            JOptionPane.showMessageDialog(null,
                    "Salário alterado com sucesso!\n\n" +
                            "Funcionário: " + funcionario.getNome() + "\n" +
                            String.format("Salário anterior: R$ %.2f\n", salarioAnterior) +
                            String.format("Novo salário: R$ %.2f", novoSalario),
                    "Alteração Realizada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Funcionário Inexistente",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lista todos os funcionários cadastrados com suas informações básicas.
     */
    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum funcionário cadastrado.",
                    "Lista Vazia",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("===== LISTA DE FUNCIONÁRIOS CADASTRADOS =====\n\n");
        for (Funcionario f : funcionarios.values()) {
            lista.append(String.format("Código: %d\n", f.getCodigo()));
            lista.append(String.format("Nome: %s\n", f.getNome()));
            lista.append(String.format("Cargo: %s\n", f.getCargo()));
            lista.append(String.format("Salário: R$ %.2f\n", f.getSalario()));
            lista.append(String.format("Dependentes: %d\n", f.getDependentes().size()));
            lista.append("\n" + "-".repeat(40) + "\n\n");
        }

        JOptionPane.showMessageDialog(null,
                lista.toString(),
                "Funcionários Cadastrados",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gera arquivo de texto com os dados de bônus dos funcionários.
     */
    private void gerarArquivoBonus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bonus_mensal.txt"))) {
            writer.write("===== RELATÓRIO DE BÔNUS MENSAL DOS FUNCIONÁRIOS =====\n");
            writer.write("Data: " + new java.util.Date() + "\n\n");

            for (Funcionario f : funcionarios.values()) {
                double bonus = f.calcularBonus();
                writer.write(String.format("Funcionário: %s\n", f.getNome()));
                writer.write(String.format("Código: %d\n", f.getCodigo()));
                writer.write(String.format("Cargo: %s\n", f.getCargo()));
                writer.write(String.format("Salário Base: R$ %.2f\n", f.getSalario()));
                writer.write(String.format("Dependentes: %d\n", f.getDependentes().size()));
                writer.write(String.format("Bônus (2%% por dependente): R$ %.2f\n", bonus));
                writer.write("\n" + "-".repeat(50) + "\n\n");
            }

            JOptionPane.showMessageDialog(null,
                    "Arquivo 'bonus_mensal.txt' gerado com sucesso!",
                    "Arquivo Gerado",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao gerar arquivo de bônus: " + e.getMessage(),
                    "Erro de I/O",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
