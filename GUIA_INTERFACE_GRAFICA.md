# 📱 Guia Completo: Interface Gráfica com JOptionPane

## 📚 Índice
1. [O que é JOptionPane?](#o-que-é-joptionpane)
2. [Por que usar JOptionPane?](#por-que-usar-joptionpane)
3. [Tipos de Diálogos](#tipos-de-diálogos)
4. [Implementação no Projeto](#implementação-no-projeto)
5. [Exemplos Práticos](#exemplos-práticos)
6. [Tratamento de Exceções](#tratamento-de-exceções)
7. [Pontos para Apresentação](#pontos-para-apresentação)

---

## 🎯 O que é JOptionPane?

**JOptionPane** é uma classe da biblioteca **Swing** do Java que permite criar facilmente caixas de diálogo (janelas pop-up) para interagir com o usuário de forma gráfica, sem precisar criar uma interface complexa.

### Localização na API Java
```java
import javax.swing.JOptionPane;
```

### Características Principais
- ✅ **Fácil de usar** - Apenas uma linha de código
- ✅ **Visual** - Interface gráfica moderna do sistema operacional
- ✅ **Multiplataforma** - Funciona em Windows, Linux e Mac
- ✅ **Pronto para uso** - Não precisa configurar janelas ou layouts

---

## 💡 Por que usar JOptionPane?

### Vantagens sobre Console (Scanner)

| Aspecto | Console (Scanner) | JOptionPane |
|---------|------------------|-------------|
| **Interface** | Texto puro, preto e branco | Janelas gráficas coloridas |
| **Usabilidade** | Apenas teclado | Mouse e teclado |
| **Feedback Visual** | Limitado | Ícones e formatação |
| **Erros** | Texto simples | Diálogos de erro destacados |
| **Profissionalismo** | Básico | Profissional |

### Requisitos da AV3
O enunciado exige **obrigatoriamente**:
> "Para simplificar a interface gráfica, utilize os métodos showInputDialog e showMessageDialog da classe JOptionPane."

---

## 🎨 Tipos de Diálogos

### 1. **showMessageDialog** - Exibir Mensagens

Usado para **mostrar informações** ao usuário.

```java
JOptionPane.showMessageDialog(
    null,                           // Componente pai (null = centro da tela)
    "Mensagem aqui",                // Texto da mensagem
    "Título da janela",             // Título
    JOptionPane.INFORMATION_MESSAGE // Tipo do ícone
);
```

#### Tipos de Mensagem (Ícones)

| Constante | Ícone | Quando Usar |
|-----------|-------|-------------|
| `INFORMATION_MESSAGE` | ℹ️ Info | Confirmações, informações gerais |
| `ERROR_MESSAGE` | ❌ Erro | Erros, validações falhas |
| `WARNING_MESSAGE` | ⚠️ Aviso | Alertas, avisos importantes |
| `QUESTION_MESSAGE` | ❓ Pergunta | Perguntas ao usuário |
| `PLAIN_MESSAGE` | (sem ícone) | Mensagens neutras |

### 2. **showInputDialog** - Receber Entrada

Usado para **solicitar dados** do usuário.

```java
String resposta = JOptionPane.showInputDialog(
    null,                           // Componente pai
    "Digite seu nome:",             // Pergunta
    "Entrada de Dados",             // Título
    JOptionPane.QUESTION_MESSAGE    // Tipo
);
```

#### Retorno importante
- **String** com o valor digitado
- **null** se o usuário cancelar ou fechar a janela

---

## 🔧 Implementação no Projeto

### Estrutura do Menu Principal

```java
String menu = "==== DEPARTAMENTO DE PESSOAL ====\n\n" +
              "1 - Cadastrar Funcionário\n" +
              "2 - Mostrar Bônus Mensal\n" +
              "3 - Excluir Funcionário\n" +
              "4 - Alterar Salário de Funcionário\n" +
              "5 - Listar Funcionários\n" +
              "6 - Sair\n\n" +
              "Escolha uma opção:";

String inputOpcao = JOptionPane.showInputDialog(
    null, 
    menu, 
    "Menu Principal", 
    JOptionPane.QUESTION_MESSAGE
);
```

**Por que assim?**
- **StringBuilder com \n** - Quebras de linha para organização
- **Texto descritivo** - Usuário entende claramente as opções
- **QUESTION_MESSAGE** - Ícone de interrogação indica que esperamos resposta

### Tratamento do Cancelamento

```java
if (inputOpcao == null) {
    opcao = 6; // Sai do sistema
    continue;
}
```

**Importante:** Sempre verificar se `inputOpcao == null` antes de usar o valor!

---

## 💻 Exemplos Práticos

### Exemplo 1: Cadastrar Funcionário

#### Entrada de Dados Numéricos
```java
// Solicita o código
String codigoStr = JOptionPane.showInputDialog(
    null, 
    "Digite o código do funcionário:", 
    "Cadastrar Funcionário", 
    JOptionPane.QUESTION_MESSAGE
);

// Verifica se cancelou
if (codigoStr == null) return;

// Converte para número com tratamento de erro
try {
    int codigo = Integer.parseInt(codigoStr);
    // Usa o código...
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(
        null, 
        "Erro: Código inválido!", 
        "Erro de Entrada", 
        JOptionPane.ERROR_MESSAGE
    );
}
```

**Fluxo:**
1. 📝 Solicita entrada
2. ✅ Verifica se cancelou
3. 🔄 Tenta converter para número
4. ❌ Mostra erro se inválido

#### Entrada de Texto
```java
String nome = JOptionPane.showInputDialog(
    null, 
    "Digite o nome do funcionário:", 
    "Cadastrar Funcionário", 
    JOptionPane.QUESTION_MESSAGE
);

// Valida se não está vazio
if (nome == null || nome.trim().isEmpty()) {
    JOptionPane.showMessageDialog(
        null, 
        "Nome não pode ser vazio!", 
        "Erro", 
        JOptionPane.ERROR_MESSAGE
    );
    return;
}
```

### Exemplo 2: Mensagens de Sucesso

```java
JOptionPane.showMessageDialog(
    null, 
    "Funcionário cadastrado com sucesso!\n\n" +
    "Código: " + codigo + "\n" +
    "Nome: " + nome + "\n" +
    "Cargo: " + cargo + "\n" +
    String.format("Salário: R$ %.2f\n", salario) +
    "Dependentes: " + qtdDependentes,
    "Sucesso",
    JOptionPane.INFORMATION_MESSAGE
);
```

**Técnicas usadas:**
- **\n\n** - Espaçamento para legibilidade
- **String.format()** - Formatação de valores monetários
- **Concatenação** - Múltiplas informações em uma mensagem

### Exemplo 3: Mensagens de Erro

```java
if (funcionarios.containsKey(codigo)) {
    JOptionPane.showMessageDialog(
        null, 
        "Funcionário já cadastrado com o código " + codigo,
        "Erro - Código Duplicado",
        JOptionPane.ERROR_MESSAGE
    );
    return;
}
```

**Destaque:**
- ❌ **ERROR_MESSAGE** - Ícone vermelho de erro
- 📌 **Título descritivo** - "Erro - Código Duplicado"
- 💬 **Mensagem clara** - Explica exatamente o problema

### Exemplo 4: Relatórios Formatados

```java
StringBuilder mensagem = new StringBuilder(
    "===== BÔNUS MENSAL DOS FUNCIONÁRIOS =====\n\n"
);

for (Funcionario f : funcionarios.values()) {
    double bonus = f.calcularBonus();
    mensagem.append(String.format("Funcionário: %s\n", f.getNome()));
    mensagem.append(String.format("Dependentes: %d\n", f.getDependentes().size()));
    mensagem.append(String.format("Bônus: R$ %.2f\n\n", bonus));
}

JOptionPane.showMessageDialog(
    null, 
    mensagem.toString(),
    "Relatório de Bônus",
    JOptionPane.INFORMATION_MESSAGE
);
```

**Por que StringBuilder?**
- ⚡ **Performance** - Mais eficiente para concatenar múltiplas strings
- 📝 **Clareza** - Código mais limpo e organizado
- 🔄 **Loop-friendly** - Ideal para iterar coleções

---

## ⚠️ Tratamento de Exceções

### Por que tratar exceções?

Quando o usuário digita texto onde esperamos número:
```
Entrada: "abc"
Conversão: Integer.parseInt("abc") → NumberFormatException ❌
```

### Padrão de Tratamento

```java
try {
    // Tenta converter
    int valor = Integer.parseInt(entrada);
    // Usa o valor...
    
} catch (NumberFormatException e) {
    // Captura o erro e mostra mensagem amigável
    JOptionPane.showMessageDialog(
        null, 
        "Erro: Digite um número válido!", 
        "Erro de Entrada", 
        JOptionPane.ERROR_MESSAGE
    );
}
```

### Aplicado no Menu

```java
try {
    opcao = Integer.parseInt(inputOpcao);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(
        null, 
        "Erro: Digite um número válido!", 
        "Entrada Inválida", 
        JOptionPane.ERROR_MESSAGE
    );
    opcao = 0; // Valor inválido para repetir loop
    continue;  // Volta ao início do loop
}
```

---

## 🎤 Pontos para Apresentação

### 1. **Justificativa da Escolha**

> "Utilizamos JOptionPane porque é um **requisito obrigatório** da atividade e porque oferece uma **interface gráfica simples e profissional** sem a complexidade de criar janelas completas com Swing ou JavaFX."

### 2. **Vantagens Implementadas**

> "Nossa implementação oferece:
> - ✅ **Feedback visual claro** com ícones apropriados para cada situação
> - ✅ **Validação de dados** com mensagens de erro destacadas
> - ✅ **Formatação profissional** com valores monetários e relatórios organizados
> - ✅ **Tratamento robusto** de cancelamentos e entradas inválidas"

### 3. **Demonstração do Fluxo**

**Exemplo: Cadastrar Funcionário**

```
1. 📋 Menu aparece → Usuário escolhe opção 1
2. 🔢 Solicita código → Validação de número
3. ✍️ Solicita nome → Validação de campo vazio
4. ✍️ Solicita cargo → Validação de campo vazio
5. 💰 Solicita salário → Validação de número e valor positivo
6. 👨‍👩‍👧 Solicita dependentes → Loop para cada dependente
7. ✅ Confirmação → Mensagem de sucesso com todos os dados
```

### 4. **Diferencial Técnico**

> "Além dos requisitos básicos, implementamos:
> - 🎯 **Métodos auxiliares** para organizar o código (cadastrarFuncionarioGUI, etc.)
> - 📊 **StringBuilder** para relatórios complexos com melhor performance
> - 🔒 **Validações múltiplas** garantindo integridade dos dados
> - 💬 **Mensagens contextualizadas** que orientam o usuário"

### 5. **Tratamento de Erros**

> "Todo diálogo de entrada possui tratamento de exceções. Se o usuário digitar um texto onde esperamos número, capturamos o `NumberFormatException` e exibimos uma mensagem clara com ícone de erro, permitindo que tente novamente."

### 6. **Comparação Antes e Depois**

**Antes (Console):**
```
Digite o código: abc
Exception in thread "main" java.util.InputMismatchException
    at java.util.Scanner.throwFor(Scanner.java:...)
```

**Depois (JOptionPane):**
```
[Janela com ícone ❌]
Título: "Erro de Entrada"
Mensagem: "Erro: Digite um número válido!"
[Botão OK]
```

---

## 📖 Conceitos-Chave para Memorizar

### 1. **Dois métodos principais**
- `showInputDialog` → Recebe entrada do usuário
- `showMessageDialog` → Exibe mensagens ao usuário

### 2. **Quatro tipos de ícone**
- `INFORMATION_MESSAGE` → Sucesso/Confirmação
- `ERROR_MESSAGE` → Erros
- `WARNING_MESSAGE` → Avisos
- `QUESTION_MESSAGE` → Perguntas

### 3. **Sempre verificar null**
```java
if (input == null) return; // Usuário cancelou
```

### 4. **Sempre tratar NumberFormatException**
```java
try {
    int numero = Integer.parseInt(input);
} catch (NumberFormatException e) {
    // Mostra erro
}
```

### 5. **Formatação profissional**
```java
String.format("R$ %.2f", valor)  // Moeda com 2 decimais
mensagem.append("\n")             // Quebra de linha
```

---

## 🎯 Checklist para Apresentação

Antes de apresentar, certifique-se:

- [ ] Sei explicar o que é JOptionPane
- [ ] Conheço os dois métodos principais (show**Input** e show**Message**)
- [ ] Sei diferenciar os 4 tipos de mensagem (INFO, ERROR, WARNING, QUESTION)
- [ ] Entendo por que verificamos `== null`
- [ ] Sei explicar o try-catch para NumberFormatException
- [ ] Consigo demonstrar o fluxo completo de uma funcionalidade
- [ ] Sei apontar as validações implementadas
- [ ] Entendo as vantagens sobre console

---

## 💡 Dicas para a Apresentação

### ✅ **O que FALAR:**
- "Implementamos interface gráfica conforme requisito obrigatório"
- "Utilizamos JOptionPane da biblioteca Swing"
- "Todos os diálogos possuem tratamento de exceções"
- "Validamos todas as entradas antes de processar"
- "Mensagens contextualizadas orientam o usuário"

### ❌ **O que EVITAR:**
- "É só um JOptionPane básico" (minimize o trabalho)
- "Foi fácil de fazer" (valorize o esforço)
- Não saber explicar o código
- Não demonstrar funcionando

### 🎬 **Roteiro de Demonstração:**
1. **Mostre o menu** - Interface inicial
2. **Cadastre com sucesso** - Fluxo feliz
3. **Provoque um erro** - Digite texto em número
4. **Cancele uma operação** - Clique em Cancelar
5. **Mostre o relatório** - Bônus formatado
6. **Mostre o arquivo gerado** - bonus_mensal.txt

---

## 📚 Referências e Estudo Adicional

### Documentação Oficial
- [JOptionPane - Oracle Docs](https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html)

### Pontos de Estudo
1. Diferença entre Swing e AWT
2. Por que `null` no primeiro parâmetro?
3. Como criar diálogos personalizados
4. Alternativas: JavaFX, Console, Web

---

## ✨ Conclusão

A interface gráfica com JOptionPane torna nosso sistema:
- 🎨 **Mais profissional** visualmente
- ✅ **Mais amigável** para usuários
- 🛡️ **Mais robusto** com validações
- 📱 **Moderno** e adequado ao requisito acadêmico

**Boa sorte na apresentação! 🚀**

---

*Documento criado para estudo e apresentação da AV3 - Programação Orientada a Objetos*
*Data: 22 de novembro de 2025*
