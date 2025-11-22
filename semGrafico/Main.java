import java.util.*;

public class Main {
    public static void main(String[] args) {
        CadastroFuncionario cadastro = new CadastroFuncionario();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n---- Menu de Cadastro de Funcionários ----");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Mostrar Bônus Mensal");
            System.out.println("3 - Excluir Funcionário");
            System.out.println("4 - Alterar Salário de Funcionário");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastrar Funcionário ---");
                    System.out.print("Digite o código do funcionário: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o cargo do funcionário: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Digite o salário do funcionário: ");
                    double salario = scanner.nextDouble();
                    System.out.print("Digite a quantidade de dependentes: ");
                    int qtdDependentes = scanner.nextInt();
                    scanner.nextLine(); 

                    List<String> nomesDependentes = new ArrayList<>();
                    for (int i = 0; i < qtdDependentes; i++) {
                        System.out.print("Digite o nome do dependente " + (i + 1) + ": ");
                        nomesDependentes.add(scanner.nextLine());
                    }
                    cadastro.cadastrarFuncionario(codigo, nome, cargo, salario, qtdDependentes, nomesDependentes);
                    break;

                case 2:
                    cadastro.mostrarBonusMensal();
                    break;

                case 3:
                    System.out.print("Digite o código do funcionário a ser excluído: ");
                    int codigoExcluir = scanner.nextInt();
                    cadastro.excluirFuncionario(codigoExcluir);
                    break;

                case 4:
                    System.out.print("Digite o código do funcionário para alterar o salário: ");
                    int codigoAlterar = scanner.nextInt();
                    System.out.print("Digite o novo salário: ");
                    double novoSalario = scanner.nextDouble();
                    cadastro.alterarSalarioFuncionario(codigoAlterar, novoSalario);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 5);

        scanner.close(); 
    }
}
