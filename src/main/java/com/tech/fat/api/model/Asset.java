package com.tech.fat.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="asset")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{asset.name.notNull}")
    @Size(min = 1, max = 80, message = "{asset.name.size}")
    private String name;

    @NotNull(message = "{asset.quantity.notNull}")
    @Min(value = 1, message = "{asset.quantity.min}")
    @Max(value = 999999999, message = "{asset.quantity.max}")
    private Long quantity;

    @NotNull(message = "{asset.price.notNull}")
    @DecimalMin(value = "0.00", message = "{asset.price.DecimalMin}")
    @DecimalMax(value = "9999999999.99", message = "{asset.price.DecimalMax}")
    @Digits(integer=10, fraction=2, message = "{asset.price.digits}")
    private BigDecimal price;

    @NotNull(message = "{asset.costBasis.notNull}")
    @DecimalMin(value = "0.00", message = "{asset.costBasis.DecimalMin}")
    @DecimalMax(value = "9999999999.99", message = "{asset.costBasis.DecimalMax}")
    @Digits(integer=10, fraction=2, message = "{asset.costBasis.digits}")
    private BigDecimal costBasis;

    @Size(min = 1, max = 500, message = "{asset.notes.size}")
    private String notes;

    private String userName;
}
