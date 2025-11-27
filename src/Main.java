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
        // Validar cada campo individualmente. Se o usuário inserir um valor inválido,
        // mostramos mensagem e repetimos a solicitação para o mesmo campo.
        // Se o usuário pressionar Cancelar (input == null), retornamos ao menu.

        // Código (inteiro)
        Integer codigo = null;
        while (codigo == null) {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null) // cancelar
                return;
            try {
                codigo = Integer.parseInt(codigoStr.trim()); // remover espaços
                if (codigo <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Código deve ser um número inteiro maior que zero.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                    codigo = null;
                } else if (cadastro.existeFuncionario(codigo)) {
                    JOptionPane.showMessageDialog(null,
                            "Erro: já existe um funcionário cadastrado com esse código. Digite outro código.",
                            "Código Duplicado",
                            JOptionPane.ERROR_MESSAGE);
                    codigo = null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: código inválido. Digite apenas números inteiros.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Nome (somente letras e espaços)
        String nome = null;
        while (nome == null) {
            String input = JOptionPane.showInputDialog(null,
                    "Digite o nome do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null)
                return;
            try {
                String trimmed = input.trim();
                if (trimmed.isEmpty())
                    throw new IllegalArgumentException("Nome não pode ser vazio.");
                if (!trimmed.matches("[A-Za-zÀ-ÿ\\s]+"))
                    throw new IllegalArgumentException("Nome inválido. Use somente letras e espaços.");
                nome = trimmed;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro no nome: " + e.getMessage(),
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Cargo (somente letras, números simples de cargo podem ser permitidos, mas aqui somente letras e espaços)
        String cargo = null;
        while (cargo == null) {
            String input = JOptionPane.showInputDialog(null,
                    "Digite o cargo do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null)
                return;
            try {
                String trimmed = input.trim();
                if (trimmed.isEmpty())
                    throw new IllegalArgumentException("Cargo não pode ser vazio.");
                if (!trimmed.matches("[A-Za-zÀ-ÿ\\s]+"))
                    throw new IllegalArgumentException("Cargo inválido. Use somente letras e espaços.");
                cargo = trimmed;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro no cargo: " + e.getMessage(),
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Salário (double)
        Double salario = null;
        while (salario == null) {
            String salarioStr = JOptionPane.showInputDialog(null,
                    "Digite o salário do funcionário:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (salarioStr == null)
                return;
            try {
                salario = Double.parseDouble(salarioStr.trim());
                if (salario <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Salário deve ser maior que zero.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                    salario = null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: salário inválido. Digite um número (ex: 2500.50).",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Quantidade de dependentes (inteiro >= 0)
        Integer qtdDependentes = null;
        while (qtdDependentes == null) {
            String qtdStr = JOptionPane.showInputDialog(null,
                    "Digite a quantidade de dependentes:",
                    "Cadastrar Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (qtdStr == null)
                return;
            try {
                qtdDependentes = Integer.parseInt(qtdStr.trim());
                if (qtdDependentes < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Quantidade de dependentes não pode ser negativa.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                    qtdDependentes = null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: quantidade inválida. Digite um número inteiro (ex: 0, 1, 2).",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Nomes dos dependentes (cada um validado como nome)
        List<String> nomesDependentes = new ArrayList<>();
        for (int i = 0; i < qtdDependentes; i++) {
            String nomeDep = null;
            while (nomeDep == null) {
                String input = JOptionPane.showInputDialog(null,
                        "Digite o nome do dependente " + (i + 1) + ":",
                        "Cadastrar Dependente",
                        JOptionPane.QUESTION_MESSAGE);
                if (input == null) // permitir cancelar todo o cadastro
                    return;
                try {
                    String trimmed = input.trim();
                    if (trimmed.isEmpty())
                        throw new IllegalArgumentException("Nome do dependente não pode ser vazio.");
                    if (!trimmed.matches("[A-Za-zÀ-ÿ\\s]+"))
                        throw new IllegalArgumentException("Nome do dependente inválido. Use somente letras e espaços.");
                    nomeDep = trimmed;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null,
                            "Erro no nome do dependente: " + e.getMessage(),
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            nomesDependentes.add(nomeDep);
        }

        // Efetua cadastro
        cadastro.cadastrarFuncionario(codigo, nome, cargo, salario, qtdDependentes, nomesDependentes);
    }

    /**
     * Método auxiliar para excluir funcionário com interface gráfica
     */
    private static void excluirFuncionarioGUI(CadastroFuncionario cadastro) {
        Integer codigo = null;
        while (codigo == null) {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário a ser excluído:",
                    "Excluir Funcionário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null) // cancelar
                return;
            try {
                int c = Integer.parseInt(codigoStr.trim());
                if (!cadastro.existeFuncionario(c)) {
                    JOptionPane.showMessageDialog(null,
                            "Funcionário inexistente. Digite outro código ou cancele.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    codigo = c;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: Código inválido! Digite somente números inteiros.",
                        "Erro de Entrada",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        cadastro.excluirFuncionario(codigo);
    }

    /**
     * Método auxiliar para alterar salário com interface gráfica
     */
    private static void alterarSalarioGUI(CadastroFuncionario cadastro) {
        // Ler código e verificar existência antes de pedir novo salário
        Integer codigo = null;
        while (codigo == null) {
            String codigoStr = JOptionPane.showInputDialog(null,
                    "Digite o código do funcionário:",
                    "Alterar Salário",
                    JOptionPane.QUESTION_MESSAGE);
            if (codigoStr == null)
                return;
            try {
                int c = Integer.parseInt(codigoStr.trim());
                if (!cadastro.existeFuncionario(c)) {
                    JOptionPane.showMessageDialog(null,
                            "Funcionário inexistente. Digite outro código ou cancele.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    codigo = c;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: código inválido. Digite apenas números inteiros.",
                        "Erro de Entrada",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Novo salário (double) - repetir até válido
        Double novoSalario = null;
        while (novoSalario == null) {
            String salarioStr = JOptionPane.showInputDialog(null,
                    "Digite o novo salário:",
                    "Alterar Salário",
                    JOptionPane.QUESTION_MESSAGE);
            if (salarioStr == null)
                return;
            try {
                double s = Double.parseDouble(salarioStr.trim());
                if (s < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Salário não pode ser negativo.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    novoSalario = s;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro: Valor numérico inválido para salário.",
                        "Erro de Entrada",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        cadastro.alterarSalarioFuncionario(codigo, novoSalario);
    }
}