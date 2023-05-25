package com.javeriana.tool_manager.RelationEntities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javeriana.tool_manager.Entities.City;
import com.javeriana.tool_manager.Entities.Tool;
import com.javeriana.tool_manager.Interfaces.ICompoundId;

import jakarta.persistence.Column;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Entity
@Table(name = "tool_city")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ToolCity extends AbstractRelationEntity
{
    @Getter
    @EmbeddedId
    private ToolCityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("toolId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "tool_id")
    @Getter
    @Setter
    private Tool tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("cityId")
    @JoinColumn(name = "city_id")
    @Getter
    @Setter
    private City city;

    @Getter
    @Setter
    @Column(name = "cantidad")
    private Integer quantity;

    public ToolCity(ToolCityId pId, Tool pTool, City pCity, Integer pQuantity)
    {
        this.id = pId;
        this.tool = pTool;
        this.city = pCity;
        this.quantity = pQuantity;
    }

    public ToolCity()
    {

    }

    @Override
    public void setId(ICompoundId pId) 
    {
        this.id = (ToolCityId) pId;
    }

}
