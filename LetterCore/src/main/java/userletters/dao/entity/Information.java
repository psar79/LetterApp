package userletters.dao.entity;

import javax.persistence.*;

@Entity
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int size;
    private String type;

    @OneToOne(mappedBy = "information")
    private Letter letter;

    public Information() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
