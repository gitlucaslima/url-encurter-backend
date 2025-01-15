// Conecta ao MongoDB
db = connect("mongodb://localhost:27017/admin");

// Seleciona o banco de dados correto ou cria se não existir
db = db.getSiblingDB("url-shortener");

// Cria o usuário com permissões no banco correto
db.createUser({
    user: "admin",
    pwd: "password",
    roles: [{ role: "readWrite", db: "url-shortener" }],
});

// (Opcional) Cria uma coleção inicial
db.createCollection("url-collection");
print("Banco de dados 'url-shortener' e usuário inicializados com sucesso!");
