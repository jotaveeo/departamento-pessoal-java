import java.io.*;
import java.util.*;

public class CadastroFuncionario {
    private Map<Integer, Funcionario> funcionarios;

    public CadastroFuncionario() {
        funcionarios = new HashMap<>();
    }

    public void cadastrarFuncionario(int codigo, String nome, String cargo, double salario, int qtdDependentes, List<String> nomesDependentes) {
        if (funcionarios.containsKey(codigo)) {
            System.out.println("Funcionário já cadastrado com o código " + codigo);
            return;
        }
        Funcionario novoFuncionario = new Funcionario(codigo, nome, cargo, salario);
        for (String nomeDependente : nomesDependentes) {
            Dependente dependente = new Dependente(novoFuncionario, nomeDependente);
            novoFuncionario.adicionarDependente(dependente);
        }
        funcionarios.put(codigo, novoFuncionario);
        System.out.println("Funcionário cadastrado com sucesso.");
    }

    public void mostrarBonusMensal() {
        for (Funcionario f : funcionarios.values()) {
            double bonus = f.calcularBonus();
            System.out.println("Funcionário: " + f.getNome() + ", Dependentes: " + f.getDependentes().size() + ", Bônus: R$ " + bonus);
        }
        gerarArquivoBonus();
    }

    public void excluirFuncionario(int codigo) {
        Funcionario funcionario = funcionarios.remove(codigo);
        if (funcionario != null) {
            System.out.println("Funcionário excluído com sucesso.");
        } else {
            System.out.println("Funcionário Inexistente");
        }
    }

    public void alterarSalarioFuncionario(int codigo, double novoSalario) {
        Funcionario funcionario = funcionarios.get(codigo);
        if (funcionario != null) {
            funcionario.setSalario(novoSalario);
            System.out.println("Salário alterado com sucesso.");
        } else {
            System.out.println("Funcionário Inexistente");
        }
    }

    private void gerarArquivoBonus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bonus_mensal.txt"))) {
            for (Funcionario f : funcionarios.values()) {
                double bonus = f.calcularBonus();
                writer.write("Funcionário: " + f.getNome() + ", Dependentes: " + f.getDependentes().size() + ", Bônus: R$ " + bonus);
                writer.newLine();
            }
            System.out.println("Arquivo de bônus gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
