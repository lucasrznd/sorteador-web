//Realiza o fechamento do modal de Sorteio e abre o Modal ganhador
function agendarFechamentoModalSorteio() {
    setTimeout(function () {
        PF('modalSorteio').hide();
        PF('modalGanhador').show();
    }, 4000);
}

// Formata o telefone
function formatarTelefone(telefone) {
    // Remove todos os caracteres não numéricos
    return telefone.replace(/\D/g, '');
}

// Redirecionamento para Whatsapp
function redirectToWhatsApp(numero, mensagem) {
    // Formatar o número de telefone
    var numeroFormatado = formatarTelefone(numero);

    // Codificar a mensagem para ser passada como parâmetro na URL
    var mensagemCodificada = encodeURIComponent(mensagem);

    // Construir a URL do WhatsApp com o número e a mensagem
    var url = 'https://wa.me/' + numeroFormatado + '?text=' + mensagemCodificada;

    // Abrir a URL em uma nova guia
    window.open(url, '_blank');
}