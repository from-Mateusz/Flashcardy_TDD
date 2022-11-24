package cz.mateusz.flashcardy.tdd.security;

import cz.mateusz.flashcardy.tdd.player.Player;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class WhoIsAuthenticatedService {

    private final static Player NO_PLAYER = null;

    private WhoIsAuthenticatedService() {}

    public Optional<Player> tell() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication.getPrincipal() == null) {
//            return Optional.ofNullable(NO_PLAYER);
//        }
//        return Optional.ofNullable((Player) authentication.getPrincipal());
        return null;
    }
}
