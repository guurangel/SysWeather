package com.java.sysweather.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do município não pode estar em branco.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O estado não pode estar em branco.")
    @Size(min = 2, max = 2, message = "O estado deve conter a sigla de 2 letras (ex: SP, RJ).")
    private String estado;

    @NotNull(message = "O número de habitantes é obrigatório.")
    @Positive(message = "O número de habitantes deve ser positivo.")
    private Integer numero_habitantes;

    @NotBlank(message = "O clima não pode estar em branco.")
    @Size(max = 50, message = "O tipo de clima deve ter no máximo 50 caracteres.")
    private String clima;

    @NotBlank(message = "A região não pode estar em branco.")
    @Pattern(
        regexp = "Norte|Nordeste|Centro-Oeste|Sudeste|Sul",
        message = "A região deve ser uma das seguintes: Norte, Nordeste, Centro-Oeste, Sudeste, Sul."
    )
    private String regiao;

    @NotNull(message = "A altitude é obrigatória.")
    @PositiveOrZero(message = "A altitude não pode ser negativa.")
    private Double altitude;

    @NotNull(message = "A área (km²) é obrigatória.")
    @Positive(message = "A área (km²) deve ser um valor positivo.")
    private Double areaKm2;

    @OneToMany(mappedBy = "municipio")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias;

}
