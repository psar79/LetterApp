package userletters.dao.entity;

import javax.persistence.*;

@Entity
public class LetterStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DeliveryStatus previous;
    private DeliveryStatus present;

    @OneToOne(mappedBy = "letterStatus")
    private Letter letter;

    public LetterStatus() {
    }

    public LetterStatus(Long id, DeliveryStatus previous, DeliveryStatus present, Letter letter) {
        this.id = id;
        this.previous = previous;
        this.present = present;
        this.letter = letter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryStatus getPrevious() {
        return previous;
    }

    public void setPrevious(DeliveryStatus previous) {
        this.previous = previous;
    }

    public DeliveryStatus getPresent() {
        return present;
    }

    public void setPresent(DeliveryStatus present) {
        this.present = present;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }
}
