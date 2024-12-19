package gu.dit213.group28.model.records;

import gu.dit213.group28.model.enums.Sector;

/**
 * Record for storing the state of the User.
 *
 * @param sector Sector in question.
 * @param quantity number of assets in sector owned by user.
 * @param value value of sector.
 */
public record UserOutput(Sector sector, int quantity, double value) {}
