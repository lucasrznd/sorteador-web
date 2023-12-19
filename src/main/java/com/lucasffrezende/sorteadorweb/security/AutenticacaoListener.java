package com.lucasffrezende.sorteadorweb.security;

import com.lucasffrezende.sorteadorweb.controllers.LoginController;
import com.lucasffrezende.sorteadorweb.models.Usuario;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import org.omnifaces.util.Faces;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutenticacaoListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        String paginaAtual = Faces.getViewId();

        boolean ehPaginaAutenticacao = paginaAtual.contains("/publico/login.xhtml");

        if (!ehPaginaAutenticacao) {
            LoginController loginController = LoginController.getInstance();

            if (loginController == null) {
                Faces.navigate("/publico/login.xhtml");
                return;
            }

            Usuario usuarioLogado = loginController.getUsuarioLogado();
            if (usuarioLogado == null) {
                Faces.navigate("/publico/login.xhtml");
                return;
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
