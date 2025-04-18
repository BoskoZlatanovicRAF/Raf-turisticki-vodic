package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Clanak {

    private Integer id;
    @NotNull(message = "Naslov je obavezan")
    @NotEmpty(message = "Naslov ne moze biti prazan")
    private String naslov;

    @NotNull(message = "Tekst je obavezan")
    @NotEmpty(message = "Tekst ne moze biti prazan")
    private String tekst;

    private Integer broj_poseta;

    @NotNull(message = "Autor je obavezan")
    @NotEmpty(message = "Autor ne moze biti prazan")
    private String autor_email;

    @NotNull(message = "Destinacija je obavezna")
    @NotEmpty(message = "Destinacija ne moze biti prazna")
    private String destinacija_ime;


    private Timestamp datum;
}
