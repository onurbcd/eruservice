package com.onurbcd.eruservice.dto.day;

import com.onurbcd.eruservice.persistency.constraint.MaxYear;
import com.onurbcd.eruservice.persistency.constraint.MinYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateMonthDto {

    @NotNull
    @MinYear
    @MaxYear
    private Short calendarYear;

    @NotNull
    @Min(1)
    @Max(12)
    private Short calendarMonth;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CreateMonthDto that = (CreateMonthDto) o;

        return new EqualsBuilder()
                .append(calendarYear, that.calendarYear)
                .append(calendarMonth, that.calendarMonth)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(calendarYear)
                .append(calendarMonth)
                .toHashCode();
    }
}
