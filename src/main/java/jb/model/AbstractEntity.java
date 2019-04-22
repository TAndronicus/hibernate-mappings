package jb.model;


import javax.persistence.*;

@MappedSuperclass
@SequenceGenerator(name = "seq", sequenceName = "seq")
class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

}
