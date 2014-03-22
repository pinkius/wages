package com.webstersmalley.wages.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Entity
public class TimeSheetEntryType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal hourlyRate;

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

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public TimeSheetEntryType() {

    }

    public TimeSheetEntryType(String name, BigDecimal hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        TimeSheetEntryType rhs = (TimeSheetEntryType) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(name).
                toHashCode();
    }

}
