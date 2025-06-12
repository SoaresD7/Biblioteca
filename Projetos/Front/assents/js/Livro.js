// API GET - Carrega dados ao iniciar
fetch('http://localhost:8080/api/alunos', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  },
})
.then(response => response.json())
.then(data => {
  addlinha(data);
})
.catch(error => {
  console.log("Erro ao buscar dados da API:", error);
});

function addlinha(dadosAPI) {
  const tabela = document.getElementById('tabelaCorpo');

  dadosAPI.forEach(element => {
    const linha = document.createElement('tr');
    linha.innerHTML = `
      <td class="px-4 py-2">${element.id ?? '-'}</td>
      <td class="px-4 py-2">${element.nome}</td>
      <td class="px-4 py-2">${element.email}</td>
      <td class="px-4 py-2">
        <button class="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600" onclick="editar(this)">Editar</button>
      </td>
      <td class="px-4 py-2">
        <button class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600" onclick="remover(this)">Remover</button>
      </td>
    `;
    tabela.appendChild(linha);
  });
}


// Cadastra nova pessoa do formulário e envia via POST
function cadastrar(event) {
  event.preventDefault();

  const nome = document.getElementById('nome').value.trim();
  const email = document.getElementById('email').value.trim();

  if (nome && email) {
    // Adiciona a nova linha na interface com ID fictício (-)
    addlinha([{ id: '-', nome, email }]);
    
    // Limpa os campos do formulário
    document.getElementById('nome').value = '';
    document.getElementById('email').value = '';

    // Envia para a API (POST)
    fetch('http://localhost:8080/api/alunos', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ nome, email })
    })
    .then(response => response.json())
    .then(data => {
      console.log("Resposta da API:", data);
    })
    .catch(error => {
      console.error("Erro ao enviar dados:", error);
    });

    Swal.fire({
      icon: 'success',
      title: 'Sucesso!',
      text: 'Cadastro feito com sucesso!'
    });
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Erro!',
      text: 'Faltam dados para o cadastro.'
    });
  }
}

// Remove uma linha da tabela com confirmação
function remover(botao) {
  Swal.fire({
    icon: 'question',
    title: 'Você tem certeza?',
    showCancelButton: true,
    confirmButtonText: 'Sim',
    cancelButtonText: 'Não'
  }).then((result) => {
    if (result.isConfirmed) {
      const linharemover = botao.closest('tr');
      linharemover.remove();
      Swal.fire('Removido!', '', 'success');
    } else {
      Swal.fire('Cancelado', '', 'info');
    }
  });
}
// Botão de editar
function editar(botao) {
  const linha = botao.closest('tr');
  const colunas = linha.querySelectorAll('td');

  const id = colunas[0].textContent;
  const nomeAtual = colunas[1].textContent;
  const emailAtual = colunas[2].textContent;

  Swal.fire({
    title: 'Editar Cadastro',
    html: `
      <input id="swal-nome" class="swal2-input" placeholder="Nome" value="${nomeAtual}">
      <input id="swal-email" class="swal2-input" placeholder="E-mail" value="${emailAtual}">
    `,
    focusConfirm: false,
    showCancelButton: true,
    confirmButtonText: 'Salvar',
    cancelButtonText: 'Cancelar',
    preConfirm: () => {
      const novoNome = document.getElementById('swal-nome').value;
      const novoEmail = document.getElementById('swal-email').value;
      if (!novoNome || !novoEmail) {
        Swal.showValidationMessage('Todos os campos são obrigatórios');
        return false;
      }
      return { novoNome, novoEmail };
    }
  }).then((result) => {
    if (result.isConfirmed) {
      const { novoNome, novoEmail } = result.value;

      // Atualiza a linha visualmente
      colunas[1].textContent = novoNome;
      colunas[2].textContent = novoEmail;

      // Se tiver ID válido, envia para a API
      if (id !== '-') {
        fetch(`http://localhost:8080/api/livro/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ nome: novoNome, email: novoEmail })
        })
        .then(response => response.json())
        .then(data => {
          addlinha([data]); // data contém o ID real retornado pela API
          Swal.fire({
            icon: 'success',
            title: 'Sucesso!',
            text: 'Cadastro feito com sucesso!'
          });
        })
        .catch(error => {
          console.error("Erro ao atualizar dados:", error);
          Swal.fire('Erro', 'Não foi possível atualizar no servidor.', 'error');
        });
      } else {
        Swal.fire('Aviso', 'Cadastro ainda não sincronizado com o servidor.', 'info');
      }
    }
  });
}

