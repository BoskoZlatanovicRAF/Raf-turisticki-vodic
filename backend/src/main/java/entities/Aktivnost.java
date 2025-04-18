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
public class Aktivnost {

    @NotNull(message = "Naziv aktivnosti je obavezan")
    @NotEmpty(message = "Naziv aktivnosti ne moze biti prazan")
    private String naziv;
}
