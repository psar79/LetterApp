package praca;

public class ArchDossierMessage<Q extends ArchMessage, T extends ArchMessage> {


    public T response(){
        return T;
    }

    public void request(Q request) {

    }
}
