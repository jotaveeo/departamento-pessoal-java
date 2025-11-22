# Cadastro de Funcionários e Dependentes

Sistema de gerenciamento de funcionários e dependentes desenvolvido para o Departamento de Pessoal, implementando cadastro, cálculo de bônus mensal, exclusão e alteração de salário através de interface gráfica.

## 📋 Funcionalidades

1. **Cadastrar Funcionário** [2,5 pontos]

   - Cadastro de funcionário com código único, nome, cargo e salário
   - Cadastro simultâneo de dependentes (zero, um ou mais)
   - Validações de entrada (código único, salário não negativo)

2. **Mostrar Bônus Mensal** [2,5 pontos]

   - Exibe bônus de cada funcionário (2% do salário por dependente)
   - Mostra nome, quantidade de dependentes e valor do bônus
   - Gera arquivo de texto com relatório completo (`bonus_mensal.txt`)

3. **Excluir Funcionário** [2,5 pontos]

   - Remove funcionário e todos seus dependentes
   - Valida existência do funcionário
   - Mensagem de erro para funcionário inexistente

4. **Alterar Salário de Funcionário** [2,5 pontos]

   - Permite alteração do salário de um funcionário específico
   - Valida existência do funcionário e valores não negativos
   - Exibe salário anterior e novo salário

5. **Listar Funcionários** (funcionalidade adicional)
   - Visualização de todos os funcionários cadastrados
   - Informações completas: código, nome, cargo, salário e dependentes

## 🎯 Requisitos Atendidos

### ✅ Estruturas Avançadas Obrigatórias

- **Interface Gráfica**: JOptionPane (showInputDialog e showMessageDialog)
- **Tratamento de Exceções**: Try-catch para validação de entradas numéricas
- **Coleções Genéricas**: HashMap<Integer, Funcionario> e ArrayList<Dependente>

### ✅ Princípios de Orientação a Objetos

- **Encapsulamento**: Atributos privados em todas as classes
- **Getters e Setters**: Métodos assessores e modificadores implementados
- **JavaDoc**: Documentação completa em todas as classes e métodos
- **Modificadores de Acesso**: Uso adequado de private e public

## 🛠️ Tecnologias Utilizadas

- Java SE 8 ou superior
- Swing (JOptionPane) para interface gráfica
- Java Collections Framework
- Java I/O para geração de arquivos

## 📂 Estrutura do Projeto

```
DepartamentoPessoal/
├── src/
│   ├── Main.java                    # Classe principal com menu GUI
│   ├── CadastroFuncionario.java    # Lógica de gerenciamento
│   ├── Funcionario.java            # Modelo de funcionário
│   └── Dependente.java             # Modelo de dependente
├── bin/                             # Arquivos compilados
├── lib/                             # Bibliotecas externas (se necessário)
└── README.md                        # Este arquivo
```

## 🚀 Como Executar

1. **Compilar o projeto**:

   ```bash
   javac -d bin src/*.java
   ```

2. **Executar a aplicação**:

   ```bash
   java -cp bin Main
   ```

3. **Interagir com o sistema**:
   - O menu gráfico será exibido automaticamente
   - Escolha as opções usando as janelas de diálogo
   - O arquivo `bonus_mensal.txt` será gerado na raiz do projeto

## 📊 Regras de Negócio

- **Código do Funcionário**: Deve ser único e maior que zero
- **Salário**: Não pode ser negativo
- **Bônus**: Calculado como 2% do salário multiplicado pelo número de dependentes
- **Exclusão**: Remove funcionário e todos os dependentes associados automaticamente
- **Dependentes**: Cada funcionário pode ter zero, um ou mais dependentes

## 🔍 Validações Implementadas

- Código de funcionário único e válido (> 0)
- Salário não negativo
- Campos obrigatórios não vazios
- Tratamento de entradas numéricas inválidas
- Verificação de existência antes de alterar/excluir

## 📝 Observações

- Interface gráfica simplificada usando JOptionPane conforme especificação
- Arquivo de bônus gerado automaticamente ao visualizar relatório
- Todas as operações validadas com mensagens claras ao usuário
- Código totalmente documentado com JavaDoc

## 👥 Apresentação

⚠️ **IMPORTANTE**: Esta atividade requer:

- Arguição presencial com o professor, OU
- Vídeo da equipe demonstrando o funcionamento

## 📄 Licença

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos.
