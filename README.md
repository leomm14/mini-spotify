# 🎧 API REST: Mini Spotify

## 📌 Sobre o Projeto
Este projeto é uma API RESTful desenvolvida em **Spring Boot** para simular as funcionalidades básicas de uma plataforma de streaming de música, inspirada no Spotify.

Foi desenvolvido como parte da avaliação (APS), com foco na construção de uma arquitetura limpa em camadas (Controller e Service), implementação de operações CRUD completas e regras de negócio específicas utilizando boas práticas REST.

---

## 🚀 Tecnologias Utilizadas
* **Java 17** * **Spring Boot 3.2+** (Spring Web)
* **Estrutura de Dados:** Collections (HashMap em memória para persistência dos dados temporários)
* **Ferramenta de Teste:** Postman

---

## 🗂️ Estrutura de Entidades
O sistema gerencia 6 entidades principais com relacionamentos simulados:
1. **Usuário:** Gerencia dados de usuários e status de atividade.
2. **Artista:** Representa os criadores ou bandas.
3. **Álbum:** Agrupa músicas e pertence a um artista (1:N).
4. **Música:** Contém as faixas, duração e métricas de reprodução.
5. **Playlist:** Coleções personalizadas criadas por usuários (N:N).
6. **Jam (Entidade Extra):** Representa "party sessions", funcionando como um grupo no Spotify onde o usuário pode criar para ouvir música ao mesmo tempo com outras pessoas.

---

## ⚙️ Funcionalidades e Regras de Negócio Implementadas

O projeto conta com CRUD para todas as entidades mencionadas. Além das operações básicas, a API conta com as seguintes lógicas de negócio:

* **▶️ Reproduzir Música (`POST /musicas/{id}/reproduzir`):** Incrementa o número de reproduções de uma faixa. O usuário é validado via header (`X-USER-ID`) e a reprodução é bloqueada (400 Bad Request) se o usuário estiver inativo.
* **➕ Adicionar à Playlist (`POST /playlists/{playlistId}/musicas/{musicaId}`):** Adiciona músicas a uma playlist. Possui validações (403 Forbidden e 400 Bad Request) para garantir que a música não seja adicionada em duplicidade e que apenas o **dono** da playlist possa modificá-la.
* **🏆 Top 10 Músicas (`GET /relatorios/top-musicas`):** Gera um relatório dinâmico utilizando Java Streams das 10 músicas mais ouvidas na plataforma, ordenadas de forma decrescente. Retorna um objeto `TopMusicasDTO` customizado contendo apenas título, nome do artista e total de reproduções.

---

## 🛠️ Como executar o projeto

1. Certifique-se de ter o **Java/JDK 17** (ou superior) instalado em sua máquina.
2. Clone este repositório ou extraia os arquivos do projeto.
3. Abra a pasta do projeto na sua IDE de preferência (IntelliJ IDEA, Eclipse, VS Code).
4. Aguarde o Maven baixar as dependências.
5. Execute a classe principal `MiniSpotifyApplication.java`.
6. A API estará rodando localmente na porta `8080` (Acesse `http://localhost:8080`).

---

## 🧪 Testando com o Postman

Junto com este projeto, foi disponibilizada a coleção de requisições **`mini-spotify.postman_collection.json`**.

**Como usar:**
1. Abra o Postman.
2. Vá em `Import` e selecione o arquivo JSON da coleção.
3. **Importante:** Como os dados são salvos de forma volátil em memória (`HashMap`), siga rigorosamente a ordem lógica de criação para evitar Erros 404 (Not Found):
    * Crie um Usuário -> Artista -> Álbum -> Música -> Playlists / Jams.
4. Para testar as rotas de regra de negócio, certifique-se de que a aba "Headers" da requisição no Postman contenha a chave `X-USER-ID` com o ID de um usuário existente.