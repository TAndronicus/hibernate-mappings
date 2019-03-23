package jb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
@SequenceGenerator(name = "seq", sequenceName = "seq")
class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

}
