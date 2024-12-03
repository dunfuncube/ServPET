package com.servPet.fnc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FncVO class corresponding to the FNC table in the database.
 */
@Entity  // Marks the class as a JPA entity
@Table(name = "FNC")  // Specifies the table name in the database
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FncVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "FNC_ID")  // Corresponds to the FNC_ID column in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generates IDs
    private Integer fncId;  // Function ID, primary key
    
    @Column(name = "FNC_NAME")
    @NotEmpty(message = "請輸入功能名稱")  // Ensures the function name is not empty
    @Size(min = 2, max = 20, message = "功能名稱長度需在{min}到{max}之間")  // Validates the length of the function name
    private String fncName;  // Function name
    
    @Column(name = "FNC_DES")
    @NotEmpty(message = "請輸入功能描述")  // Ensures the function description is not empty
    @Size(min = 2, max = 255, message = "功能描述長度需在{min}到{max}之間")  // Validates the length of the function description
    private String fncDes;  // Function description
    
    @Column(name = "FNC_CRE_AT")
    @NotNull(message = "建立日期不能為空")  // Ensures creation date is not null
    private LocalDateTime fncCreAt;  // Creation date
    
    @Column(name = "FNC_UPD_AT")
    @NotNull(message = "更新日期不能為空")  // Ensures update date is not null
    private LocalDateTime fncUpdAt;  // Update date
}