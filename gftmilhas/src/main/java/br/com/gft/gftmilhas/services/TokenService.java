package br.com.gft.gftmilhas.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.gft.gftmilhas.entities.Usuario;

@Service
public class TokenService {

    @Lazy
    @Autowired
    private AuthenticationManager authManager;

    @Value("${gft_milhas.jwt.expiration}")
    private String expiration;

    @Value("${gft_milhas.jwt.secret}")
    private String secret;

    @Value("${gft_milhas.jwt.issuer}")
    private String issuer;


    private Algorithm criarAlgoritmo() {
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(Usuario usuario) {

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(dataExpiracao)
                .withSubject(usuario.getId().toString())
                .sign(this.criarAlgoritmo());

    }

    public boolean verificaToken(String token) {

        try {
            if (token == null)
                return false;

            JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);

            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public Long retornarIdUsuario(String token) {
        String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();
        return Long.parseLong(subject);
    }

}
