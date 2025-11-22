import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe principal que gerencia o menu de interação com o usuário
 * utilizando interface gráfica com JOptionPane.
 */
public class Main {
    public static void main(String[] args) {
        CadastroFuncionario cadastro = new CadastroFuncionario();
        int opcao;

        do {
            String menu = "==== DEPARTAMENTO DE PESSOAL ====\n\n" +
                    "1 - Cadastrar Funcionário\n" +
                    "2 - Mostrar Bônus Mensal\n" +
                    "3 - Excluir Funcionário\n" +
                    "4 - Alterar Salário de Funcionário\n" +
                    "5 - Listar Funcionários\n" +
                    "6 - Sair\n\n" +
                    "Escolha uma opção:";

            String inputOpcao = JOptionPane.showInputDialog(null, menu,
                    "Menu Principal", JOptionPane.QUESTION_MESSAGE);

            // Se o usuário cancelar ou fechar a janela
            if (inputOpcao == null) {
                opcao = 6;
                continue;
            }

            try {
                opcao = Integer.parseInt(inputOpcao);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: Digite um número válido!",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarFuncionarioGUI(cadastro);
                    break;

                case 2:
                    cadastro.mostrarBonusMensal();
                    break;

                case 3:
                    excluirFuncionarioGUI(cadastro);
                    break;

                case 4:
                    alterarSalarioGUI(cadastro);
                    break;

                case 5:
                    cadastro.listarFuncionarios();
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null,
                            "Sistema encerrado com sucesso!",
                            "Até logo",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção inválida! Escolha entre 1 e 6.",
                            "Erro",
                            JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while (opcao != 6);
    }

    /**
     * Método auxiliar para cadastrar funcionário com interface gráfica
     */
    private static void cadastrarFuncionarioGUI(CadastroFuncionario cadastro) {
        try {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null)
                return;
            int codigo = Integer.parseInt(codigoStr);

            String nome = JOptionPane.showInputDialog(null,
                    "Digite o nome do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode ser vazio!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cargo = JOptionPane.showInputDialog(null,
                    "Digite o cargo do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (cargo == null || cargo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cargo não pode ser vazio!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String salarioStr = JOptionPane.showInputDialog(null,
                    "Digite o salário do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (salarioStr == null)
                return;
            double salario = Double.parseDouble(salarioStr);

            String qtdStr = JOptionPane.showInputDialog(null,
                    "Digite a quantidade de dependentes:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (qtdStr == null)
                return;
            int qtdDependentes = Integer.parseInt(qtdStr);

            List<String> nomesDependentes = new ArrayList<>();
            for (int i = 0; i < qtdDependentes; i++) {
                String nomeDep = JOptionPane.showInputDialog(null,
                        "Digite o nome do dependente " + (i + 1) + ":",
                        "Cadastrar Dependente",
                        JOptionPane.QUESTION_MESSAGE);
                if (nomeDep == null || nomeDep.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Nome do dependente não pode ser vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    i--;
                    continue;
                }
                nomesDependentes.add(nomeDep);
            }

            cadastro.cadastrarFuncionario(codigo, nome, cargo, salario, qtdDependentes, nomesDependentes);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Valor numérico inválido!\n" + e.getMessage(),
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método auxiliar para excluir funcionário com interface gráfica
     */
    private static void excluirFuncionarioGUI(CadastroFuncionario cadastro) {
        try {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário a ser excluído:",
                    "Excluir Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null)
                return;
            int codigo = Integer.parseInt(codigoStr);

            cadastro.excluirFuncionario(codigo);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Código inválido!",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método auxiliar para alterar salário com interface gráfica
     */
    private static void alterarSalarioGUI(CadastroFuncionario cadastro) {
        try {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário:",
                    "Alterar Salário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null)
                return;
            int codigo = Integer.parseInt(codigoStr);

            String salarioStr = JOptionPane.showInputDialog(null,
                    "Digite o novo salário:",
                    "Alterar Salário",
                    JOptionPane.QUESTION_MESSAGE);
            if (salarioStr == null)
                return;
            double novoSalario = Double.parseDouble(salarioStr);

            cadastro.alterarSalarioFuncionario(codigo, novoSalario);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Valor numérico inválido!",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}