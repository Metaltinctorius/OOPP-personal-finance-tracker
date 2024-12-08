package gu.dit213.group28.model.records;

import gu.dit213.group28.model.enums.Sector;

public record UserOutput(Sector sector, int quantity, double value) {}
