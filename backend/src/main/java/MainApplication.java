import entities.Aktivnost;
import entities.Clanak_Aktivnost;
import filters.AuthFilter;
import filters.Cors;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import repositories.aktivnost.AktivnostRepository;
import repositories.aktivnost.AktivnostRepositoryImpl;
import repositories.clanak.ClanakRepository;
import repositories.clanak.ClanakRepositoryImpl;
import repositories.clanak_aktivnost.ClanakAktivnostRepository;
import repositories.clanak_aktivnost.ClanakAktivnostRepositoryImpl;
import repositories.destinacija.DestinacijaRepository;
import repositories.destinacija.DestinacijaRepositoryImpl;
import repositories.komentar.KomentarRepository;
import repositories.komentar.KomentarRepositoryImpl;
import repositories.korisnik.KorisnikRepository;
import repositories.korisnik.KorisnikRepositoryImpl;
import services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class MainApplication extends ResourceConfig {

    public MainApplication(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(KorisnikRepositoryImpl.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bind(AktivnostRepositoryImpl.class).to(AktivnostRepository.class).in(Singleton.class);
                this.bind(ClanakRepositoryImpl.class).to(ClanakRepository.class).in(Singleton.class);
                this.bind(ClanakAktivnostRepositoryImpl.class).to(ClanakAktivnostRepository.class).in(Singleton.class);
                this.bind(DestinacijaRepositoryImpl.class).to(DestinacijaRepository.class).in(Singleton.class);
                this.bind(KomentarRepositoryImpl.class).to(KomentarRepository.class).in(Singleton.class);



                this.bindAsContract(AktivnostService.class);
                this.bindAsContract(ClanakAktivnostService.class);
                this.bindAsContract(ClanakService.class);
                this.bindAsContract(DestinacijaService.class);
                this.bindAsContract(KomentarService.class);
                this.bindAsContract(KorisnikService.class);
            }
        };
        register(binder);

        packages("resources");

        register(AuthFilter.class);
        register(Cors.class);

    }
}
