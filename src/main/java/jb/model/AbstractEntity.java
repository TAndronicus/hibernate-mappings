package jb.model;


import javax.persistence.*;

@MappedSuperclass
@SequenceGenerator(name = "seq", sequenceName = "seq")
class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractEntity id(final Long id) {
        this.id = id;
        return this;
    }

}
