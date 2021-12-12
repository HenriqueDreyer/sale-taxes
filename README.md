# Sale-Taxes Backend
Sistema de consulta e compra de produtos.

Instalação:
- Clonar o projeto do GitHub: _git clone https://github.com/HenriqueDreyer/sale-taxes.git_;
- executar no bash, dentro do diretório do projeto: _mvn clean install_;
- Pelo bash, diretório _/[your.user]/.m2/h2database/h2/1.4.200_, executar o comando _java -jar h2-1.4.200.jar_ para que inicie o banco de dados H2;

Criação do banco
- Criar/Conectar ao banco: _jdbc:h2:tcp://localhost/~/sale-taxes-db_
- Executar a queru