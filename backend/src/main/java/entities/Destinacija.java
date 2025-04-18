package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Destinacija {


    @NotNull(message = "Ime destinacije je obavezno")
    @NotEmpty(message = "Ime destinacije ne moze biti prazno")
    private String ime;

    @NotNull(message = "Opis destinacije je obavezan")
    @NotEmpty(message = "Opis destinacije ne moze biti prazan")
    private String opis;
}
