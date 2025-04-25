package com.github.rodvpx.apiconsultoriomedicospring;

import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import com.github.rodvpx.apiconsultoriomedicospring.model.Usuario;
import com.github.rodvpx.apiconsultoriomedicospring.repository.RecepcionistaRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ApiConsultorioMedicoSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApiConsultorioMedicoSpringApplication.class, args);
        criarPrimeiroRecepcionista(context);
    }

    public static void criarPrimeiroRecepcionista(ConfigurableApplicationContext context) {
        // Obtém os repositórios necessários
        UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);
        RecepcionistaRepository recepcionistaRepository = context.getBean(RecepcionistaRepository.class);
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // Verifica se já existem usuários no banco
        if (usuarioRepository.count() == 0) {
            try {
                // Cria o usuário do recepcionista
                Usuario usuarioAdmin = new Usuario();
                usuarioAdmin.setEmail("admin@clinica.com");
                usuarioAdmin.setSenha(passwordEncoder.encode("Admin123@")); // Senha temporária
                usuarioAdmin.setRole("ROLE_RECEPCIONISTA");
                usuarioRepository.save(usuarioAdmin);

                // Cria o recepcionista
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setUsuario(usuarioAdmin);
                recepcionista.setCpf("12345678901"); // CPF válido
                recepcionista.setNome("Administrador Principal");
                recepcionista.setTelefone("(11) 99999-9999");
                recepcionista.setDataContratacao(LocalDate.now().minusDays(1));
                recepcionista.setStatus(true);

                recepcionistaRepository.save(recepcionista);

                System.out.println("\n=============================================");
                System.out.println("  PRIMEIRO RECEPCIONISTA CRIADO COM SUCESSO");
                System.out.println("=============================================");
                System.out.println("  Email: admin@clinica.com");
                System.out.println("  Senha temporária: Admin123@");
                System.out.println("=============================================");
                System.out.println("  ALTERE A SENHA NO PRIMEIRO LOGIN!");
                System.out.println("=============================================\n");
            } catch (Exception e) {
                System.err.println("\nERRO AO CRIAR RECEPCIONISTA INICIAL:");
                e.printStackTrace();
            }
        } else {
            System.out.println("\nSistema já possui usuários cadastrados. Nenhum recepcionista inicial foi criado.\n");
        }
    }
}