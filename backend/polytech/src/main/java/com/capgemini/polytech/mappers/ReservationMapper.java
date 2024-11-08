package com.capgemini.polytech.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.capgemini.polytech.dto.ReservationDto;
import com.capgemini.polytech.models.Reservation;



@Mapper(componentModel = "spring")
public interface ReservationMapper {
    
        
    
    ReservationDto toDTO(Reservation Reservation);
    List<ReservationDto> toDTO(List<Reservation> Reservations);

    
    @Mapping(target = "utilisateur.password", ignore = true)
    Reservation toEntity(ReservationDto reservationDTO);
             
    } 
    

    
    

