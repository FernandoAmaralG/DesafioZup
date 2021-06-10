# DesafioZup
Criando uma API REST para controle de uma lista de veículo por usuário utilizando Spring Boot e consumindo API FIPE utilizando Feing

Para esse desafio devemos construir o seguinte:
· O primeiro passo deve ser a construção de um cadastro de usuários, sendo obrigatórios: nome, e-mail, CPF e data de nascimento, sendo que e-mail e CPF devem ser únicos.
. O segundo passo é criar um cadastro de veículos, sendo obrigatórios: Marca, Modelo do Veículo e Ano. E o serviço deve consumir a API da FIPE (https://deividfortuna.github.io/fipe/) para obter os dados do valor do veículo baseado nas informações inseridas.
. O terceiro passo é criar um endpoint que retornará um usuário com a lista de todos seus veículos cadastrados.
Devemos construir 3 endpoints neste sistema, o cadastro do usuário, o cadastro de veículo e a listagem dos veículos para um usuário específico.

No endpoint que listará seus veículos, devemos considerar algumas configurações a serem exibidas para o usuário final. Vamos criar dois novos atributos no objeto do carro, sendo eles:

1.) Dia do rodízio deste carro, baseado no último número do ano do veículo, considerando as condicionais:
Final 0-1: segunda-feira
Final 2-3: terça-feira
Final 4-5: quarta-feira
Final 6-7: quinta-feira
Final 8-9: sexta-feira

2.) Também devemos criar um atributo de rodízio ativo, que compara a data atual do sistema com as condicionais anteriores e, quando for o dia ativo do rodizio, retorna true; caso contrário, false.
