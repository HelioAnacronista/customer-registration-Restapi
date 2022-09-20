package io.github.helioanacronista.customerRegistrerFullCliente.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, name = "data_nacimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date nacimento;

    @Column(nullable = false, length = 250)
    private String endereco;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false, length = 11)
    private String cpf;


}
