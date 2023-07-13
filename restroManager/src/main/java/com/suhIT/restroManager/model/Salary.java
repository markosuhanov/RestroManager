package com.suhIT.restroManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Salary {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DecimalMin(value = "0.0", message = "Amount must be a positive number!")
    private double amount;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Nullable
    private LocalDate endDate;
    @ManyToOne
    private User user;
    @NotNull(message = "User activity is required")
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Salary salary = (Salary) o;
        return getId() != null && Objects.equals(getId(), salary.getId());
    }

}
