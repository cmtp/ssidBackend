package com.ssid.api.apissid.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *@author Franz A. Lopez Choque
 */
@Entity
@Table(name = "causing_agents")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CausingAgent extends ModelBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ausing_agent_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "ausing_agent_name")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "causingAgent")
    private Set<Accident> accidentSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Accident> getAccidentSet() {
        return accidentSet;
    }

    public void setAccidentSet(Set<Accident> accidentSet) {
        this.accidentSet = accidentSet;
    }
}
