package ru.team38.common.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private Long id;
    private boolean isDeleted;
    private String title;
    private Long countryId;
}