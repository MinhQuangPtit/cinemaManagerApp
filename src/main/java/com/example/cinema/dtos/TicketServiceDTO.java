package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketServiceDTO {
    @NotEmpty(message = "ticket_id cannot be empty")
    @JsonProperty("ticket_id")
    private int ticketId;

    @NotEmpty(message = "service_id cannot be empty")
    @JsonProperty("service_id")
    private int serviceId;

    private String description;
}
