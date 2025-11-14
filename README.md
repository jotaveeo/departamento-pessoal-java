## Atividade avaliativa 3 para AV3

O departamento de pessoal de uma empresa deseja automatizar o cadastro dos funcionários e seus respectivos dependentes. Para isso, os requisitos abaixo foram repassados à equipe de desenvolvedores contratada, da qual você faz parte.

A aplicação deverá mostrar as seguintes opções ao usuário:

1. Cadastrar funcionário [2,5 pontos]

o Cada vez que essa operação for realizada, você deverá criar um novo funcionário, preencher seus dados e inseri-lo no conjunto de funcionários da empresa. Dados do funcionário: número do funcionário (código), nome do funcionário, cargo e salário. O código do funcionário deve ser único;

o Logo após, deverá realizar o cadastro de dependentes. Isso implica em criar vários dependentes e inseri-los no conjunto de dependentes (cada funcionário pode ter zero, um ou mais dependentes. O usuário dará essa quantidade). Dados do dependente: funcionário (referência para um objeto da classe de funcionários) e nome do dependente.

2. Mostrar bônus mensal de cada funcionário [2,5 pontos]

o Os funcionários têm 2% de aumento em seu salário para cada dependente. Por isso, mostre o nome de cada funcionário, seguido da quantidade de dependentes que possui e do bônus a quem tem direito.

o Gere um arquivo de texto com os dados acima.

3. Excluir funcionário [2,5 pontos]

o Você deverá excluir da lista um funcionário cujo código será informado pelo usuário;

o Todos os dependentes desse funcionário também deverão ser excluídos;

o Caso o funcionário informado não esteja cadastrado, mostre uma mensagem de erro "Funcionário Inexistente".

4. Alterar salário de um funcionário [2,5 pontos]

o Em cada posição do conjunto de funcionários, existe um objeto da classe de funcionários. Considerando que esses objetos possuam o método setSalario, você deverá procurar um funcionário (cujo código é informado pelo usuário) e alterar o seu salário;

o O valor do novo salário também deverá ser informado pelo usuário;

o Caso o funcionário informado não esteja cadastrado, mostre uma mensagem de erro "Funcionário Inexistente".

OBSERVAÇÕES:

• Utilizar modificadores de acesso nos atributos e métodos de todas as classes, de tal forma que se garanta o máximo possível de encapsulamento dos objetos;

• Deverão ser criados métodos assessores (getters) e modificadores (setters) para cada um dos atributos das classes;

• Utilizar obrigatoriamente estruturas avançadas para validar as entradas de dados (tratamento de exceções), armazenar/manipular os dados (coleções genéricas) e interagir com o usuário (interface gráfica).

• Para simplificar a interface gráfica, utilize os métodos showInputDialog e showMessageDialog da classe JOptionPane.

Seu trabalho deverá ser obrigatoriamente apresentado e submetido a uma arguição pelo professor.
ATENÇÃO: Esta atividade só terá validade para compor a nota de AV3 mediante verificação dos requisitos por via de arguição presencial ou pela produção de um vídeo da equipe que deverá compor o pacote de entregas. 
