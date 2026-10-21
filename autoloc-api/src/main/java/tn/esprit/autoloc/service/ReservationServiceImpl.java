package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Reservation e, boolean isNew) {
        return e.getDateDebut() != null && e.getDateFin() != null && e.getStatut() != null;
    }

    @Override protected String getEntityName() { return "Reservation"; }
    @Override protected Long getId(Reservation e) { return e.getIdReservation(); }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setStatut(source.getStatut());
        target.setVehicule(source.getVehicule());
        target.setClient(source.getClient());
        target.setContrat(source.getContrat());
    }
}
