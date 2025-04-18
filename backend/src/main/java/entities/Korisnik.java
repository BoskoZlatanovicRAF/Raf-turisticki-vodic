package entities;


import entities.enums.Status_Korisnika;
import entities.enums.Tip_Korisnika;
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

public class Korisnik {

    @NotNull(message = "Email korisnika je obavezan")
    @NotEmpty(message = "Email korisnika ne moze biti prazan")
    private String email;

    @NotNull(message = "Lozinka korisnika je obavezna")
    @NotEmpty(message = "Lozinka korisnika ne mosze biti prazna")
    private String password;

    @NotNull(message = "Ime i prezime korisnika je obavezno")
    @NotEmpty(message = "Ime i prezime korisnika ne moze biti prazno")
    private String ime_prezime;

    @NotNull(message = "Tip korisnika je obavezan")
    @NotEmpty(message = "Tip korisnika ne moze biti prazan")
    private Tip_Korisnika tip_korisnika;

    @NotNull(message = "Status korisnika je obavezan")
    @NotEmpty(message = "Status korisnika ne moze biti prazan")
    private Status_Korisnika status_korisnika;

}
