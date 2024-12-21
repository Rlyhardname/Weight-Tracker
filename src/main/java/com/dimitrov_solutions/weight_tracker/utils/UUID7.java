package com.dimitrov_solutions.weight_tracker.utils;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDType;
import com.fasterxml.uuid.impl.UUIDUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * UUID generator and validator
 */
public class UUID7 {

    public static final String Uuid_not_supported = "UUID not version 7 or Invalid UUID for value %s";

    /**
     * Generates UUID7 with first 48 bits being the timestamp of the moment
     * of generating the UUID
     */
    public static UUID generateUuid7() {
        return Generators.timeBasedEpochGenerator().generate();
    }

    public static boolean isUuidValid(UUID uuid) {
        if (Objects.isNull(uuid)) {
            throw new NullPointerException();
        }

        if (isVersion7(uuid)) {
            return true;
        }

        throw new IllegalArgumentException(getExceptionMessage(Uuid_not_supported, uuid));
    }

    /**
     * Returns LocalDateTime of the UUID timestamp towards UTC+3 timezone
     */
    public static LocalDateTime localDtFromUuid7(UUID uuid) {
        if (Objects.isNull(uuid)) {
            throw new NullPointerException();
        }

        if (isVersion7(uuid)) {
            long ms = extractTimestampFromUUIDv7(uuid);
            Instant timestamp = Instant.ofEpochMilli(ms);
            ZonedDateTime zdt = timestamp.atZone(ZoneId.of("UTC+3"));
            return LocalDateTime.from(zdt);
        }

        throw new IllegalArgumentException(getExceptionMessage(Uuid_not_supported, uuid));
    }

    private static String getExceptionMessage(String message, UUID uuid) {
        return String.format(message, uuid);
    }

    /**
     * Extracts the first 48 bits from UUID which correlate to
     * the timestamp
     */
    private static long extractTimestampFromUUIDv7(UUID uuid) {
        // UUID v7 stores the timestamp in the first 48 bits
        // Get the most significant bits and extract the first 48 bits
        long mostSigBits = uuid.getMostSignificantBits();

        // Shift right by 16 to remove the version (UUID v7 is in bits 12-15)
        // and get the first 48 bits for the timestamp
        long timestampMs = mostSigBits >>> 16;

        return timestampMs;
    }

    private static boolean isVersion7(UUID uuid) {
        return UUIDUtil.typeOf(uuid) == UUIDType.TIME_BASED_EPOCH;
    }
}