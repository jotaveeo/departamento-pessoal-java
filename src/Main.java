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
            // Validação do código
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null)
                return;

            int codigo = Integer.parseInt(codigoStr);
            if (codigo <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Erro: Código deve ser maior que zero!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação do nome
            String nome = null;
            while (nome == null || nome.trim().isEmpty() || !nome.matches("^[A-Za-zÀ-ÿ ]+$")) {
                nome = JOptionPane.showInputDialog(null,
                        "Digite o nome do funcionário:",
                        "Cadastrar Funcionário",
                        JOptionPane.QUESTION_MESSAGE);
                if (nome == null)
                    return;

                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Nome não pode ser vazio!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!nome.matches("^[A-Za-zÀ-ÿ ]+$")) {
                    JOptionPane.showMessageDialog(null,
                            "Nome inválido. Use apenas letras!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Validação do cargo
            String cargo = null;
            while (cargo == null || cargo.trim().isEmpty() || !cargo.matches("^[A-Za-zÀ-ÿ ]+$")) {
                cargo = JOptionPane.showInputDialog(null,
                        "Digite o cargo do funcionário:",
                        "Cadastrar Funcionário",
                        JOptionPane.QUESTION_MESSAGE);
                if (cargo == null)
                    return;

                if (cargo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Cargo não pode ser vazio!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!cargo.matches("^[A-Za-zÀ-ÿ ]+$")) {
                    JOptionPane.showMessageDialog(null,
                            "Cargo inválido. Use apenas letras!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Validação do salário
            double salario = -1;
            while (salario <= 0) {
                String salarioStr = JOptionPane.showInputDialog(null,
                        "Digite o salário do funcionário:",
                        "Cadastrar Funcionário",
                        JOptionPane.QUESTION_MESSAGE);
                if (salarioStr == null)
                    return;

                try {
                    salario = Double.parseDouble(salarioStr);
                    if (salario <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Salário deve ser maior que zero!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Erro: Digite um valor numérico válido!",
                            "Erro de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Validação da quantidade de dependentes
            int qtdDependentes = -1;
            while (qtdDependentes < 0) {
                String qtdStr = JOptionPane.showInputDialog(null,
                        "Digite a quantidade de dependentes:",
                        "Cadastrar Funcionário",
                        JOptionPane.QUESTION_MESSAGE);
                if (qtdStr == null)
                    return;

                try {
                    qtdDependentes = Integer.parseInt(qtdStr);
                    if (qtdDependentes < 0) {
                        JOptionPane.showMessageDialog(null,
                                "Quantidade não pode ser negativa!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Erro: Digite um número inteiro válido!",
                            "Erro de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Validação dos nomes dos dependentes
            List<String> nomesDependentes = new ArrayList<>();
            for (int i = 0; i < qtdDependentes; i++) {
                String nomeDep = null;
                while (nomeDep == null || nomeDep.trim().isEmpty() || !nomeDep.matches("^[A-Za-zÀ-ÿ ]+$")) {
                    nomeDep = JOptionPane.showInputDialog(null,
                            "Digite o nome do dependente " + (i + 1) + ":",
                            "Cadastrar Dependente",
                            JOptionPane.QUESTION_MESSAGE);
                    if (nomeDep == null)
                        return;

                    if (nomeDep.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Nome do dependente não pode ser vazio!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (!nomeDep.matches("^[A-Za-zÀ-ÿ ]+$")) {
                        JOptionPane.showMessageDialog(null,
                                "Nome inválido. Use apenas letras!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                nomesDependentes.add(nomeDep);
            }

            cadastro.cadastrarFuncionario(codigo, nome, cargo, salario, qtdDependentes, nomesDependentes);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro inesperado: " + e.getMessage(),
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

            // Validação do salário com loop
            double novoSalario = -1;
            while (novoSalario <= 0) {
                String salarioStr = JOptionPane.showInputDialog(null,
                        "Digite o novo salário:",
                        "Alterar Salário",
                        JOptionPane.QUESTION_MESSAGE);
                if (salarioStr == null)
                    return;

                try {
                    novoSalario = Double.parseDouble(salarioStr);
                    if (novoSalario <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Salário deve ser maior que zero!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Erro: Digite um valor numérico válido!",
                            "Erro de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            cadastro.alterarSalarioFuncionario(codigo, novoSalario);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro: Código inválido!",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}