package gu.dit213.group28.model.records;

import gu.dit213.group28.model.enums.Sector;

/**
 * Record for storing the state of the market.
 *
 * @param sector Sector in question.
 * @param value value of sector.
 */
public record MarketOutput(Sector sector, double value) {}
