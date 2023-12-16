function agendarFechamentoModalSorteio() {
    setTimeout(function () {
        PF('modalSorteio').hide();
        PF('modalGanhador').show();
    }, 4000);
}