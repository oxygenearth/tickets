package com.aerolinea.tickets.modelo;

/**
 * ENUM to set three possible values for the ticketType field
 */
public enum TicketType {
    ONE_WAY_TRIP("One way trip"),
    ROUND_TRIP("Round trip"),
    MULTIPLE_DESTINATIONS("Multiple destination");

    private final String displayName;

    TicketType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
