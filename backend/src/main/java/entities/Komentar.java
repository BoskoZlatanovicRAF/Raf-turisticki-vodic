package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Komentar {

    private Integer id;
    @NotNull(message = "Autor komentara je obavezan")
    @NotEmpty(message = "Autor komentara ne moze biti prazan")
    private String autor_komentara;

    @NotNull(message = "Tekst komentara je obavezan")
    @NotEmpty(message = "Tekst komentara ne moze biti prazan")
    private String tekst;

    @NotNull(message = "Datum je obavezan")
    @NotEmpty(message = "Datum ne moze biti prazan")
    private Timestamp datum;

    @NotNull(message = "Clanak je obavezan")
    @NotEmpty(message = "Clanak ne moze biti prazan")
    private Integer clanak_id;

}
